package com.sollian.iu;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
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
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;
import com.sollian.base.Utils.DirUtil;
import com.sollian.buz.http.IUHttpManager;

import java.io.InputStream;

/**
 * @author sollian on 2017/9/27.
 */
@GlideModule(
        glideName = "GlideIU"
)
public class IUGlideModule extends AppGlideModule {
    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        builder.setDiskCache(new DiskLruCacheFactory(DirUtil.getImageDir(), 1024 * 1024 * 100))
               .setMemoryCache(new LruResourceCache(1024 * 1024 * 20))
               .setDefaultRequestOptions(getRequestOptions())
               .setDefaultTransitionOptions(Drawable.class,
                       DrawableTransitionOptions.withCrossFade())
               .setDefaultTransitionOptions(Bitmap.class, BitmapTransitionOptions.withCrossFade())
               .setLogLevel(Log.WARN)
        ;
    }

    @Override
    public void registerComponents(Context context, Glide glide, Registry registry) {
        registry.replace(GlideUrl.class, InputStream.class,
                new OkHttpUrlLoader.Factory(IUHttpManager.Companion.getInstance().getClient()));
    }

    private static RequestOptions getRequestOptions() {
        return new RequestOptions()
                .placeholder(R.mipmap.iu_default_green)
                .error(R.mipmap.iu_default_gray)
                .format(DecodeFormat.PREFER_ARGB_8888)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                ;
    }
}
