package com.sollian.base.view.roundimage;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.sollian.base.R;


/**
 * @author lishouxian on 2016/12/14.
 */

public class IURoundImageView extends AppCompatImageView {
    private final IURoundImageHelper helper = new IURoundImageHelper(this);

    public IURoundImageView(Context context) {
        super(context);
    }

    public IURoundImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IURoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context
                .obtainStyledAttributes(attrs, R.styleable.IURoundImageView, defStyleAttr, 0);
        helper.setShape(typedArray
                .getInt(R.styleable.IURoundImageView_riv_shape,
                        IURoundImageHelper.SHAPE_RECTANGLE));
        helper.setCornerRadius(typedArray
                .getDimensionPixelSize(R.styleable.IURoundImageView_riv_cornerRadius,
                        IURoundImageHelper.DEFAULT_CORNER_R5));
        int boundWidth = typedArray
                .getDimensionPixelSize(R.styleable.IURoundImageView_riv_strokeWidth, 0);
        helper.setBoundColor(typedArray
                .getColor(R.styleable.IURoundImageView_riv_strokeColor,
                        IURoundImageHelper.DEFAULT_BOUND_COLOR));

        if (boundWidth <= 0) {
            boundWidth = 0;
        }
        helper.setBoundWidth(boundWidth);
        typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        helper.doDrawContent(canvas);
    }

    public int getShape() {
        return helper.getShape();
    }

    public void setShape(int shape) {
        helper.setShape(shape);
        invalidate();
    }

    public int getCornerRadius() {
        return helper.getCornerRadius();
    }

    public void setCornerRadius(int cornerRadius) {
        helper.setCornerRadius(cornerRadius);
        invalidate();
    }

    public int getBoundWidth() {
        return helper.getBoundWidth();
    }

    public void setBoundWidth(int boundWidth) {
        helper.setBoundWidth(boundWidth);
        invalidate();
    }

    public int getBoundColor() {
        return helper.getBoundColor();
    }

    public void setBoundColor(int boundColor) {
        helper.setBoundColor(boundColor);
        invalidate();
    }
}
