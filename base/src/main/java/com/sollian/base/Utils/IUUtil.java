package com.sollian.base.Utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

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
}
