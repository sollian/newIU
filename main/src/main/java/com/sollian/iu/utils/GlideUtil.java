package com.sollian.iu.utils;

import android.content.Context;
import android.widget.ImageView;

/**
 * @author sollian on 2017/9/29.
 */

public class GlideUtil {
    public static void load(Context context, String url, ImageView imageView) {
        GlideIU.with(context)
               .load(url)
               .into(imageView);
    }
}
