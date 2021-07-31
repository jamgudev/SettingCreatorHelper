package com.example.settingcreatorhelper.model

import android.graphics.Typeface
import com.example.settingcreatorhelper.base.SettingViewBinder
import com.example.settingcreatorhelper.model.SettingConstants.DEFAULT_HINT_TEXT_COLOR
import com.example.settingcreatorhelper.model.SettingConstants.DEFAULT_HINT_TEXT_SIZE
import com.example.settingcreatorhelper.model.SettingConstants.DEFAULT_ICON_HEIGHT
import com.example.settingcreatorhelper.model.SettingConstants.DEFAULT_ICON_PLACEHOLDER
import com.example.settingcreatorhelper.model.SettingConstants.DEFAULT_ICON_RADIUS
import com.example.settingcreatorhelper.model.SettingConstants.DEFAULT_ICON_WIDTH
import com.example.settingcreatorhelper.model.SettingConstants.DEFAULT_MAIN_TEXT_COLOR
import com.example.settingcreatorhelper.model.SettingConstants.DEFAULT_MAIN_TEXT_SIZE
import com.example.settingcreatorhelper.model.SettingConstants.DEFAULT_PADDING_BOTTOM
import com.example.settingcreatorhelper.model.SettingConstants.DEFAULT_PADDING_LEFT
import com.example.settingcreatorhelper.model.SettingConstants.DEFAULT_PADDING_RIGHT
import com.example.settingcreatorhelper.model.SettingConstants.DEFAULT_PADDING_TOP
import com.example.settingcreatorhelper.model.SettingConstants.DEFAULT_TEXT_TYPEFACE

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
    private var layoutprop: LayoutProp? = null

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
    ): SetItemBuilder {
        return mainText(
            content,
            textSize,
            null,
            null
        )
    }

    fun mainText(
        content: String?, textColor: String?
    ): SetItemBuilder {
        return mainText(
            content,
            null,
            textColor,
            null
        )
    }

    @JvmOverloads
    fun mainText(
        content: String?, textSize: Int?,
        textColor: String?, typeface: Typeface? = null
    ): SetItemBuilder {
        return mainTextProp(
            TextProp(
                content,
                textSize ?: DEFAULT_MAIN_TEXT_SIZE,
                textColor ?: DEFAULT_MAIN_TEXT_COLOR,
                typeface ?: DEFAULT_TEXT_TYPEFACE
            )
        )
    }

    private fun mainTextProp(mainTextProp: TextProp?): SetItemBuilder {
        this.mainTextProp = mainTextProp
        return this
    }

    @JvmOverloads
    fun hintText(
        content: String?, textSize: Int? = null,
    ): SetItemBuilder {
        return hintText(
            content,
            textSize,
            null,
            null
        )
    }

    fun hintText(
        content: String?, textColor: String?,
    ): SetItemBuilder {
        return hintText(
            content,
            null,
            textColor,
            null
        )
    }

    @JvmOverloads
    fun hintText(
        content: String?, textSize: Int?,
        textColor: String?, typeface: Typeface? = null
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

    fun mainIcon(
        target: Any?,
    ): SetItemBuilder {
        return mainIcon(target, null, null, null)
    }

    fun mainIcon(
        target: Any?,
        radius: Int?
    ): SetItemBuilder {
        return mainIcon(target, null, null, radius)
    }

    fun mainIcon(
        target: Any?,
        width: Int?,
        height: Int?
    ): SetItemBuilder {
        return mainIcon(
            target = target, width = width,
            height = height, null
        )
    }

    @JvmOverloads
    fun mainIcon(
        target: Any?,
        width: Int?,
        height: Int?,
        radius: Int?,
        placeholder: Int? = null,
        errorHolder: Int? = null
    ): SetItemBuilder {
        return mainIconProp(
            IconProp(
                target = target, width = width ?: DEFAULT_ICON_WIDTH,
                height = height ?: DEFAULT_ICON_HEIGHT,
                radius = radius ?: DEFAULT_ICON_RADIUS,
                placeholder = placeholder ?: DEFAULT_ICON_PLACEHOLDER,
                errorHolder = errorHolder ?: DEFAULT_ICON_PLACEHOLDER
            )
        )
    }

    private fun mainIconProp(mainIconProp: IconProp?): SetItemBuilder {
        this.mainIconProp = mainIconProp
        return this
    }


    fun hintIcon(
        target: Any?,
    ): SetItemBuilder {
        return hintIcon(target, null, null, null)
    }

    fun hintIcon(
        target: Any?,
        radius: Int?
    ): SetItemBuilder {
        return hintIcon(target, null, null, radius)
    }

    fun hintIcon(
        target: Any?,
        width: Int?,
        height: Int?
    ): SetItemBuilder {
        return hintIcon(
            target = target, width = width,
            height = height, null
        )
    }

    @JvmOverloads
    fun hintIcon(
        target: Any?,
        width: Int?,
        height: Int?,
        radius: Int?,
        placeholder: Int? = null,
        errorHolder: Int? = null
    ): SetItemBuilder {
        return hintIconProp(
            IconProp(
                target = target, width = width ?: DEFAULT_ICON_WIDTH,
                height = height ?: DEFAULT_ICON_HEIGHT,
                radius = radius ?: DEFAULT_ICON_RADIUS,
                placeholder = placeholder ?: DEFAULT_ICON_PLACEHOLDER,
                errorHolder = errorHolder ?: DEFAULT_ICON_PLACEHOLDER
            )
        )
    }

    private fun hintIconProp(hintIconProp: IconProp?): SetItemBuilder {
        this.hintIconProp = hintIconProp
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

    fun layoutProp(layoutProp: LayoutProp?): SetItemBuilder {
        this.layoutprop = layoutProp
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
            layoutProp = layoutprop,
        )
    }

}