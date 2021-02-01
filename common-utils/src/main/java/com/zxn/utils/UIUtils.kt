package com.zxn.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Point
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.text.Html
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.util.DisplayMetrics
import android.view.Display
import android.view.View
import android.view.WindowManager
import androidx.annotation.*

/**
 * 系统UI操作工具类
 * Created by zxn on 2019/3/6.
 */
class UIUtils {
    /**
     * 获取dimes.xml中dimen的尺寸值。类似于方法
     * [.getDimension]，不同的是返回值是int类型的值
     *
     * @param id 资源id。
     * @return 返回强转成int类型的值。
     */
    fun getDimensionPixelOffset(@DimenRes id: Int): Int {
        isNull
        return context!!.resources.getDimensionPixelOffset(id)
    }

    companion object {
        const val RMB = "¥"
        var BLANK = "&#12288;"

        /**
         * 获取Context
         *
         * @return mContext
         */
        //-------------------------以下方法需初始化init----------------------------->>>
        var context: Context? = null
            private set

        /**
         * 工具类初始化.一般在程序入口初始化一次.
         *
         * @param context 上下文对象
         */
        fun init(context: Context?) {
            Companion.context = context
        }

        val resources: Resources
            get() {
                isNull
                return context!!.resources
            }

        fun getInteger(@IntegerRes id: Int): Int {
            isNull
            return context!!.resources.getInteger(id)
        }

        fun getIntArray(@ArrayRes id: Int): IntArray {
            isNull
            return context!!.resources.getIntArray(id)
        }

        fun getStringArray(@ArrayRes id: Int): Array<String> {
            isNull
            return context!!.resources.getStringArray(id)
        }

        /**
         * 获取color.xml中的颜色
         *
         * @param id 颜色id,例如R.id.c_ffffff
         * @return 颜色值
         */
        fun getColor(@ColorRes id: Int): Int {
            isNull
            return context!!.resources.getColor(id)
        }

        /**
         * Returns a localized formatted string from the application's package's
         * default string table, substituting the format arguments as defined in
         * [java.util.Formatter] and [java.lang.String.format].
         *
         * @param resId      Resource id for the format string
         * @param formatArgs The format arguments that will be used for
         * substitution.
         * @return The string data associated with the resource, formatted and
         * stripped of styled text information.
         */
        fun getString(@StringRes resId: Int, vararg formatArgs: Any?): String {
            isNull
            //getString(@StringRes int resId, Object... formatArgs)
            return context!!.getString(resId, *formatArgs)
        }

        /**
         * Returns a localized string from the application's package's
         * default string table.
         *
         * @param resId Resource id for the string
         * @return The string data associated with the resource, stripped of styled
         * text information.
         */
        fun getString(@StringRes resId: Int): String {
            isNull
            return context!!.getString(resId)
        }
//        //&#12288;"门&#12288;&#12288;店:"
//        /**
//         * 代表一个字宽度空格.
//         *
//         * @param text 内容
//         * @return 带空格的字符串.BLANK
//         */
//        fun getBlankString(text: String?): String {
//            return Html.fromHtml(text).toString()
//        }

        fun toast(msg: String?) {
            isNull
            ZToastUtils.init(context)
            ZToastUtils.toast(msg)
        }

        /**
         * 获取屏幕宽度。
         *
         * @return 屏幕宽度
         */
        val screenWidth: Int
            get() {
                isNull
                val wmManager = context!!.getSystemService(Context.WINDOW_SERVICE) as WindowManager
                return getScreenWidth(wmManager)
            }

        fun getScreenWidth(context: Context): Int {
            val wmManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            return getScreenWidth(wmManager)
        }

        val screenHeight: Int
            get() {
                isNull
                val wmManager = context!!.getSystemService(Context.WINDOW_SERVICE) as WindowManager
                return getScreenHeight(wmManager)
            }

        fun getScreenHeight(context: Context): Int {
            val wmManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            return getScreenHeight(wmManager)
        }

        private fun getScreenHeight(windowManager: WindowManager): Int {
            var heightPixels = 0
            val defaultDisplay = windowManager.defaultDisplay
            if (aboveApiLevel(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
                    && !aboveApiLevel(Build.VERSION_CODES.JELLY_BEAN_MR1)) {
                try {
                    heightPixels = Display::class.java.getMethod("getRawHeight").invoke(defaultDisplay) as Int
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                val realSize = Point()
                defaultDisplay.getRealSize(realSize)
                heightPixels = realSize.y
            } else {
                val metrics = DisplayMetrics()
                defaultDisplay.getMetrics(metrics)
                heightPixels = metrics.heightPixels
            }
            return heightPixels
        }

        /**
         * 获取dimes.xml中dimen的尺寸值。
         *
         * @param id 资源id
         * @return 返回float类型的尺寸值。
         */
        fun getDimension(@DimenRes id: Int): Float {
            isNull
            return context!!.resources.getDimension(id)
        }

        /**
         * dp转px
         *
         * @param dpValue dp值
         * @return px值
         */
        fun dp2px(dpValue: Float): Int {
            isNull
            return dp2px(context, dpValue)
        }

        /**
         * px转dp
         *
         * @param pxValue px值
         * @return dp值
         */
        fun px2dp(pxValue: Float): Int {
            isNull
            return px2dp(context, pxValue)
        }

        /**
         * sp转px
         *
         * @param spValue sp值
         * @return px值
         */
        fun sp2px(spValue: Float): Int {
            isNull
            return sp2px(context, spValue)
        }

        /**
         * px转sp
         *
         * @param pxValue px值
         * @return sp值
         */
        fun px2sp(pxValue: Float): Int {
            isNull
            return px2sp(context, pxValue)
        }

        /**
         * Causes the Runnable r to be added to the message queue, to be run
         * after the specified amount of time elapses.
         *
         * @param r           The Runnable that will be executed.
         * @param delayMillis The delay (in milliseconds) until the Runnable will be executed.
         * @return true:执行成功
         */
        fun postDelayed(r: Runnable?, delayMillis: Long): Boolean {
            val mHandler = Handler(Looper.getMainLooper())
            return mHandler.postDelayed(r!!, delayMillis)
        }

        /**
         * 获取color.xml中的颜色
         *
         * @param view 控件
         * @param id   颜色id,例如R.id.c_ffffff
         * @return 颜色值
         */
        fun getColor(view: View, @ColorRes id: Int): Int {
            return view.resources.getColor(id)
        }
        //<<<-------------------------以下方法需初始化init----------------------------|
        //|-------------------------完全静态方法,无需初始化init----------------------->>>
        /**
         * 去除字符串空格
         *
         * @param text 要处理的字符串.
         * @return 去除空格后的字符串.
         */
        fun trim(text: String?): String {
            return text?.trim { it <= ' ' } ?: ""
        }

        /**
         * Returns true if the string is null or 0-length or 含有空格视为空串.
         *
         * @param str the string to be examined
         * @return true if str is null or zero length
         */
        fun isEmpty(str: CharSequence?): Boolean {
            return str == null || str.length == 0 || str.toString().trim { it <= ' ' }.length == 0
        }

        /**
         * 获取屏幕的宽度。
         *
         * @param windowManager 窗口管理者。
         * @return 宽度
         */
        fun getScreenWidth(windowManager: WindowManager): Int {
            var widthPixels = 0
            val defaultDisplay = windowManager.defaultDisplay
            if (aboveApiLevel(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
                    && !aboveApiLevel(Build.VERSION_CODES.JELLY_BEAN_MR1)) {
                try {
                    widthPixels = Display::class.java.getMethod("getRawWidth").invoke(defaultDisplay) as Int
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                val realSize = Point()
                defaultDisplay.getRealSize(realSize)
                widthPixels = realSize.x
            } else {
                val metrics = DisplayMetrics()
                defaultDisplay.getMetrics(metrics)
                widthPixels = metrics.widthPixels
            }
            return widthPixels
        }

        /**
         * 获取dimes.xml中dimen的尺寸值，类似于方法
         * [.getDimension]，不同的是返回值是int类型的值
         *
         * @param id 资源id。
         * @return 尺寸值
         * @see .getDimension
         *
         * @see .getDimensionPixelOffset
         */
        fun getDimensionPixelSize(@DimenRes id: Int): Int {
            isNull
            return context!!.resources.getDimensionPixelSize(id)
        }

        /**
         * 获取color.xml中的颜色
         *
         * @param context 上下文
         * @param id      颜色id,例如R.id.c_ffffff
         * @return 颜色值
         */
        fun getColor(context: Context, @ColorRes id: Int): Int {
            return context.resources.getColor(id)
        }

        /**
         * dp转px
         *
         * @param context Context
         * @param dpValue dp值
         * @return px值
         */
        fun dp2px(context: Context?, dpValue: Float): Int {
            return SizeUtils.dp2px(context!!, dpValue)
        }

        /**
         * px转dp
         *
         * @param context Context
         * @param pxValue px值
         * @return dp值
         */
        fun px2dp(context: Context?, pxValue: Float): Int {
            return SizeUtils.px2dp(context!!, pxValue)
        }

        /**
         * sp转px
         *
         * @param context Context
         * @param spValue sp值
         * @return px值
         */
        fun sp2px(context: Context?, spValue: Float): Int {
            return SizeUtils.sp2px(context!!, spValue)
        }

        /**
         * px转sp
         *
         * @param context Context
         * @param pxValue px值
         * @return sp值
         */
        fun px2sp(context: Context?, pxValue: Float): Int {
            return SizeUtils.px2sp(context!!, pxValue)
        }

        /**
         * 将数字按照4位进行分割.
         *
         * @param numText 分割前的文字
         * @return 分割后的文字.
         */
        fun makeInterval(numText: String?): String {
            if (numText == null || numText.length == 0) {
                return ""
            }
            val sb = StringBuilder(numText)
            val length = numText.length / 4 + numText.length
            for (i in 0 until length) {
                if (i % 5 == 0) {
                    sb.insert(i, " ")
                }
            }
            sb.deleteCharAt(0)
            return sb.toString()
        }

        /**
         * 根据view创建bitmap.
         *
         * @param view  View
         * @return  Bitmap
         */
        fun createBitmap(view: View): Bitmap {
            val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
            view.draw(Canvas(bitmap))
            return bitmap
        }

        /**
         * Sets the current primary clip on the clipboard.  This is the clip that
         * is involved in normal cut and paste operations.
         *
         * @param context  context
         * @param copeText copeText The clipped data item to set.
         */
        fun setPrimaryClip(context: Context, copeText: String?) {
            val cmb = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            cmb.setPrimaryClip(ClipData.newPlainText(null, copeText))
        }

        private fun aboveApiLevel(sdkInt: Int): Boolean {
            return apiLevel >= sdkInt
        }

        private val apiLevel: Int
            get() = Build.VERSION.SDK_INT

        /**
         * mContext 是否为null.
         */
        private val isNull: Unit
            get() {
                if (null == context) {
                    throw RuntimeException("null == mContext,should call init()")
                }
            }

        /**
         * 用于TextView,出现两种颜色的情况.
         *
         * @param text        要展示的完整字符串
         * @param colorId     默认颜色
         * @param deepColorId 要加深高亮的颜色
         * @param startIndex  加深色的起始索引
         * @param endIndex    加深色的结束索引
         * @return SpannableString
         */
        fun spannableString(text: String, @ColorRes colorId: Int, @ColorRes deepColorId: Int, startIndex: Int, endIndex: Int): SpannableString {
            isNull
            val spannableString = SpannableString(text)
            if (startIndex > 1) {
                spannableString.setSpan(ForegroundColorSpan(getColor(colorId)), 1, startIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                spannableString.setSpan(ForegroundColorSpan(getColor(deepColorId)), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                spannableString.setSpan(ForegroundColorSpan(getColor(colorId)), endIndex, text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            } else {
                spannableString.setSpan(ForegroundColorSpan(getColor(deepColorId)), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                spannableString.setSpan(ForegroundColorSpan(getColor(colorId)), endIndex, text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
            return spannableString
        }
    }
}