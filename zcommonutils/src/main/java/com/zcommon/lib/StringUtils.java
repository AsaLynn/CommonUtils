package com.zcommon.lib;

import android.text.TextUtils;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StringUtils {

	public static Double StringToDouble(String str) {
		if (str == null || "".equals(str) || "null".equals(str) || "Null".equals(str) || "NULL".equals(str)) {
			return (double) 0;
		}
		return Double.valueOf(str);
	}

	/**
	 * 字符转浮点
	 *
	 * @param str
	 * @return
	 */
	public static Float StringToFloat(String str) {
		if (str == null || "".equals(str) || "null".equals(str) || "Null".equals(str) || "NULL".equals(str)) {
			return 0.0f;
		}
		return Float.valueOf(str);
	}

	/**
	 * 字符转浮点
	 *
	 * @param str
	 * @return
	 */
	public static int StringToInt(String str) {
		if (str == null || "".equals(str) || "null".equals(str) || "Null".equals(str) || "NULL".equals(str)) {
			return 0;
		}
		return Integer.valueOf(str);
	}



	/**
	 * 判断手机号码是否正确
	 *
	 * @param mobiles
	 * @return
	 */
	public static boolean isMobileNO(String mobiles) {
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);

		return m.matches();
	}

	/**
	 * 判断密码是否是数字和字母
	 *
	 */
	public static boolean isPwd(String pwd) {
		Pattern p = Pattern.compile("^(?=.*\\d.*)(?=.*[a-zA-Z].*).{6,}$");
		Matcher m = p.matcher(pwd);

		return m.matches();
	}

	/**
	 * 判断手机号码是否是13位
	 *
	 * @param mobiles
	 * @return
	 */
	public static boolean isMobile13(String mobiles) {
		if (mobiles != null && mobiles.length() == 11) {
			return true;
		}

		return false;
	}

	/**
	 * 判断email格式是否正确
	 *
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		String str = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(email);

		return m.matches();
	}

	private static final String splitStr = " ";

	private static String getNumberString() {
		StringBuffer buf = new StringBuffer();
		for (int i = 1; i < 10; i++) {
			buf.append(String.valueOf(i));
			buf.append(splitStr);
		}
		return buf.toString();
	}

	private static String getUppercase() {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < 26; i++) {
			buf.append(String.valueOf((char) ('A' + i)));
			buf.append(splitStr);
		}
		return buf.toString();
	}

	private static String getLowercase() {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < 26; i++) {
			buf.append(String.valueOf((char) ('a' + i)));
			buf.append(splitStr);
		}
		return buf.toString();
	}

	private static String getSpecialString() {
		String str = "~@#$%^&*()_+|\\=-`";
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			buf.append(str.substring(i, i + 1));
			buf.append(splitStr);
		}
		return buf.toString();
	}

	private static String getString(String type) {
		StringBuffer pstr = new StringBuffer();
		if (type.length() > 0) {
			if (type.indexOf('i') != -1)
				pstr.append(getNumberString());
			if (type.indexOf('l') != -1)
				pstr.append(getLowercase());
			if (type.indexOf('u') != -1)
				pstr.append(getUppercase());
			if (type.indexOf('s') != -1)
				pstr.append(getSpecialString());

		}
		return pstr.toString();
	}

	/**
	 * 中文转unicode
	 * 
	 * @param str
	 * @return 反回unicode编码
	 */
	public static String chinaToUnicode(String str) {
		String result = "";
		for (int i = 0; i < str.length(); i++) {
			int chr1 = (char) str.charAt(i);
			result += "\\u" + Integer.toHexString(chr1);
		}
		return result;
	}

	/**
	 * unicode转中�?
	 * 
	 * @return 中文
	 */
	public static String unicodeToChinese(String dataStr) {

		int start = 0;
		int end = 0;
		final StringBuffer buffer = new StringBuffer();
		while (start > -1) {
			end = dataStr.indexOf("\\u", start + 2);
			String charStr = "0";
			if (end == -1) {
				charStr = dataStr.substring(start + 2, dataStr.length());
			} else {
				charStr = dataStr.substring(start + 2, end);
			}
			char letter = (char) Integer.parseInt(charStr, 16);
			buffer.append(Character.valueOf(letter).toString());
			start = end;
		}
		return buffer.toString();

	}


	/**
	 * 判断字符串是否包含中文
	 * @param str str
	 * @return boolean
	 */
	public static boolean isContainChinese(String str) {

		Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
		Matcher m = p.matcher(str);
		if (m.find()) {
			return true;
		}
		return false;
	}

	/**
	 * 判断字符串中是否同时包含字母和数字
	 * 
	 * @param str str
	 * @return boolean
	 */
	public static boolean isContainsNumberAndLatter(String str) {
		if (TextUtils.isEmpty(str)) {
			return false;
		}
		// 是否包含数字
		boolean isDigit = false;
		// 表示是否包含字母
		boolean isLetter = false;
		for (int i = 0; i < str.length(); i++) { // 循环遍历字符串
			if (Character.isDigit(str.charAt(i))) { // 用char包装类中的判断数字的方法判断每一个字符
				isDigit = true;
			}
			if (Character.isLetter(str.charAt(i))) { // 用char包装类中的判断字母的方法判断每一个字符
				isLetter = true;
			}
		}

		if (isDigit && isLetter) {
			return true;
		}
		return false;
	}

	/**
	 * 半角转换为全角
	 * 
	 * @param input input
	 * @return  String
	 */
	public static String toDBC(String input) {
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == 12288) {
				c[i] = (char) 32;
				continue;
			}
			if (c[i] > 65280 && c[i] < 65375)
				c[i] = (char) (c[i] - 65248);
		}
		return new String(c);
	}

	/**
	 * 将指定字符串的某些字符替换成* 例如130****6368
	 * 
	 * @param source
	 *            原字符
	 * @param startIndex
	 *            开始索引
	 * @param count
	 *            个数
	 * @return String
	 */
	public static String getSafeText(String source, int startIndex, int count) {
		if (TextUtils.isEmpty(source)) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < source.length(); i++) {
			if (i > (startIndex - 1) && i < source.length() - count) {
				sb.append("*");
			} else {
				sb.append(source.charAt(i));
			}
		}
		return sb.toString();
	}

	/**
	 * 银行卡字符串添加空格
	 * 
	 * @param source
	 * @return
	 */
	public static String getBlankString(String source) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < source.length(); i++) {
			if (i != 0 && i % 4 == 0) {
				stringBuilder.append(" ");
			}
			stringBuilder.append(source.charAt(i));
		}
		return stringBuilder.toString();
	}
	
	/**
	 * 银行卡字符串添加空格并且前四位后三位显示 中间*号
	 * 
	 * @param source
	 * @return
	 */
	public static String getBlankSymbolString(String source) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < source.length(); i++) {
			if (i != 0 && i % 4 == 0) {
				stringBuilder.append(" ");
			}
			if(i>=4 && i<source.length()-3){
				stringBuilder.append("*");	
			}else{
				stringBuilder.append(source.charAt(i));
			}
		}
		return stringBuilder.toString();
	}

	/**
	 * 保留两位小数
	 * 
	 * @param num
	 * @return
	 */
	public static String getNumberFormat(Double num) {
		return new DecimalFormat("0.00").format(num == null ? 0 : num);
	}
}
