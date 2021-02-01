package com.zxn.utils

import android.view.View

val View.isVisible: Boolean
    get() = visibility == View.VISIBLE
