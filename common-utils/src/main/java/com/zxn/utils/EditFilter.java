package com.zxn.utils;

import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.NumberKeyListener;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * EditText输入框过滤器.
 * Created by zxn on 2019/9/24.
 */
public class EditFilter {
    /**
     * 保留2位小数  开头为0 自动加入小数
     *
     * @param editText EditText
     */
    public static void filter(EditText editText) {
        editText.setFilters(new InputFilter[]{new InputFilter() {
            int decimalNumber = 2;//小数点后保留位数

            @Override
            //source:即将输入的内容 dest：原来输入的内容
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                String sourceContent = source.toString();
                String lastInputContent = dest.toString();

                //验证删除等按键
                if (TextUtils.isEmpty(sourceContent)) {
                    return "";
                }
                //以小数点"."开头，默认为设置为“0.”开头
                if (sourceContent.equals(".") && lastInputContent.length() == 0) {
                    return "0.";
                }
                //输入“0”，默认设置为以"0."开头
                if (sourceContent.equals("0") && lastInputContent.length() == 0) {
                    return "0.";
                }
                //小数点后保留两位
                if (lastInputContent.contains(".")) {
                    int index = lastInputContent.indexOf(".");
                    if (dend - index >= decimalNumber + 1) {
                        return "";
                    }
                }else {
                    if (lastInputContent.length() >= 8) {
                        return "";
                    }
                }

                return null;
            }
        }});

    }

    /**
     * 设置保留 num 位小数
     *
     * @param editText EditText
     * @param num      小数位数
     */
    public static void filter(EditText editText, final int num) {
        editText.setFilters(new InputFilter[]{new InputFilter() {
            int decimalNumber = num;//小数点后保留位数

            @Override
            //source:即将输入的内容 dest：原来输入的内容
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                String sourceContent = source.toString();
                String lastInputContent = dest.toString();

                //验证删除等按键
                if (TextUtils.isEmpty(sourceContent)) {
                    return "";
                }
                //以小数点"."开头，默认为设置为“0.”开头
                if (sourceContent.equals(".") && lastInputContent.length() == 0) {
                    return "0.";
                }
                //输入“0”，默认设置为以"0."开头
                if (sourceContent.equals("0") && lastInputContent.length() == 0) {
                    return "0.";
                }
                //小数点后保留两位
                if (lastInputContent.contains(".")) {
                    int index = lastInputContent.indexOf(".");
                    if (dend - index >= decimalNumber + 1) {
                        return "";
                    }
                }
                return null;
            }
        }});

    }

    /**
     * 整型数字开头为0  输入其他时0自动删除   在EditText监听中调取此方法即可
     * @param editText  EditText
     * @param editable  Editable
     */
    public static void onAfterTextChanged(final EditText editText, Editable editable) {
        if (TextUtils.isEmpty(editable.toString())) {
            editText.setText("0");
            editText.setSelection(1);
        }
        if (editable.toString().length() >= 2 && editable.toString().startsWith("0")) {
            editText.setText(editable.toString().substring(1));
            editText.setSelection(editable.toString().length() - 1);
        }
    }

    /**
     * EditTextView光标默认在最后
     *
     * @param editText EditText
     * @param length   int
     */
    public static void selection(EditText editText, int length) {
        editText.setSelection(length);
    }


    /**
     * 默认不获取焦点
     *
     * @param editText               EditText
     * @param isFocusable            boolean
     * @param isFocusableInTouchMode boolean
     */
    public static void noFocusable(EditText editText, boolean isFocusable, boolean isFocusableInTouchMode) {
        editText.setFocusable(isFocusable);
        editText.setFocusableInTouchMode(isFocusableInTouchMode);
    }

    /**
     * 只能输入整数
     *
     * @param editText EditText
     */
    public static void integerNum(EditText editText) {
        editText.setKeyListener(new NumberKeyListener() {
            @Override
            protected char[] getAcceptedChars() {
                char[] numberChars = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
                return numberChars;
            }

            @Override
            public int getInputType() {
                return android.text.InputType.TYPE_CLASS_PHONE;
            }
        });
    }

    /**
     * 禁止输入表情符号
     * @param editText  EditText
     */
    public static void noExpression(final EditText editText) {
        InputFilter inputFilter = new InputFilter() {
            Pattern emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
                    Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);

            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                Matcher emojiMatcher = emoji.matcher(source);
                if (emojiMatcher.find()) {
                    Toast.makeText(editText.getContext(), "不支持输入表情", Toast.LENGTH_SHORT).show();
                    return "";
                }
                return null;
            }
        };
        //给editText设置filter
        editText.setFilters(new InputFilter[]{inputFilter, new InputFilter.LengthFilter(12)});
    }

    /**
     * 这样屏蔽的表情符号肯定还是不能够百分之百的屏蔽完全的。
     * 所以最后我们只能选择让用户填写英文，汉字，和数字了。
     * 下面是过滤的代码
     */

//    InputFilter inputFilter=new InputFilter() {
//
//        Pattern pattern = Pattern.compile("[^a-zA-Z0-9\\u4E00-\\u9FA5_]");
//        @Override
//        public CharSequence filter(CharSequence charSequence, int i, int i1, Spanned spanned, int i2, int i3) {
//            Matcher matcher=  pattern.matcher(charSequence);
//            if(!matcher.find()){
//                return null;
//            }else{
//                MyToast.showText("只能输入汉字,英文，数字");
//                return "";
//            }
//
//        }
//    };


    /**
     * 设置去除小数点后多余的0
     * @param s String
     * @return  String
     */
    public static String subZeroAndDot(String s) {
        if (s.indexOf(".") > 0) {
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;
    }

}
