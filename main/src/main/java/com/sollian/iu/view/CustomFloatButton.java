package com.sollian.iu.view;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;

import com.gordonwong.materialsheetfab.AnimatedFab;

/**
 * @author sollian on 2017/9/29.
 */

public class CustomFloatButton extends FloatingActionButton implements AnimatedFab {
    private boolean forceHide;

    public CustomFloatButton(Context context) {
        super(context);
    }

    public CustomFloatButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomFloatButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void show() {
        show(0, 0);
    }

    @Override
    public void show(float translationX, float translationY) {
        if (forceHide) {
            return;
        }
        setVisibility(VISIBLE);
    }

    @Override
    public void hide() {
        if (forceHide) {
            return;
        }
        setVisibility(INVISIBLE);
    }

    public void setForceHide(boolean forceHide) {
        this.forceHide = forceHide;
        setVisibility(forceHide ? GONE : VISIBLE);
    }
}
