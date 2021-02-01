package com.zxn.utils

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import androidx.annotation.ColorInt
import androidx.annotation.Size

/**
 * Created by zxn on 2019/7/11.
 */
object DrawableFactory {
    /**
     * 代码中创建带不带圆角的shape背景图.
     *
     * @param argb 颜色值.
     * @return GradientDrawable
     */
    fun create(@ColorInt argb: Int): GradientDrawable {
        return create(argb, 0f)
    }

    /**
     * 代码中创建带四个圆角的shape背景图.
     *
     * @param solidArgb 颜色值.
     * @param radius    半径值.
     * @return GradientDrawable
     */
    fun create(@ColorInt solidArgb: Int, radius: Float): GradientDrawable {
//        GradientDrawable gradientDrawable = new GradientDrawable();
//        gradientDrawable.setColor(argb);
//        gradientDrawable.setCornerRadius(radius);
//        return gradientDrawable;
        return create(solidArgb, 0, radius, 0)
    }

    /**
     * shape背景图
     *
     * @param solidArgb   背景颜色
     * @param strokeArgb  边框的颜色
     * @param radius      圆角半径
     * @param strokeWidth 边框的粗细
     * @return  GradientDrawable
     */
    fun create(@ColorInt solidArgb: Int, @ColorInt strokeArgb: Int, radius: Float, strokeWidth: Int): GradientDrawable {
        val gradientDrawable = GradientDrawable()
        gradientDrawable.setColor(solidArgb)
        if (strokeArgb != 0 && strokeWidth != 0) {
            gradientDrawable.setStroke(strokeWidth, strokeArgb)
        }
        gradientDrawable.cornerRadius = radius
        return gradientDrawable
    }

    /**
     * 代码中创建带四个圆角的shape背景图.
     *
     * @param colorString 颜色值.
     * @param radius      半径.
     * @return GradientDrawable
     */
    fun create(@Size(min = 1) colorString: String?, radius: Float): GradientDrawable {
        val gradientDrawable = GradientDrawable()
        gradientDrawable.setColor(Color.parseColor(colorString))
        gradientDrawable.cornerRadius = radius
        return gradientDrawable
    }

    /**
     * 改变原有shape背景图的颜色.
     *
     * @param drawable shape背景图
     * @param argb     颜色
     * @return GradientDrawable
     */
    fun create(drawable: Drawable?, @ColorInt argb: Int): GradientDrawable {
        return if (drawable is GradientDrawable) {
            drawable.setColor(argb)
            drawable
        } else {
            create(argb)
        }
    }
}