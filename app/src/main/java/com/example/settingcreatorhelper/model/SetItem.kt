package com.example.settingcreatorhelper.model

import android.view.View
import android.widget.CompoundButton
import com.example.settingcreatorhelper.base.SettingViewBinder
import com.example.settingcreatorhelper.model.SettingConstants.DEFAULT_CHECKBOX_BG
import com.example.settingcreatorhelper.model.SettingConstants.DEFAULT_CLICK_BG
import com.example.settingcreatorhelper.model.SettingConstants.DEFAULT_HINT_COLOR
import com.example.settingcreatorhelper.model.SettingConstants.DEFAULT_HINT_ICON_RADIUS
import com.example.settingcreatorhelper.model.SettingConstants.DEFAULT_HINT_ICON_SIZE
import com.example.settingcreatorhelper.model.SettingConstants.DEFAULT_HINT_TEXT_SIZE
import com.example.settingcreatorhelper.model.SettingConstants.DEFAULT_MAIN_COLOR
import com.example.settingcreatorhelper.model.SettingConstants.DEFAULT_MAIN_TEXT_SIZE
import com.example.settingcreatorhelper.model.SettingConstants.DEFAULT_PADDING_BOTTOM
import com.example.settingcreatorhelper.model.SettingConstants.DEFAULT_PADDING_LEFT
import com.example.settingcreatorhelper.model.SettingConstants.DEFAULT_PADDING_RIGHT
import com.example.settingcreatorhelper.model.SettingConstants.DEFAULT_PADDING_TOP

/**
 * CheckBox配置: checkStatus是否选中，drawableResId自定义背景图资源ID
 *
 * 自定义背景图，使用指定宽高比
 */
class CheckBoxProp constructor(
    val checkStatus: Boolean, val drawableResId: Int,
    val width: Int, val whRate: Float,
    val onChecked: CompoundButton.OnCheckedChangeListener
) {
    /**
     * 使用默认背景图，原始宽度
     */
    constructor(checkStatus: Boolean, onClick: CompoundButton.OnCheckedChangeListener) : this(checkStatus, -1, onClick)

    /**
     * 使用默认背景图，指定宽度
     */
    constructor(checkStatus: Boolean, width: Int, onClick: CompoundButton.OnCheckedChangeListener):
            this(checkStatus, DEFAULT_CHECKBOX_BG, width, -1F, onClick)

    /**
     * 自定义背景图，固定宽度，以原始图片的宽高比进行缩放
     */
    constructor(checkStatus: Boolean, drawableResId: Int, width: Int, onClick: CompoundButton.OnCheckedChangeListener) : this(
        checkStatus,
        drawableResId,
        width,
        -1F,
        onClick
    )

    /**
     * 自定义背景图，固定宽高比，取原始图片宽度，对宽高进行缩放
     */
    constructor(checkStatus: Boolean, drawableResId: Int, whRate: Float, onClick: CompoundButton.OnCheckedChangeListener): this(
        checkStatus,
        drawableResId,
        -1,
        whRate,
        onClick
    )
}

/**
 * 点击事件配置：clickDrawableRes 点击时的drawable， to 点击触发的回调
 */
class ClickProp @JvmOverloads constructor(val clickDrawableRes: Int = DEFAULT_CLICK_BG, val to: (View) -> Unit)

// TODO 完善icon配置
class IconProp()

class SetItem internal constructor(
    val viewType: Int,
    val iconUrl: String?,
    val mainText: String,
    val mainTextSize: Int,
    val hintText: String?,
    val hintTextSize: Int,
    val hintIconUrl: String?,
    val mainTextColor: String,
    val hintTextColor: String,
    val hintIconSize: Int,
    val hintIconRadius: Int,
    // check box
    val checkBoxProp: CheckBoxProp?,

    val paddingLeft: Int,
    val paddingRight: Int,
    val paddingTop: Int,
    val paddingBottom: Int,
    val viewBinder: SettingViewBinder?,
    val clickProp: ClickProp?
)

class SetItemBuilder {


    private var viewType: Int = -1
    private var iconUrl: String? = null
    private var mainText: String = ""
    private var mainTextSize: Int ?= null
    private var mainTextColor: String? = null
    private var hintText: String? = null
    private var hintTextSize: Int? = null
    private var hintIconUrl: String? = null
    private var hintTextColor: String? = null
    private var hintIconSize: Int? = null
    private var hintIconRadius: Int? = null

    private var checkBoxProp: CheckBoxProp? = null

    private var paddingLeft: Int? = null
    private var paddingRight: Int? = null
    private var paddingTop: Int? = null
    private var paddingBottom: Int? = null

    private var viewBinder: SettingViewBinder? = null
    private var clickProp: ClickProp? = null

    private var isPaddingSet = false

    fun viewType(viewType: Int): SetItemBuilder {
        this.viewType = viewType
        return this
    }

    fun iconUrl(iconUrl: String): SetItemBuilder {
        this.iconUrl= iconUrl
        return this
    }

    fun mainText(text: String): SetItemBuilder {
        this.mainText = text
        return this
    }

    fun mainTextSize(textSize: Int): SetItemBuilder {
        this.mainTextSize = textSize
        return this
    }

    fun mainTextColor(textColor: String): SetItemBuilder {
        this.mainTextColor = textColor
        return this
    }

    fun hintText(hintText: String): SetItemBuilder {
        this.hintText = hintText
        return this
    }

    fun hintTextSize(hintTextSize: Int): SetItemBuilder {
        this.hintTextSize = hintTextSize
        return this
    }

    fun hintIconUrl(hintIconUrl: String?): SetItemBuilder {
        this.hintIconUrl = hintIconUrl
        return this
    }

    fun hintTextColor(hintColor: String): SetItemBuilder {
        this.hintTextColor = hintColor
        return this
    }

    fun hintIconSize(hintIconSize: Int): SetItemBuilder {
        this.hintIconSize = hintIconSize
        return this
    }

    fun hintIconRadius(hintIconRadius: Int): SetItemBuilder {
        this.hintIconRadius = hintIconRadius
        return this
    }

    fun checkBoxProp(checkBoxProp: CheckBoxProp): SetItemBuilder {
        this.checkBoxProp = checkBoxProp
        return this
    }

    fun paddingX(paddingX: Int): SetItemBuilder {
        paddingInsets(paddingX, DEFAULT_PADDING_TOP, paddingX, DEFAULT_PADDING_BOTTOM)
        return this
    }

    fun paddingY(paddingY: Int): SetItemBuilder {
        paddingInsets(DEFAULT_PADDING_LEFT, paddingY, DEFAULT_PADDING_RIGHT, paddingY)
        return this
    }

    fun paddingAll(paddingAll: Int): SetItemBuilder {
        paddingInsets(paddingAll, paddingAll, paddingAll, paddingAll)
        return this
    }

    fun paddingInsets(left: Int, top: Int, right: Int, bottom: Int): SetItemBuilder {
        isPaddingSet = true
        this.paddingLeft = left
        this.paddingTop = top
        this.paddingRight = right
        this.paddingBottom = bottom
        return this
    }

    fun isPaddingChanged(): Boolean {
        return isPaddingSet
    }

    fun viewBinder(binder: SettingViewBinder): SetItemBuilder {
        this.viewBinder = binder
        return this
    }

    fun onLayoutClickProp(clickProp: ClickProp?): SetItemBuilder {
        this.clickProp = clickProp
        return this
    }

    internal fun build(): SetItem {
        return SetItem(
            viewType = viewType,
            iconUrl = iconUrl,
            mainText = mainText,
            mainTextSize = mainTextSize ?: DEFAULT_MAIN_TEXT_SIZE,
            hintText = hintText,
            hintTextSize = hintTextSize ?: DEFAULT_HINT_TEXT_SIZE,
            hintIconUrl = hintIconUrl,
            mainTextColor = mainTextColor ?: DEFAULT_MAIN_COLOR,
            hintTextColor = hintTextColor ?: DEFAULT_HINT_COLOR,
            hintIconSize = hintIconSize ?: DEFAULT_HINT_ICON_SIZE,
            hintIconRadius = hintIconRadius ?: DEFAULT_HINT_ICON_RADIUS,
            checkBoxProp = checkBoxProp,
            paddingLeft = paddingLeft ?: DEFAULT_PADDING_LEFT,
            paddingRight = paddingRight ?: DEFAULT_PADDING_RIGHT,
            paddingTop = paddingTop ?: DEFAULT_PADDING_TOP,
            paddingBottom = paddingBottom ?: DEFAULT_PADDING_BOTTOM,
            viewBinder = viewBinder,
            clickProp = clickProp,
        )
    }

}
