package com.zcommon.lib;

public class TextNumUtils {

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
