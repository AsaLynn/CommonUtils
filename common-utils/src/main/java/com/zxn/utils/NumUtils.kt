package com.zxn.utils

import android.text.TextUtils
import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.NumberFormat

object NumUtils {
    /**
     * 将小数转为百分数,并保留两位小数点.
     *
     * @param number 小数
     * @return 百分数文字(始终带有两位小数)
     */
    fun formatPercent(number: Double): String {
        val df = DecimalFormat("0.00%")
        return df.format(number)
    }
    /**
     * 将小数转为百分数
     *
     * @param number       小数数值
     * @param decimalCount 保留的位数
     * @return  百分数
     */
    /**
     * 将小数转为百分数,并保留两位小数点.
     *
     * @param number 小数
     * @return 百分数文字(小数位数不足2位展示1位, 大于等于2位则展示2位)
     */
    @JvmOverloads
    fun percent(number: Double, decimalCount: Int = 2): String {
        val format = NumberFormat.getPercentInstance()
        format.maximumFractionDigits = decimalCount
        return format.format(number)
    }

    /**
     * 隐藏20位的银行卡号的前面的12位,并从左到右进行4位一分割
     * (d{16})(d{3}):保留后三位,前面的12位替换为*
     * ****************$2:替换格式.
     *
     * @param cardNumber 19位的银行卡号.
     * @return xxxx****xxxx
     */
    fun hideFrontalCardNum(cardNumber: String?): String? {
        var cardNumber = cardNumber
        if (cardNumber == null || cardNumber.length == 0) {
            return ""
        }
        if (cardNumber.trim { it <= ' ' }.length == 19) {
            cardNumber = cardNumber.trim { it <= ' ' }.replace("(\\d{16})(\\d{3})".toRegex(), "****************$2")
            cardNumber = makeInterval(cardNumber)
        }
        return cardNumber
    }

    /**
     * 四舍五入保留两位小数点(整数在后面补.00)
     * numberToTextByTwo
     *
     * @param num 需要处理的数
     * @return  String
     */
    fun formatByTwo(num: Double): String {
        try {
            // 防止不是中文
            val format = DecimalFormat("0.00")
            return format.format(BigDecimal(num))
        } catch (e: Exception) {
        }
        return "0.00"
    }

    /**
     * 隐藏20位的银行卡号的中间12位,并从左到右进行4位一分割
     * (d{4})(d{12})(d{3}):保留前四位,保留后三位,中间12位替换为*
     * $1************$3:替换格式.
     *
     * @param cardNumber 19位的银行卡号.
     * @return xxxx****xxxx
     */
    fun hideCardNum(cardNumber: String?): String? {
        var cardNumber = cardNumber
        if (cardNumber == null || cardNumber.length == 0) {
            return ""
        }
        if (cardNumber.trim { it <= ' ' }.length == 19) {
            cardNumber = cardNumber.trim { it <= ' ' }.replace("(\\d{4})(\\d{12})(\\d{3})".toRegex(), "$1************$3")
            cardNumber = makeInterval(cardNumber)
        }
        return cardNumber
    }

    /**
     * 隐藏手机号码中间四位.
     *
     * @param phoneNumber 手机号.
     * @return 137****1676
     */
    fun hidePhoneNum(phoneNumber: String): String {
        var phoneNumber = phoneNumber
        if (TextUtils.isEmpty(phoneNumber)) {
            phoneNumber = ""
            return phoneNumber
        }
        phoneNumber = phoneNumber.replace("(\\d{3})\\d{4}(\\d{4})".toRegex(), "$1****$2")
        return phoneNumber
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
}