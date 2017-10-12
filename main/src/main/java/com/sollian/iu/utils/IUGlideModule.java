package com.sollian.iu.utils;

import android.content.Context;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;
import com.sollian.base.Utils.DirUtil;
import com.sollian.base.Utils.IUUtil;
import com.sollian.base.http.IHttpProgressListener;
import com.sollian.buz.http.IUHttpManager;
import com.sollian.iu.R;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;

/**
 * @author sollian on 2017/9/27.
 */
@GlideModule(
        glideName = "GlideIU"
)
public class IUGlideModule extends AppGlideModule implements IHttpProgressListener {
    private static final Map<String, IHttpProgressListener> LISTENERS = new HashMap<>();

    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        builder.setDiskCache(new DiskLruCacheFactory(DirUtil.getImageDir(), 1024 * 1024 * 100))
               .setMemoryCache(new LruResourceCache(1024 * 1024 * 20))
               .setDefaultRequestOptions(getRequestOptions())
               .setLogLevel(Log.WARN);
        //        if (needAnim()) {
        //            builder.setDefaultTransitionOptions(Drawable.class,
        //                    DrawableTransitionOptions.withCrossFade())
        //                   .setDefaultTransitionOptions(Bitmap.class,
        //                           BitmapTransitionOptions.withCrossFade());
        //        }
    }

    @Override
    public void registerComponents(Context context, Glide glide, Registry registry) {
        OkHttpClient client = IUHttpManager.getInstance().getDownloadClient(this);
        registry.replace(GlideUrl.class, InputStream.class,
                new OkHttpUrlLoader.Factory(client));
    }

    private RequestOptions getRequestOptions() {
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.iu_default_green)
                .error(R.drawable.iu_default_gray)
                .format(DecodeFormat.PREFER_ARGB_8888)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        //        if (!needAnim()) {
        //            options.dontAnimate()
        //                   .dontTransform();
        //        }
        return options;
    }

    protected boolean needAnim() {
        return false;
    }

    static void putListener(String url, IHttpProgressListener listener) {
        LISTENERS.put(url, listener);
    }

    static void removeListener(String url) {
        LISTENERS.remove(url);
    }

    @Override
    public void onUpdate(final String url, final long bytesRead, final long contentLength) {
        if (contentLength < bytesRead) {
            removeListener(url);
        }

        final IHttpProgressListener listener = LISTENERS.get(url);
        if (listener == null) {
            return;
        }

        IUUtil.post(new Runnable() {
            @Override
            public void run() {
                listener.onUpdate(url, bytesRead, contentLength);
            }
        });
    }
}
