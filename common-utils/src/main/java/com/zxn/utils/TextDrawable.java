package com.zxn.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;


/**
 * 根据文本生成Drawable
 * Created by zxn on 2019/5/28.
 */
public class TextDrawable extends Drawable {

    protected String mText;
    protected int mColor;
    protected Paint mPaint;
    protected Rect mTextBound;

    public TextDrawable() {
        super();
        this.mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        this.mPaint.setStrokeWidth(3);
        this.mTextBound = new Rect();
    }


    public TextDrawable(String text, int color, int textSize) {
        this.mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        this.mPaint.setStrokeWidth(3);
        this.mTextBound = new Rect();
        this.mPaint.setColor(color);
        this.mPaint.setTextSize(textSize);
        this.mText = text;
        this.mPaint.getTextBounds(text, 0, text.length(), mTextBound);
    }

    /**
     * 创建一个TextDrawable
     *
     * @param context  Context
     * @param text     展示的文字
     * @param colorId  文字颜色
     * @param textSize 字体大小,单位sp.
     * @return Drawable图片
     */
    public static TextDrawable create(Context context, String text, int colorId, int textSize) {
        TextDrawable drawable = new TextDrawable();
        drawable.setText(text);
        drawable.setColor(UIUtils.getColor(context, colorId));
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        drawable.setTextSize(SizeUtils.sp2px(context, textSize));
        return drawable;
    }

    public void setText(String text) {
        this.mText = text;
        this.mPaint.getTextBounds(text, 0, text.length(), mTextBound);
        invalidateSelf();
    }

    public void setColor(int color) {
        this.mColor = color;
        this.mPaint.setColor(color);
        invalidateSelf();
    }

    public void setTextSize(float textSize) {
        this.mPaint.setTextSize(textSize);
        invalidateSelf();
    }

    @Override
    public void draw(Canvas canvas) {

        Rect bounds = getBounds();

        int count = canvas.save();
        canvas.translate(bounds.left, bounds.top);

        canvas.drawText(mText, bounds.width() / 2 - mTextBound.width() / 2, bounds.height() / 2 - ((mPaint.descent() + mPaint.ascent()) / 2), mPaint);

        canvas.restoreToCount(count);

    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public int getIntrinsicWidth() {
        return mTextBound.width();
    }

    @Override
    public int getIntrinsicHeight() {
        return mTextBound.height();
    }

}
