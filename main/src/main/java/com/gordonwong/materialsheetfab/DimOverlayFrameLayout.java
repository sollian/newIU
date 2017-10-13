package com.gordonwong.materialsheetfab;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.sollian.iu.R;

/**
 * Created by Gordon Wong on 7/13/2015.
 * <p>
 * Layout that draws a dimmed overlay.
 */
public class DimOverlayFrameLayout extends FrameLayout {

    public DimOverlayFrameLayout(Context context) {
        super(context);
        init();
    }

    public DimOverlayFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DimOverlayFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.dim_overlay, this);
    }
}
