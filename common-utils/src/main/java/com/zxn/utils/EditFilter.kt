package com.zxn.utils

import android.text.*
import android.text.InputFilter.LengthFilter
import android.text.method.NumberKeyListener
import android.widget.EditText
import android.widget.Toast
import java.util.regex.Pattern

/**
 * EditText输入框过滤器.
 * Created by zxn on 2019/9/24.
 */
object EditFilter {
    /**
     * 保留2位小数  开头为0 自动加入小数
     *
     * @param editText EditText
     */
    fun filter(editText: EditText) {
        editText.filters = arrayOf<InputFilter>(object : InputFilter {
            var decimalNumber = 2 //小数点后保留位数

            //source:即将输入的内容 dest：原来输入的内容
            override fun filter(source: CharSequence, start: Int, end: Int, dest: Spanned, dstart: Int, dend: Int): CharSequence ?{
                val sourceContent = source.toString()
                val lastInputContent = dest.toString()

                //验证删除等按键
                if (TextUtils.isEmpty(sourceContent)) {
                    return ""
                }
                //以小数点"."开头，默认为设置为“0.”开头
                if (sourceContent == "." && lastInputContent.length == 0) {
                    return "0."
                }
                //输入“0”，默认设置为以"0."开头
                if (sourceContent == "0" && lastInputContent.length == 0) {
                    return "0."
                }
                //小数点后保留两位
                if (lastInputContent.contains(".")) {
                    val index = lastInputContent.indexOf(".")
                    if (dend - index >= decimalNumber + 1) {
                        return ""
                    }
                } else {
                    if (lastInputContent.length >= 8) {
                        return ""
                    }
                }
                return null
            }
        })
    }

    /**
     * 设置保留 num 位小数
     *
     * @param editText EditText
     * @param num      小数位数
     */
    fun filter(editText: EditText, num: Int) {
        editText.filters = arrayOf<InputFilter>(object : InputFilter {
            var decimalNumber = num //小数点后保留位数

            //source:即将输入的内容 dest：原来输入的内容
            override fun filter(source: CharSequence, start: Int, end: Int, dest: Spanned, dstart: Int, dend: Int): CharSequence ?{
                val sourceContent = source.toString()
                val lastInputContent = dest.toString()

                //验证删除等按键
                if (TextUtils.isEmpty(sourceContent)) {
                    return ""
                }
                //以小数点"."开头，默认为设置为“0.”开头
                if (sourceContent == "." && lastInputContent.length == 0) {
                    return "0."
                }
                //输入“0”，默认设置为以"0."开头
                if (sourceContent == "0" && lastInputContent.length == 0) {
                    return "0."
                }
                //小数点后保留两位
                if (lastInputContent.contains(".")) {
                    val index = lastInputContent.indexOf(".")
                    if (dend - index >= decimalNumber + 1) {
                        return ""
                    }
                }
                return null
            }
        })
    }

    /**
     * 整型数字开头为0  输入其他时0自动删除   在EditText监听中调取此方法即可
     * @param editText  EditText
     * @param editable  Editable
     */
    fun onAfterTextChanged(editText: EditText, editable: Editable) {
        if (TextUtils.isEmpty(editable.toString())) {
            editText.setText("0")
            editText.setSelection(1)
        }
        if (editable.toString().length >= 2 && editable.toString().startsWith("0")) {
            editText.setText(editable.toString().substring(1))
            editText.setSelection(editable.toString().length - 1)
        }
    }

    /**
     * EditTextView光标默认在最后
     *
     * @param editText EditText
     * @param length   int
     */
    fun selection(editText: EditText, length: Int) {
        editText.setSelection(length)
    }

    /**
     * 默认不获取焦点
     *
     * @param editText               EditText
     * @param isFocusable            boolean
     * @param isFocusableInTouchMode boolean
     */
    fun noFocusable(editText: EditText, isFocusable: Boolean, isFocusableInTouchMode: Boolean) {
        editText.isFocusable = isFocusable
        editText.isFocusableInTouchMode = isFocusableInTouchMode
    }

    /**
     * 只能输入整数
     *
     * @param editText EditText
     */
    fun integerNum(editText: EditText) {
        editText.keyListener = object : NumberKeyListener() {
            override fun getAcceptedChars(): CharArray {
                return charArrayOf('1', '2', '3', '4', '5', '6', '7', '8', '9', '0')
            }

            override fun getInputType(): Int {
                return InputType.TYPE_CLASS_PHONE
            }
        }
    }

    /**
     * 禁止输入表情符号
     * @param editText  EditText
     */
    fun noExpression(editText: EditText) {
        val inputFilter: InputFilter = object : InputFilter {
            var emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
                    Pattern.UNICODE_CASE or Pattern.CASE_INSENSITIVE)

            override fun filter(source: CharSequence, start: Int, end: Int, dest: Spanned, dstart: Int, dend: Int): CharSequence ?{
                val emojiMatcher = emoji.matcher(source)
                if (emojiMatcher.find()) {
                    Toast.makeText(editText.context, "不支持输入表情", Toast.LENGTH_SHORT).show()
                    return ""
                }
                return null
            }
        }
        //给editText设置filter
        editText.filters = arrayOf(inputFilter, LengthFilter(12))
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
    fun subZeroAndDot(s: String): String {
        var s = s
        if (s.indexOf(".") > 0) {
            s = s.replace("0+?$".toRegex(), "") //去掉多余的0
            s = s.replace("[.]$".toRegex(), "") //如最后一位是.则去掉
        }
        return s
    }
}