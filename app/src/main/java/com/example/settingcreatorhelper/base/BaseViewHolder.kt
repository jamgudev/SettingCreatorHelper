package com.example.settingcreatorhelper.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.settingcreatorhelper.databinding.SettingCheckBoxItemLayoutBinding
import com.example.settingcreatorhelper.databinding.SettingNormalItemLayoutBinding
import com.example.settingcreatorhelper.databinding.SettingTextTitleItemLayoutBinding

class NormalViewHolder(binding: SettingNormalItemLayoutBinding): BaseViewHolder<SettingNormalItemLayoutBinding>(binding)

class CheckBoxViewHolder(binding: SettingCheckBoxItemLayoutBinding): BaseViewHolder<SettingCheckBoxItemLayoutBinding>(binding)

class TextTitleViewHolder(binding: SettingTextTitleItemLayoutBinding): BaseViewHolder<SettingTextTitleItemLayoutBinding>(binding)

open class BaseViewHolder<T: ViewBinding>(val binding: T): RecyclerView.ViewHolder(binding.root)