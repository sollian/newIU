package com.sollian.iu.utils;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.target.Target;

/**
 * @author sollian on 2017/9/29.
 */

public final class GlideUtil {
    private GlideUtil() {
    }

    public static Target<?> load(Context context, String url, ImageView imageView) {
        return GlideIU.with(context)
                      .load(url)
                      .dontAnimate()
                      .dontTransform()
                      .into(imageView);
    }

    public static Target<?> loadWithAnim(Context context, String url, ImageView imageView) {
        return GlideIU.with(context)
                      .load(url)
                      .transition(DrawableTransitionOptions.withCrossFade())
                      .into(imageView);
    }

    public static void resume(Context context) {
        GlideIU.with(context).resumeRequests();
    }

    public static void pause(Context context) {
        GlideIU.with(context).pauseRequests();
    }

    public static void clear(Context context, View view) {
        GlideIU.with(context).clear(view);
    }
}
