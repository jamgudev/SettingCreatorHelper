package com.jamgu.settingpie.model

import android.graphics.Typeface
import android.widget.ImageView
import com.jamgu.settingpie.R

object SetConstants {
    // Padding
    const val DEFAULT_PADDING_LEFT = 16   // dp
    const val DEFAULT_PADDING_RIGHT = DEFAULT_PADDING_LEFT
    const val DEFAULT_PADDING_TOP = 8     // dp
    const val DEFAULT_PADDING_BOTTOM = DEFAULT_PADDING_TOP
    val DEFAULT_PADDING_PROP = PaddingProp(
        DEFAULT_PADDING_LEFT, DEFAULT_PADDING_TOP,
        DEFAULT_PADDING_RIGHT, DEFAULT_PADDING_BOTTOM
    )

    // ICON
    const val DEFAULT_ICON_WIDTH = 30   // dp
    const val DEFAULT_ICON_HEIGHT = 30  // dp
    const val DEFAULT_ICON_RADIUS = 4   // dp
    val DEFAULT_ICON_SCALE_TYPE = ImageView.ScaleType.CENTER_CROP
    val DEFAULT_ICON_PLACEHOLDER = R.drawable.default_img_placeholder

    // Text
    val DEFAULT_TEXT_TYPEFACE: Typeface = Typeface.DEFAULT // normal

    // Hint Text
    const val DEFAULT_HINT_TEXT_COLOR = "#999999"
    const val DEFAULT_HINT_TEXT_SIZE = 14

    // Main Text
    const val DEFAULT_MAIN_TEXT_COLOR = "#333333"
    const val DEFAULT_MAIN_TEXT_SIZE = 16

    // CheckBox
    val DEFAULT_CHECKBOX_BG = R.drawable.default_checkbox_selector

    // ItemDecoration
    const val DEFAULT_DECORATION_HEIGHT = 1     // dp
    const val DEFAULT_DIVIDER_OFFSET_X = 0   // dp
    const val DEFAULT_DIVIDER_OFFSET_Y = 0   // dp
    const val DEFAULT_DIVIDER_COLOR = "#f0f2f5"
    const val DEFAULT_DIVIDER_GROUP_COLOR = "#00000000"
    const val DEFAULT_DECORATION_GROUP_HEIGHT = 10

    // Theme ItemDecoration
    val DEFAULT_DECORATION_PROP_THEME = DecorationProp(
        DEFAULT_DECORATION_HEIGHT,
        DEFAULT_DIVIDER_OFFSET_X,
        DEFAULT_DIVIDER_OFFSET_Y,
        DEFAULT_DIVIDER_GROUP_COLOR
    )
    // Group ItemDecoration
    val DEFAULT_DECORATION_PROP_OUTER = DecorationProp(
        DEFAULT_DECORATION_GROUP_HEIGHT,
        DEFAULT_DIVIDER_OFFSET_X,
        DEFAULT_DIVIDER_OFFSET_Y,
        DEFAULT_DIVIDER_GROUP_COLOR
    )

    // layout
    val DEFAULT_CLICKABLE_BG = R.drawable.default_white_gray_selector

    // Arrow
    val DEFAULT_ARROW_BG = R.drawable.default_arrow_right
    val DEFAULT_ARROW_PROP = ArrowProp(
        true, DEFAULT_ARROW_BG
    )
}