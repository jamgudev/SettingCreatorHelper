package com.jamgu.settingpie.base

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jamgu.settingpie.databinding.SettingCheckBoxItemLayoutBinding
import com.jamgu.settingpie.databinding.SettingNormalItemLayoutBinding
import com.jamgu.settingpie.databinding.SettingTextTitleItemLayoutBinding
import com.jamgu.settingpie.model.SetItem
import com.jamgu.settingpie.model.ViewType.VIEW_TYPE_CHECKBOX
import com.jamgu.settingpie.model.ViewType.VIEW_TYPE_CUSTOM
import com.jamgu.settingpie.model.ViewType.VIEW_TYPE_NORMAL
import com.jamgu.settingpie.model.ViewType.VIEW_TYPE_NOT_FOUNT
import com.jamgu.settingpie.model.ViewType.VIEW_TYPE_TEXT_TITLE

class SettingAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mSettingData: ArrayList<SetItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_NORMAL -> {
                // NORMAL
                val binding = SettingNormalItemLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
                NormalViewHolder(binding)
            }
            VIEW_TYPE_CHECKBOX -> {
                val binding = SettingCheckBoxItemLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                CheckBoxViewHolder(binding)
            }
            VIEW_TYPE_TEXT_TITLE -> {
                val binding = SettingTextTitleItemLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                TextTitleViewHolder(binding)
            }
            else -> {
                // 自定义布局，使用viewType作为layoutId
                if (viewType == VIEW_TYPE_NOT_FOUNT)
                    throw RuntimeException("未设置viewBinder属性，或没有在viewBinder中定义layoutId")

                val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
                object : RecyclerView.ViewHolder(view) {}
            }
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val setItem = mSettingData[position]

        // layout
        initialLayoutProp(setItem.layoutProp, holder)

        // padding
        initialPaddingProp(setItem.paddingProp, holder.itemView)


        // 自定义布局
        if (setItem.viewType == VIEW_TYPE_CUSTOM && setItem.viewBinder != null) {
            setItem.viewBinder.binder?.invoke(holder, position)
            return
        }

        // 赋值
        when (holder) {
            is NormalViewHolder -> {
                bindNormalViewHolder(holder.binding, setItem)
            }
            is CheckBoxViewHolder -> {
                bindCheckBoxViewHolder(holder.binding, setItem)
            }
            is TextTitleViewHolder -> {
                bindTextTitleViewHolder(holder.binding, setItem)
            }
        }
    }

    private fun bindTextTitleViewHolder(binding: SettingTextTitleItemLayoutBinding, item: SetItem) {
        initialTextData(binding.settingTitle, item.mainTextProp)
    }

    private fun bindCheckBoxViewHolder(binding: SettingCheckBoxItemLayoutBinding, setItem: SetItem) {
        initialTextData(binding.settingName, setItem.mainTextProp)

        // check box setting
        initialCheckBoxData(binding.settingCheckbox, setItem.checkBoxProp)
    }

    private fun bindNormalViewHolder(binding: SettingNormalItemLayoutBinding, setItem: SetItem) {

        initialTextData(binding.settingName, setItem.mainTextProp)

        initialTextData(binding.settingHint, setItem.hintTextProp)

        initialIconData(binding.settingMainIcon, setItem.mainIconProp)
        initialIconData(binding.settingHintIcon, setItem.hintIconProp)

        // arrow
        initialArrowProp(setItem.arrowProp, binding.settingArrow)
    }

    override fun getItemCount(): Int = mSettingData.size

    override fun getItemViewType(position: Int): Int {
        val setItem = mSettingData[position]
        return if (setItem.viewType == VIEW_TYPE_CUSTOM) {
            setItem.viewBinder?.layoutId ?: VIEW_TYPE_NOT_FOUNT
        } else setItem.viewType ?: VIEW_TYPE_NOT_FOUNT
    }

    fun setData(data: ArrayList<SetItem>) {
        this.mSettingData = data
        notifyDataSetChanged()
    }

}