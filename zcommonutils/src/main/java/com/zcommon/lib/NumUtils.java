package com.zcommon.lib;

import android.text.TextUtils;

public class NumUtils {

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
