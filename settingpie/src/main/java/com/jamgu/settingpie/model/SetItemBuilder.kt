package com.jamgu.settingpie.model

import android.graphics.Typeface
import androidx.annotation.DrawableRes
import com.jamgu.settingpie.base.SettingViewBinder
import com.jamgu.settingpie.model.SetConstants.DEFAULT_ARROW_BG
import com.jamgu.settingpie.model.SetConstants.DEFAULT_HINT_TEXT_COLOR
import com.jamgu.settingpie.model.SetConstants.DEFAULT_HINT_TEXT_SIZE
import com.jamgu.settingpie.model.SetConstants.DEFAULT_ICON_HEIGHT
import com.jamgu.settingpie.model.SetConstants.DEFAULT_ICON_PLACEHOLDER
import com.jamgu.settingpie.model.SetConstants.DEFAULT_ICON_RADIUS
import com.jamgu.settingpie.model.SetConstants.DEFAULT_ICON_WIDTH
import com.jamgu.settingpie.model.SetConstants.DEFAULT_MAIN_TEXT_COLOR
import com.jamgu.settingpie.model.SetConstants.DEFAULT_MAIN_TEXT_SIZE
import com.jamgu.settingpie.model.SetConstants.DEFAULT_PADDING_BOTTOM
import com.jamgu.settingpie.model.SetConstants.DEFAULT_PADDING_LEFT
import com.jamgu.settingpie.model.SetConstants.DEFAULT_PADDING_RIGHT
import com.jamgu.settingpie.model.SetConstants.DEFAULT_PADDING_TOP
import com.jamgu.settingpie.model.SetConstants.DEFAULT_TEXT_TYPEFACE

class SetItemBuilder {


    private var viewType: Int = -1
    private var mainTextProp: TextProp? = null
    private var hintTextProp: TextProp? = null
    private var mainIconProp: IconProp? = null
    private var hintIconProp: IconProp? = null

    private var checkBoxProp: CheckBoxProp? = null
    private var arrowProp: ArrowProp? = null

    private var paddingProp: PaddingProp? = null

    private var viewBinder: SettingViewBinder? = null
    private var layoutprop: LayoutProp? = null

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

    @JvmOverloads
    fun showArrow(isShow: Boolean?, @DrawableRes bgRest: Int? = null): SetItemBuilder {
        this.arrowProp = ArrowProp(isShow, bgRest ?: DEFAULT_ARROW_BG)
        return this
    }

    fun paddingX(paddingX: Int): SetItemBuilder {
        return paddingInsets(paddingX, DEFAULT_PADDING_TOP, paddingX, DEFAULT_PADDING_BOTTOM)
    }

    fun paddingY(paddingY: Int): SetItemBuilder {
        return paddingInsets(DEFAULT_PADDING_LEFT, paddingY, DEFAULT_PADDING_RIGHT, paddingY)
    }

    fun paddingPair(x: Int?, y: Int?): SetItemBuilder {
        return paddingInsets(x, y, x, y)
    }

    fun paddingAll(paddingAll: Int): SetItemBuilder {
        return paddingInsets(paddingAll, paddingAll, paddingAll, paddingAll)
    }

    private fun paddingInsets(left: Int?, top: Int?, right: Int?, bottom: Int?): SetItemBuilder {
        this.paddingProp = PaddingProp(
            left ?: DEFAULT_PADDING_LEFT, top ?: DEFAULT_PADDING_TOP,
            right ?: DEFAULT_PADDING_RIGHT, bottom ?: DEFAULT_PADDING_BOTTOM
        )
        return this
    }

    fun isPaddingSet(): Boolean {
        return paddingProp != null
    }

    fun isArrowSet(): Boolean {
        return arrowProp != null
    }

    fun viewBinder(binder: SettingViewBinder?): SetItemBuilder {
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
            mainTextProp = mainTextProp,
            hintIconProp = hintIconProp,
            hintTextProp = hintTextProp,
            checkBoxProp = checkBoxProp,
            paddingProp = paddingProp,
            viewBinder = viewBinder,
            layoutProp = layoutprop,
            arrowProp = arrowProp
        )
    }

}