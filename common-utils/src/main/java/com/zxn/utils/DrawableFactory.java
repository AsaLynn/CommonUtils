package com.zxn.utils;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import androidx.annotation.ColorInt;
import androidx.annotation.Size;

/**
 * Created by zxn on 2019/7/11.
 */
public class DrawableFactory {

    /**
     * 代码中创建带不带圆角的shape背景图.
     *
     * @param argb 颜色值.
     * @return GradientDrawable
     */
    public static GradientDrawable create(@ColorInt int argb) {
        return create(argb, 0);
    }

    /**
     * 代码中创建带四个圆角的shape背景图.
     *
     * @param solidArgb 颜色值.
     * @param radius    半径值.
     * @return GradientDrawable
     */
    public static GradientDrawable create(@ColorInt int solidArgb, float radius) {
//        GradientDrawable gradientDrawable = new GradientDrawable();
//        gradientDrawable.setColor(argb);
//        gradientDrawable.setCornerRadius(radius);
//        return gradientDrawable;
        return create(solidArgb, 0, radius, 0);
    }

    /**
     * shape背景图
     *
     * @param solidArgb   背景颜色
     * @param strokeArgb  边框的颜色
     * @param radius      圆角半径
     * @param strokeWidth 边框的粗细
     * @return
     */
    public static GradientDrawable create(@ColorInt int solidArgb, @ColorInt int strokeArgb, float radius, int strokeWidth) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(solidArgb);
        if (strokeArgb != 0 && strokeWidth != 0) {
            gradientDrawable.setStroke(strokeWidth, strokeArgb);
        }
        gradientDrawable.setCornerRadius(radius);
        return gradientDrawable;
    }

    /**
     * 代码中创建带四个圆角的shape背景图.
     *
     * @param colorString 颜色值.
     * @param radius      半径.
     * @return GradientDrawable
     */
    public static GradientDrawable create(@Size(min = 1) String colorString, float radius) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(Color.parseColor(colorString));
        gradientDrawable.setCornerRadius(radius);
        return gradientDrawable;
    }

    /**
     * 改变原有shape背景图的颜色.
     *
     * @param drawable shape背景图
     * @param argb     颜色
     * @return GradientDrawable
     */
    public static GradientDrawable create(Drawable drawable, @ColorInt int argb) {
        if (drawable instanceof GradientDrawable) {
            GradientDrawable gradientDrawable = (GradientDrawable) drawable;
            gradientDrawable.setColor(argb);
            return gradientDrawable;
        } else {
            return create(argb);
        }
    }
}
