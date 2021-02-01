package com.zxn.utils

import android.text.Editable
import android.text.TextWatcher

/**
 * 这样可以只重写需要的方法
 * @author user
 */
class SimpleTextWatcher : TextWatcher {
    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    override fun afterTextChanged(s: Editable) {}
}