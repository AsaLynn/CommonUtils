package com.zxn.utils;

import android.graphics.drawable.Drawable;
import androidx.annotation.DrawableRes;
import android.widget.TextView;

/**
 * TextView工具类.
 * 1,设置TextView的左边或上边或右边或下边的图片,图片大小自适应
 * 2,设置TextView的左边或上边或右边或下边的图片,指定图片宽高相等
 * 3,设置TextView的左边或上边或右边或下边的图片,指定图片宽高不等
 *
 * Created by zxn on 2019/4/5.
 */
public class TVUtil {

    /**
     * TextView设置左边图标,图片大小自适应图片.
     *
     * @param textView 控件.
     * @param resId    图片资源id.
     */
    public static void drawableLeft(TextView textView, @DrawableRes int resId) {
        Drawable drawable = getDrawable(textView, resId);
        textView.setCompoundDrawables(drawable, null, null, null);
    }

    /**
     * TextView设置左边图标
     *
     * @param textView 控件.
     * @param resId    图片资源id.
     * @param size     指定图片的宽高,单位:px.
     */
    public static void drawableLeft(TextView textView, @DrawableRes int resId, int size) {
        Drawable drawable = getDrawable(textView, resId, size, size);
        textView.setCompoundDrawables(drawable, null, null, null);
    }

    /**
     * TextView设置左边图标
     *
     * @param textView     控件.
     * @param resId        图片资源id.
     * @param width 指定图片的宽,单位:px.
     * @param height 指定图片的高,单位:px.
     */
    public static void drawableLeft(TextView textView, @DrawableRes int resId, int width, int height) {
        Drawable drawable = getDrawable(textView, resId, width, height);
        textView.setCompoundDrawables(drawable, null, null, null);
    }

    /**
     * TextView设置顶部图标,图片大小自适应图片.
     *
     * @param textView 控件.
     * @param resId    图片资源id.
     */
    public static void drawableTop(TextView textView, @DrawableRes int resId) {
        Drawable drawable = getDrawable(textView, resId);
        textView.setCompoundDrawables(null, drawable, null, null);
    }

    /**
     * TextView设置顶部图标
     *
     * @param textView 控件.
     * @param resId    图片资源id.
     * @param size     指定图片的宽高,单位:px.
     */
    public static void drawableTop(TextView textView, @DrawableRes int resId, int size) {
        Drawable drawable = textView.getResources().getDrawable(resId);
        drawable.setBounds(0, 0, size, size);
        textView.setCompoundDrawables(null, drawable, null, null);
    }

    /**
     * TextView设置顶部图标
     *
     * @param textView 控件.
     * @param resId    图片资源id.
     * @param width    指定图片的宽,单位:px.
     * @param height   指定图片的高,单位:px.
     */
    public static void drawableTop(TextView textView, @DrawableRes int resId, int width, int height) {
        Drawable drawable = textView.getResources().getDrawable(resId);
        drawable.setBounds(0, 0, width, height);
        textView.setCompoundDrawables(null, drawable, null, null);
    }

    /**
     * TextView设置右部图标,图片大小自适应图片.
     *
     * @param textView 控件.
     * @param resId    图片资源id.
     */
    public static void drawableRight(TextView textView, @DrawableRes int resId) {
        Drawable drawable = getDrawable(textView, resId);
        textView.setCompoundDrawables(null, null, drawable, null);
    }

    /**
     * TextView设置右部图标
     *
     * @param textView 控件.
     * @param resId    图片资源id.
     * @param size     指定图片的宽高,单位:px.
     */
    public static void drawableRight(TextView textView, @DrawableRes int resId, int size) {
        Drawable drawable = getDrawable(textView, resId, size, size);
        textView.setCompoundDrawables(null, null, drawable, null);
    }

    /**
     * TextView设置右部图标
     *
     * @param textView 控件.
     * @param resId    图片资源id.
     * @param width    指定图片的宽,单位:px.
     * @param height   指定图片的高,单位:px.
     */
    public static void drawableRight(TextView textView, @DrawableRes int resId, int width, int height) {
        Drawable drawable = getDrawable(textView, resId, width, height);
        textView.setCompoundDrawables(null, null, drawable, null);
    }


    /**
     * TextView设置底部图标,图片大小自适应图片.
     *
     * @param textView 控件.
     * @param resId    图片资源id.
     */
    public static void drawableBottom(TextView textView, @DrawableRes int resId) {
        Drawable drawable = getDrawable(textView, resId);
        textView.setCompoundDrawables(null, null, null, drawable);
    }

    /**
     * TextView设置底部图标
     *
     * @param textView 控件.
     * @param resId    图片资源id.
     * @param size     指定图片的宽高,单位:px.
     */
    public static void drawableBottom(TextView textView, @DrawableRes int resId, int size) {
        Drawable drawable = getDrawable(textView, resId, size, size);
        textView.setCompoundDrawables(null, null, null, drawable);
    }

    /**
     * TextView设置底部图标
     *
     * @param textView 控件.
     * @param resId    图片资源id.
     * @param width    指定图片的宽,单位:px.
     * @param height   指定图片的高,单位:px.
     */
    public static void drawableBottom(TextView textView, @DrawableRes int resId, int width, int height) {
        Drawable drawable = getDrawable(textView, resId, width, height);
        textView.setCompoundDrawables(null, null, null, drawable);
    }


    //----------------------------------------内部使用----------------------------------------------------------------------------------------
    private static Drawable getDrawable(TextView textView, int resId) {
        Drawable drawable = textView.getResources().getDrawable(resId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        return drawable;
    }

    private static Drawable getDrawable(TextView textView, int resId, int width, int height) {
        Drawable drawable = textView.getResources().getDrawable(resId);
        drawable.setBounds(0, 0, width, height);
        return drawable;
    }

}
