package com.zxn.utils;

import android.text.TextUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class NumUtils {

    /**
     * 将小数转为百分数,并保留两位小数点.
     *
     * @param number 小数
     * @return 百分数文字(始终带有两位小数)
     */
    public static String formatPercent(double number) {
        DecimalFormat df = new DecimalFormat("0.00%");
        return df.format(number);
    }

    /**
     * 将小数转为百分数,并保留两位小数点.
     *
     * @param number 小数
     * @return 百分数文字(小数位数不足2位展示1位, 大于等于2位则展示2位)
     */
    public static String percent(double number) {
        return percent(number, 2);
    }

    /**
     * 将小数转为百分数
     *
     * @param number       小数数值
     * @param decimalCount 保留的位数
     * @return
     */
    public static String percent(double number, int decimalCount) {
        NumberFormat format = NumberFormat.getPercentInstance();
        format.setMaximumFractionDigits(decimalCount);
        String numberText = format.format(number);
        return numberText;
    }

    /**
     * 隐藏20位的银行卡号的前面的12位,并从左到右进行4位一分割
     * (d{16})(d{3}):保留后三位,前面的12位替换为*
     * ****************$2:替换格式.
     *
     * @param cardNumber 19位的银行卡号.
     * @return xxxx****xxxx
     */
    public static String hideFrontalCardNum(String cardNumber) {
        if (cardNumber == null || cardNumber.length() == 0) {
            return "";
        }
        if (cardNumber.trim().length() == 19) {
            cardNumber = cardNumber.trim().replaceAll("(\\d{16})(\\d{3})", "****************$2");
            cardNumber = makeInterval(cardNumber);
        }
        return cardNumber;
    }

    /**
     * 四舍五入保留两位小数点(整数在后面补.00)
     * numberToTextByTwo
     *
     * @param num 需要处理的数
     * @return  String
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
