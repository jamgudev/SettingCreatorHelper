package com.example.settingcreatorhelper.base

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.settingcreatorhelper.databinding.SettingCheckBoxItemLayoutBinding
import com.example.settingcreatorhelper.databinding.SettingNormalItemLayoutBinding
import com.example.settingcreatorhelper.model.CheckBoxProp
import com.example.settingcreatorhelper.model.IconProp
import com.example.settingcreatorhelper.model.SetItem
import com.example.settingcreatorhelper.model.SettingConstants.VIEW_TYPE_CHECKBOX
import com.example.settingcreatorhelper.model.SettingConstants.VIEW_TYPE_CUSTOM
import com.example.settingcreatorhelper.model.SettingConstants.VIEW_TYPE_NORMAL
import com.example.settingcreatorhelper.model.SettingConstants.VIEW_TYPE_NOT_FOUNT
import com.example.settingcreatorhelper.model.TextProp
import com.example.settingcreatorhelper.util.dp2px

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

        // click
        setItem.clickProp?.apply {
            holder.itemView.setBackgroundResource(clickDrawableRes)
            holder.itemView.setOnClickListener(to)
        }

        // 自定义布局
        if (setItem.viewType == VIEW_TYPE_CUSTOM && setItem.viewBinder != null) {
            setItem.viewBinder.binder.invoke(holder, position)
            return
        }

        // padding
        rePadding(setItem, holder.itemView)

        // 赋值
        when (holder) {
            is NormalViewHolder -> {
                bindNormalViewHolder(holder.binding, setItem)
            }
            is CheckBoxViewHolder -> {
                bindCheckBoxViewHolder(holder.binding, setItem)
            }
        }
    }

    private fun bindCheckBoxViewHolder(binding: SettingCheckBoxItemLayoutBinding, setItem: SetItem) {
        quickInitialTextData(binding.settingName, setItem.mainTextProp)

        // check box setting
        quickInitialCheckBoxData(binding.settingCheckbox, setItem.checkBoxProp)
    }

    private fun bindNormalViewHolder(binding: SettingNormalItemLayoutBinding, setItem: SetItem) {

        quickInitialTextData(binding.settingName, setItem.mainTextProp)

        quickInitialTextData(binding.settingHint, setItem.hintTextProp)

        quickInitialIconData(binding.settingMainIcon, setItem.mainIconProp)
        quickInitialIconData(binding.settingHintIcon, setItem.hintIconProp)
//        quickInitialIconData(binding.settingHintIcon, setItem.hintIconProp)

    }

    private fun rePadding(setItem: SetItem, v: View) {
        setItem.apply {
            val context = v.context
            v.setPadding(
                paddingLeft.dp2px(context), paddingTop.dp2px(context),
                paddingRight.dp2px(context), paddingBottom.dp2px(context)
            )
            v.invalidate()
        }
    }

    override fun getItemCount(): Int = mSettingData.size

    override fun getItemViewType(position: Int): Int {
        val setItem = mSettingData[position]
        return if (setItem.viewType == VIEW_TYPE_CUSTOM) {
            setItem.viewBinder?.layoutId ?: VIEW_TYPE_NOT_FOUNT
        } else setItem.viewType
    }

    fun setData(data: ArrayList<SetItem>) {
        this.mSettingData = data
        notifyDataSetChanged()
    }

    /**
     * 初始化TextView快捷方法
     */
    private fun quickInitialTextData(textView: TextView, textProp: TextProp?) {
        textProp?.apply {
            textView.text = content
            textView.textSize = textSize.toFloat()
            textView.setTextColor(Color.parseColor(textColor))
            textView.typeface = typeface
        }
    }

    /**
     * 初始化ImageView快捷方法
     */
    @SuppressLint("CheckResult")
    private fun quickInitialIconData(imageView: ImageView, iconProp: IconProp?) {
        iconProp?.apply {
            if (target == null) {
                imageView.visibility = View.GONE
            } else {
                val context = imageView.context
                val requestOptions = RequestOptions()
                        .override(width.dp2px(context), height.dp2px(context))
                        .transform(CenterCrop(), RoundedCorners(radius.dp2px(context)))
                        .placeholder(placeholder)
                        .error(errorHolder)
                Glide.with(context).load(target)
                        .apply(requestOptions)
                        .into(imageView)
                imageView.visibility = View.VISIBLE
            }
        }
    }

    /**
     * 初始化CheckBox快捷方法
     */
    @SuppressLint("UseCompatLoadingForDrawables")
    private fun quickInitialCheckBoxData(checkBox: CheckBox, checkBoxProp: CheckBoxProp?) {
        checkBoxProp?.apply {
            val context = checkBox.context
            val resources = context.resources
            val drawable = resources.getDrawable(drawableResId)
            checkBox.background = drawable
            // 是否选中
            checkBox.isChecked = checkStatus == true
            // 选中状态监听
            checkBox.setOnCheckedChangeListener(onChecked)

            // 如果设置了宽高比
            val desiredWidth = width
            val layoutParams = checkBox.layoutParams
            layoutParams?.apply {
                if (whRate > 0) {
                    width = if (desiredWidth > 0) {
                        desiredWidth.dp2px(context)
                    } else drawable.intrinsicWidth

                    height = (width / whRate).toInt()
                } else if (desiredWidth > 0) {
                    val rate = drawable.intrinsicWidth * 1.0f / drawable.intrinsicHeight
                    width = desiredWidth.dp2px(context)
                    height = (width / rate).toInt()
                }
            }
        }
    }
}