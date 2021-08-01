package com.jamgu.settingpie.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.jamgu.settingpie.databinding.SettingCheckBoxItemLayoutBinding
import com.jamgu.settingpie.databinding.SettingNormalItemLayoutBinding
import com.jamgu.settingpie.databinding.SettingTextTitleItemLayoutBinding

class NormalViewHolder(binding: SettingNormalItemLayoutBinding) :
    BaseViewHolder<SettingNormalItemLayoutBinding>(binding)

class CheckBoxViewHolder(binding: SettingCheckBoxItemLayoutBinding) :
    BaseViewHolder<SettingCheckBoxItemLayoutBinding>(binding)

class TextTitleViewHolder(binding: SettingTextTitleItemLayoutBinding) :
    BaseViewHolder<SettingTextTitleItemLayoutBinding>(binding, false)

open class BaseViewHolder<T : ViewBinding>(val binding: T, val clickable: Boolean = true) :
    RecyclerView.ViewHolder(binding.root)