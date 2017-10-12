package com.sollian.iu.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.GridView;

/**
 * @author sollian on 2017/10/12.
 */

public class ForbidTouchGridView extends GridView {
    public ForbidTouchGridView(Context context) {
        super(context);
    }

    public ForbidTouchGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ForbidTouchGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ForbidTouchGridView(Context context, AttributeSet attrs, int defStyleAttr,
            int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }
}
