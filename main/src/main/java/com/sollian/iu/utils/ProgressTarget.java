package com.sollian.iu.utils;

import android.graphics.drawable.Drawable;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;

import com.bumptech.glide.request.target.BaseTarget;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.transition.Transition;
import com.sollian.base.http.IHttpProgressListener;

/**
 * @author sollian on 2017/9/28.
 */

public abstract class ProgressTarget<T, Z> extends BaseTarget<Z> implements IHttpProgressListener {
    private T model;

    public ProgressTarget(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    public void setModel(T model) {
        this.model = model;
    }

    private void start() {
        IUGlideModule.putListener(String.valueOf(model), this);
    }

    private void stop() {
        IUGlideModule.removeListener(String.valueOf(model));
        model = null;
    }

    @Override
    public void onLoadStarted(
            @Nullable
                    Drawable placeholder) {
        super.onLoadStarted(placeholder);
        start();
    }

    @Override
    public void onLoadFailed(
            @Nullable
                    Drawable errorDrawable) {
        super.onLoadFailed(errorDrawable);
        stop();
    }

    @Override
    public void onLoadCleared(
            @Nullable
                    Drawable placeholder) {
        super.onLoadCleared(placeholder);
        stop();
    }

    @CallSuper
    @Override
    public void onResourceReady(Z resource, Transition<? super Z> transition) {
        stop();
    }

    @Override
    public void removeCallback(SizeReadyCallback cb) {
    }
}
