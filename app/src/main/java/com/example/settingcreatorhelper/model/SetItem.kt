package com.example.settingcreatorhelper.model

import android.graphics.Typeface
import android.view.View
import android.widget.CompoundButton
import com.example.settingcreatorhelper.base.SettingViewBinder
import com.example.settingcreatorhelper.model.SettingConstants.DEFAULT_CHECKBOX_BG
import com.example.settingcreatorhelper.model.SettingConstants.DEFAULT_DIVIDER_COLOR
import com.example.settingcreatorhelper.model.SettingConstants.DEFAULT_ICON_HEIGHT
import com.example.settingcreatorhelper.model.SettingConstants.DEFAULT_ICON_PLACEHOLDER
import com.example.settingcreatorhelper.model.SettingConstants.DEFAULT_ICON_RADIUS
import com.example.settingcreatorhelper.model.SettingConstants.DEFAULT_ICON_WIDTH

/**
 * CheckBox配置: checkStatus是否选中，drawableResId自定义背景图资源ID
 *
 * 自定义背景图，使用指定宽高比
 */
class CheckBoxProp constructor(
    val checkStatus: Boolean?, val drawableResId: Int?,
    val width: Int?, val whRate: Float?,
    val onChecked: CompoundButton.OnCheckedChangeListener?
) {
    /**
     * 使用默认背景图，原始宽度
     */
    constructor(checkStatus: Boolean, onClick: CompoundButton.OnCheckedChangeListener) :
            this(checkStatus, -1, onClick)

    /**
     * 使用默认背景图，指定宽度
     */
    constructor(checkStatus: Boolean, width: Int, onClick: CompoundButton.OnCheckedChangeListener) :
            this(checkStatus, DEFAULT_CHECKBOX_BG, width, -1F, onClick)

    /**
     * 自定义背景图，固定宽度，以原始图片的宽高比进行缩放
     */
    constructor(
        checkStatus: Boolean,
        drawableResId: Int,
        width: Int,
        onClick: CompoundButton.OnCheckedChangeListener
    ) : this(
        checkStatus,
        drawableResId,
        width,
        -1F,
        onClick
    )

    /**
     * 自定义背景图，固定宽高比，取原始图片宽度，对宽高进行缩放
     */
    constructor(
        checkStatus: Boolean,
        drawableResId: Int,
        whRate: Float,
        onClick: CompoundButton.OnCheckedChangeListener
    ) : this(
        checkStatus,
        drawableResId,
        -1,
        whRate,
        onClick
    )
}

/**
 * 点击事件配置：clickDrawableRes 点击时的drawable， onClick 点击触发的回调
 *
 *  优先使用bgRes，如果bgRes和bgColor都没有，用默认的
 */
class LayoutProp private constructor(
    val bgColor: String?,
    val bgRes: Int?,
    val onClick: ((View) -> Unit)?
) {

    @JvmOverloads
    constructor(bgRes: Int? = null, onClick: ((View) -> Unit)?): this(null, bgRes = bgRes, onClick = onClick)

    /**
     * 用户传的是一个颜色固定值，可能不需要点击事件，onClick可以不传
     */
    @JvmOverloads
    constructor(bgColor: String?, onClick: ((View) -> Unit)? = null): this(bgColor, null, onClick = onClick)
}

/**
 * 图标配置
 */
class IconProp(
    val target: Any? = null,
    val width: Int?, val height: Int?, val radius: Int?,
    val placeholder: Int?, val errorHolder: Int?
) {

    @JvmOverloads
    constructor(
        target: Any? = null, width: Int,
        height: Int, radius: Int, placeholder: Int
    ) : this(
        target,
        width,
        height,
        radius,
        placeholder,
        DEFAULT_ICON_PLACEHOLDER,
    )

    @JvmOverloads
    constructor(
        target: Any? = null, width: Int,
        height: Int, radius: Int
    ) : this(
        target,
        width,
        height,
        radius,
        DEFAULT_ICON_PLACEHOLDER,
    )

    constructor(target: Any? = null, radius: Int) : this(
        target,
        DEFAULT_ICON_WIDTH,
        DEFAULT_ICON_HEIGHT,
        radius
    )

    @JvmOverloads
    constructor(target: Any? = null, width: Int, height: Int) : this(
        target,
        width,
        height,
        DEFAULT_ICON_RADIUS,
    )

    @JvmOverloads
    constructor(target: Any? = null) : this(
        target,
        DEFAULT_ICON_WIDTH,
        DEFAULT_ICON_HEIGHT,
    )

}

/**
 * 文字配置
 */
class TextProp(val content: String?, val textSize: Int?, val textColor: String?, val typeface: Typeface?)

/**
 * 分割线配置
 */
class DecorationProp @JvmOverloads constructor(
    val height: Int?,
    val offsetX: Int?,
    val offsetY: Int?,
    val decorationColor: String? = DEFAULT_DIVIDER_COLOR
)

class SetItem internal constructor(
    val viewType: Int?,
    val mainTextProp: TextProp?,
    val hintTextProp: TextProp?,
    val mainIconProp: IconProp?,
    val hintIconProp: IconProp?,
    // check box
    val checkBoxProp: CheckBoxProp?,

    val paddingLeft: Int?,
    val paddingRight: Int?,
    val paddingTop: Int?,
    val paddingBottom: Int?,
    val viewBinder: SettingViewBinder?,
    val layoutProp: LayoutProp?
)

