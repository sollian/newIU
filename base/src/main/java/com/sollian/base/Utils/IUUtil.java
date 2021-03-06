package com.sollian.base.Utils;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * @author sollian on 2017/9/28.
 */

public final class IUUtil {
    private static final Handler HANDLER = new Handler(Looper.getMainLooper());

    private IUUtil() {
    }

    public static int dp2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dp(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static void hideSoftKeyBoard(View v) {
        if (v == null) {
            return;
        }

        InputMethodManager imm = (InputMethodManager) BaseContext.Companion
                .getContext()
                .getSystemService(
                        Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(v.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public static void showSoftKeyBoard(View v) {
        if (v == null) {
            return;
        }

        InputMethodManager imm = (InputMethodManager) BaseContext.Companion
                .getContext()
                .getSystemService(
                        Context.INPUT_METHOD_SERVICE);
        if (!imm.isActive()) {
            imm.showSoftInput(v, 0);
        }
    }

    public static void post(
            @NonNull
                    Runnable runnable) {
        if (Thread.currentThread().getId() == Looper.getMainLooper().getThread().getId()) {
            runnable.run();
        } else {
            HANDLER.post(runnable);
        }
    }

    public static int str2Color(
            @NonNull
                    String str) {
        int i = str.hashCode();
        String color = '#' +
                //                ensureLength(Integer.toHexString(i >> 24 & 0xFF)) +
                ensureLength(Integer.toHexString(i >> 16 & 0xFF)) +
                ensureLength(Integer.toHexString(i >> 8 & 0xFF)) +
                ensureLength(Integer.toHexString(i & 0xFF));
        return Color.parseColor(color);
    }

    /**
     * Blend {@code color1} and {@code color2} using the given ratio.
     *
     * @param ratio of which to blend. 0.0 will return {@code color1}, 0.5 will give an even blend,
     * 1.0 will return {@code color2}.
     */
    public static int blendColors(int color1, int color2, float ratio) {
        float inverseRatio = 1.0f - ratio;
        float a = Color.alpha(color1) * inverseRatio + Color.alpha(color2) * ratio;
        float r = Color.red(color1) * inverseRatio + Color.red(color2) * ratio;
        float g = Color.green(color1) * inverseRatio + Color.green(color2) * ratio;
        float b = Color.blue(color1) * inverseRatio + Color.blue(color2) * ratio;
        return Color.argb((int) a, (int) r, (int) g, (int) b);
    }

    private static String ensureLength(String str) {
        if (str.length() == 1) {
            return '0' + str;
        }
        return str;
    }

    /**
     * 对时间戳进行自定义格式化
     *
     * @param timeStamp 时间戳
     */
    public static String formatTime(long timeStamp) {
        String strTime;
        long timeMillis = timeStamp * 1000;
        long curTime = System.currentTimeMillis();
        long tempTime = curTime - timeMillis;
        if (tempTime < 1000 * 60) {
            // 小于1min
            strTime = "刚刚";
        } else if (tempTime < 1000 * 60 * 60) {
            // 小于1h
            strTime = tempTime / (1000 * 60) + "分钟前";
        } else if (tempTime < 1000 * 60 * 60 * 24) {
            // 小于1天
            long lHour = tempTime / (1000 * 60 * 60);
            long lMin = tempTime % (1000 * 60 * 60) / (1000 * 60);
            strTime = lHour + "小时" + lMin + "分前";
        } else if (tempTime < 1000L * 60 * 60 * 24 * 30) {// 此处注意要将int转换为long
            // 小于1个月
            strTime = tempTime / (1000L * 60 * 60 * 24) + "天前";
        } else if (tempTime < 1000L * 60 * 60 * 24 * 30 * 12) {// 此处注意要将int转换为long
            // 小于1年
            strTime = tempTime / (1000L * 60 * 60 * 24 * 30) + "个月前";
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",
                    Locale.CHINA);
            strTime = sdf.format(timeMillis);
        }
        return strTime;
    }

    /**
     * 获取时间
     */
    public static String getLocalTime(long timeStamp) {
        long ts = timeStamp * 1000;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                Locale.CHINA);
        return sdf.format(ts);
    }
}
