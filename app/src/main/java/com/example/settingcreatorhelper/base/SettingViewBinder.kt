package com.example.settingcreatorhelper.base

import androidx.recyclerview.widget.RecyclerView

typealias Binder = (RecyclerView.ViewHolder, Int) -> Unit

class SettingViewBinder(val layoutId: Int, val binder: Binder)