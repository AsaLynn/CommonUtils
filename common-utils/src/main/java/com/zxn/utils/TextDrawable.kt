package com.zxn.utils

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable

/**
 * 根据文本生成Drawable
 * Created by zxn on 2019/5/28.
 */
class TextDrawable : Drawable {
    protected var mText: String? = null
    protected var mColor = 0
    protected var mPaint: Paint
    protected var mTextBound: Rect

    constructor() : super() {
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mPaint.strokeWidth = 3f
        mTextBound = Rect()
    }

    constructor(text: String, color: Int, textSize: Int) {
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mPaint.strokeWidth = 3f
        mTextBound = Rect()
        mPaint.color = color
        mPaint.textSize = textSize.toFloat()
        mText = text
        mPaint.getTextBounds(text, 0, text.length, mTextBound)
    }

    fun setText(text: String) {
        mText = text
        mPaint.getTextBounds(text, 0, text.length, mTextBound)
        invalidateSelf()
    }

    fun setColor(color: Int) {
        mColor = color
        mPaint.color = color
        invalidateSelf()
    }

    fun setTextSize(textSize: Float) {
        mPaint.textSize = textSize
        invalidateSelf()
    }

    override fun draw(canvas: Canvas) {
        val bounds = bounds
        val count = canvas.save()
        canvas.translate(bounds.left.toFloat(), bounds.top.toFloat())
        canvas.drawText(mText!!, bounds.width() / 2 - mTextBound.width() / 2.toFloat(), bounds.height() / 2 - (mPaint.descent() + mPaint.ascent()) / 2, mPaint)
        canvas.restoreToCount(count)
    }

    override fun setAlpha(alpha: Int) {
        mPaint.alpha = alpha
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        mPaint.colorFilter = colorFilter
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }

    override fun getIntrinsicWidth(): Int {
        return mTextBound.width()
    }

    override fun getIntrinsicHeight(): Int {
        return mTextBound.height()
    }

    companion object {
        /**
         * 创建一个TextDrawable
         *
         * @param context  Context
         * @param text     展示的文字
         * @param colorId  文字颜色
         * @param textSize 字体大小,单位sp.
         * @return Drawable图片
         */
        fun create(context: Context, text: String, colorId: Int, textSize: Int): TextDrawable {
            val drawable = TextDrawable()
            drawable.setText(text)
            drawable.setColor(context.resources.getColor(colorId))
            drawable.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight)
            drawable.setTextSize(SizeUtils.sp2px(context, textSize.toFloat()).toFloat())
            return drawable
        }
    }
}