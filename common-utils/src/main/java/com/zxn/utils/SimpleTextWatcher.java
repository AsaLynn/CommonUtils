package com.zxn.utils;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * 这样可以只重写需要的方法
 * @author user
 *
 */
public class SimpleTextWatcher implements TextWatcher {

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {

	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {

	}

	@Override
	public void afterTextChanged(Editable s) {

	}

}
