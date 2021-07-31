package com.example.settingcreatorhelper.util

import android.content.Context
import android.util.TypedValue



internal fun Int.dp2px(context: Context): Int {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), context.resources?.displayMetrics).toInt()
}

internal fun Float.dp2px(context: Context): Int {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, context.resources?.displayMetrics).toInt()
}