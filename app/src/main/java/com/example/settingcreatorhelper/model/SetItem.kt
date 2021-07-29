package com.example.settingcreatorhelper.model

import android.graphics.Typeface
import android.view.View
import android.widget.CompoundButton
import android.widget.ImageView
import com.example.settingcreatorhelper.base.SettingViewBinder
import com.example.settingcreatorhelper.model.SettingConstants.DEFAULT_CHECKBOX_BG
import com.example.settingcreatorhelper.model.SettingConstants.DEFAULT_CLICK_BG
import com.example.settingcreatorhelper.model.SettingConstants.DEFAULT_HINT_TEXT_COLOR
import com.example.settingcreatorhelper.model.SettingConstants.DEFAULT_HINT_TEXT_SIZE
import com.example.settingcreatorhelper.model.SettingConstants.DEFAULT_ICON_HEIGHT
import com.example.settingcreatorhelper.model.SettingConstants.DEFAULT_ICON_RADIUS
import com.example.settingcreatorhelper.model.SettingConstants.DEFAULT_ICON_SCALE_TYPE
import com.example.settingcreatorhelper.model.SettingConstants.DEFAULT_ICON_WIDTH
import com.example.settingcreatorhelper.model.SettingConstants.DEFAULT_MAIN_TEXT_COLOR
import com.example.settingcreatorhelper.model.SettingConstants.DEFAULT_MAIN_TEXT_SIZE
import com.example.settingcreatorhelper.model.SettingConstants.DEFAULT_PADDING_BOTTOM
import com.example.settingcreatorhelper.model.SettingConstants.DEFAULT_PADDING_LEFT
import com.example.settingcreatorhelper.model.SettingConstants.DEFAULT_PADDING_RIGHT
import com.example.settingcreatorhelper.model.SettingConstants.DEFAULT_PADDING_TOP
import com.example.settingcreatorhelper.model.SettingConstants.DEFAULT_TEXT_TYPEFACE

/**
 * CheckBox配置: checkStatus是否选中，drawableResId自定义背景图资源ID
 *
 * 自定义背景图，使用指定宽高比
 */
class CheckBoxProp constructor(
    val checkStatus: Boolean?, val drawableResId: Int,
    val width: Int, val whRate: Float,
    val onChecked: CompoundButton.OnCheckedChangeListener
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
 * 点击事件配置：clickDrawableRes 点击时的drawable， to 点击触发的回调
 */
class ClickProp @JvmOverloads constructor(
    val clickDrawableRes: Int = DEFAULT_CLICK_BG,
    val to: ((View) -> Unit)?
)

/**
 * 图标配置
 */
class IconProp(
    val iconUrl: String?, val scaleType: ImageView.ScaleType,
    val width: Int, val height: Int, val radius: Int
) {

    constructor(iconUrl: String, scaleType: ImageView.ScaleType, width: Int, height: Int) : this(
        iconUrl,
        scaleType,
        width,
        height,
        DEFAULT_ICON_RADIUS
    )

    constructor(iconUrl: String, scaleType: ImageView.ScaleType) : this(
        iconUrl,
        scaleType,
        DEFAULT_ICON_WIDTH,
        DEFAULT_ICON_HEIGHT
    )

    constructor(iconUrl: String) : this(iconUrl, DEFAULT_ICON_SCALE_TYPE)
}

/**
 * 文字配置
 */
class TextProp(val content: String?, val textSize: Int, val textColor: String, val typeface: Typeface)

class SetItem internal constructor(
    val viewType: Int,
    val mainTextProp: TextProp?,
    val hintTextProp: TextProp?,
    val mainIconProp: IconProp?,
    val hintIconProp: IconProp?,
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
    private var mainTextProp: TextProp? = null
    private var hintTextProp: TextProp? = null
    private var mainIconProp: IconProp? = null
    private var hintIconProp: IconProp? = null

    private var checkBoxProp: CheckBoxProp? = null

    private var paddingLeft: Int? = null
    private var paddingRight: Int? = null
    private var paddingTop: Int? = null
    private var paddingBottom: Int? = null

    private var viewBinder: SettingViewBinder? = null
    private var clickProp: ClickProp? = null

    private var isPaddingSet = false

    private var defaultMainTextProp: TextProp? = null
    private var defaultHintTextProp: TextProp? = null

    fun viewType(viewType: Int): SetItemBuilder {
        this.viewType = viewType
        return this
    }

    @JvmOverloads
    fun mainText(
        content: String?, textSize: Int? = null,
        textColor: String? = null, typeface: Typeface? = null
    ): SetItemBuilder {
        mainTextProp(
            TextProp(
                content,
                textSize ?: DEFAULT_MAIN_TEXT_SIZE,
                textColor ?: DEFAULT_MAIN_TEXT_COLOR,
                typeface ?: DEFAULT_TEXT_TYPEFACE
            )
        )
        return this
    }

    private fun mainTextProp(mainTextProp: TextProp?): SetItemBuilder {
        this.mainTextProp = mainTextProp
        return this
    }

    @JvmOverloads
    fun hintText(
        content: String?, textSize: Int? = null,
        textColor: String? = null, typeface: Typeface? = null
    ): SetItemBuilder {
        hintTextProp(
            TextProp(
                content,
                textSize ?: DEFAULT_HINT_TEXT_SIZE,
                textColor ?: DEFAULT_HINT_TEXT_COLOR,
                typeface ?: DEFAULT_TEXT_TYPEFACE
            )
        )
        return this
    }

    private fun hintTextProp(hintTextProp: TextProp?): SetItemBuilder {
        this.hintTextProp = hintTextProp
        return this
    }

    fun mainIconProp(mainIconProp: IconProp?): SetItemBuilder {
        this.mainIconProp = mainIconProp
        return this
    }

    fun hintIconProp(hintIconProp: IconProp?): SetItemBuilder {
        this.mainIconProp = hintIconProp
        return this
    }

    fun checkBoxProp(checkBoxProp: CheckBoxProp?): SetItemBuilder {
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
            mainIconProp = mainIconProp,
            mainTextProp = mainTextProp ?: defaultMainTextProp,
            hintIconProp = hintIconProp,
            hintTextProp = hintTextProp ?: defaultHintTextProp,
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
