package com.zxn.utils;

import android.text.TextUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class NumUtils {

    /**
     * 四舍五入保留两位小数点(整数在后面补.00)
     * numberToTextByTwo
     *
     * @param num 需要处理的数
     * @return
     */
    public static String formatByTwo(double num) {
        try {
            // 防止不是中文
            DecimalFormat format = new DecimalFormat("0.00");
            return format.format(new BigDecimal(num));
        } catch (Exception e) {
        }
        return "0.00";
    }

    /**
     * 隐藏20位的银行卡号的中间12位,并从左到右进行4位一分割
     * (d{4})(d{12})(d{3}):保留前四位,保留后三位,中间12位替换为*
     * $1************$3:替换格式.
     *
     * @param cardNumber 19位的银行卡号.
     * @return xxxx****xxxx
     */
    public static String hideCardNum(String cardNumber) {
        if (cardNumber == null || cardNumber.length() == 0) {
            return "";
        }
        if (cardNumber.trim().length() == 19) {
            cardNumber = cardNumber.trim().replaceAll("(\\d{4})(\\d{12})(\\d{3})", "$1************$3");
            cardNumber = makeInterval(cardNumber);
        }
        return cardNumber;
    }

    /**
     * 隐藏手机号码中间四位.
     *
     * @param phoneNumber 手机号.
     * @return 137****1676
     */
    public static String hidePhoneNum(String phoneNumber) {
        if (TextUtils.isEmpty(phoneNumber)) {
            phoneNumber = "";
            return phoneNumber;
        }
        phoneNumber = phoneNumber.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        return phoneNumber;
    }

    /**
     * 将数字按照4位进行分割.
     *
     * @param numText 分割前的文字
     * @return 分割后的文字.
     */
    public static String makeInterval(String numText) {
        if (numText == null || numText.length() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(numText);
        int length = numText.length() / 4 + numText.length();

        for (int i = 0; i < length; i++) {
            if (i % 5 == 0) {
                sb.insert(i, " ");
            }
        }
        sb.deleteCharAt(0);
        return sb.toString();
    }
}
