package com.jamgu.settingpie.base

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.jamgu.settingpie.model.ArrowProp
import com.jamgu.settingpie.model.CheckBoxProp
import com.jamgu.settingpie.model.IconProp
import com.jamgu.settingpie.model.LayoutProp
import com.jamgu.settingpie.model.PaddingProp
import com.jamgu.settingpie.model.SetConstants
import com.jamgu.settingpie.model.TextProp
import com.jamgu.settingpie.util.dp2px

/**
 * 初始化TextView
 */
internal fun initialTextData(textView: TextView, textProp: TextProp?) {
    if (textProp == null) {
        textView.visibility = View.GONE
        return
    }
    textProp.apply {
        textView.text = content
        textView.textSize = textSize?.toFloat() ?: SetConstants.DEFAULT_HINT_TEXT_SIZE.toFloat()
        textView.setTextColor(Color.parseColor(textColor))
        textView.typeface = typeface
        textView.visibility = View.VISIBLE
    }
}


/**
 * 初始化padding
 */
internal fun initialPaddingProp(paddingProp: PaddingProp?, v: View) {
    val prop = paddingProp ?: SetConstants.DEFAULT_PADDING_PROP
    prop.apply {
        val context = v.context
        val paddingLeft = paddingLeft?.dp2px(context) ?: SetConstants.DEFAULT_PADDING_LEFT
        val paddingTop = paddingTop?.dp2px(context) ?: SetConstants.DEFAULT_PADDING_TOP
        val paddingRight = paddingRight?.dp2px(context) ?: SetConstants.DEFAULT_PADDING_RIGHT
        val paddingBottom = paddingBottom?.dp2px(context) ?: SetConstants.DEFAULT_PADDING_BOTTOM
        v.setPadding(
            paddingLeft,
            paddingTop,
            paddingRight,
            paddingBottom
        )
        v.invalidate()
    }
}

/**
 * 初始化arrow
 */
internal fun initialArrowProp(arrowProp: ArrowProp?, v: ImageView) {
    val prop = arrowProp ?: SetConstants.DEFAULT_ARROW_PROP
    prop.apply {
        if (isShow == true) {
            v.visibility = View.VISIBLE
            v.setBackgroundResource(bgRes ?: SetConstants.DEFAULT_ARROW_BG)
        } else v.visibility = View.GONE
    }
}

/**
 * 初始化ImageView
 */
@SuppressLint("CheckResult")
internal fun initialIconData(imageView: ImageView, iconProp: IconProp?) {
    if (iconProp == null) {
        imageView.visibility = View.GONE
        return
    }
    iconProp.apply {
        if (target == null) {
            imageView.visibility = View.GONE
        } else {
            val context = imageView.context
            val requestOptions = RequestOptions()
                    .override(
                        width?.dp2px(context) ?: SetConstants.DEFAULT_ICON_WIDTH,
                        height?.dp2px(context) ?: SetConstants.DEFAULT_ICON_HEIGHT
                    )
                    .transform(
                        CenterCrop(),
                        RoundedCorners(radius?.dp2px(context) ?: SetConstants.DEFAULT_ICON_RADIUS)
                    )
                    .placeholder(placeholder ?: SetConstants.DEFAULT_ICON_PLACEHOLDER)
                    .error(errorHolder ?: SetConstants.DEFAULT_ICON_PLACEHOLDER)
            Glide.with(context).load(target)
                    .apply(requestOptions)
                    .into(imageView)
            imageView.visibility = View.VISIBLE
        }
    }
}

/**
 * 初始化layout
 */
internal fun initialLayoutProp(layoutProp: LayoutProp?, holder: RecyclerView.ViewHolder) {
    if (layoutProp == null) {
        if (holder is BaseViewHolder<*> && !holder.clickable) return

        holder.itemView.setBackgroundResource(SetConstants.DEFAULT_CLICKABLE_BG)
        return
    }

    layoutProp.apply {
        // 优先使用bgRes，如果bgRes和bgColor都没有，用默认的
        when {
            bgRes != null -> {
                holder.itemView.setBackgroundResource(bgRes)
            }
            bgColor != null -> {
                holder.itemView.setBackgroundColor(Color.parseColor(bgColor))
            }
            else -> {
                holder.itemView.setBackgroundResource(SetConstants.DEFAULT_CLICKABLE_BG)
            }
        }
        holder.itemView.setOnClickListener(onClick)
    }
}

/**
 * 初始化CheckBox
 */
@SuppressLint("UseCompatLoadingForDrawables")
internal fun initialCheckBoxData(checkBox: CheckBox, checkBoxProp: CheckBoxProp?) {
    if (checkBoxProp == null) {
        checkBox.visibility = View.GONE
        return
    }
    checkBoxProp.apply {
        val context = checkBox.context
        val resources = context.resources
        val drawable = resources.getDrawable(drawableResId ?: SetConstants.DEFAULT_CHECKBOX_BG)
        checkBox.background = drawable
        // 是否选中
        checkBox.isChecked = checkStatus == true
        // 选中状态监听
        checkBox.setOnCheckedChangeListener(onChecked)

        // 如果设置了宽高比
        val desiredWidth = width
        val layoutParams = checkBox.layoutParams
        layoutParams?.apply {
            if (whRate != null && whRate > 0) {
                width = if (desiredWidth != null && desiredWidth > 0) {
                    desiredWidth.dp2px(context)
                } else drawable.intrinsicWidth

                height = (width / whRate).toInt()
            } else if (desiredWidth != null && desiredWidth > 0) {
                val rate = drawable.intrinsicWidth * 1.0f / drawable.intrinsicHeight
                width = desiredWidth.dp2px(context)
                height = (width / rate).toInt()
            }
        }
    }
}