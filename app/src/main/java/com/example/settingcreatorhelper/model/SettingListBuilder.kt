package com.example.settingcreatorhelper.model

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.settingcreatorhelper.base.SettingItemDecoration
import com.example.settingcreatorhelper.model.SettingConstants.DEFAULT_DIVIDER_COLOR
import com.example.settingcreatorhelper.model.SettingConstants.DEFAULT_DECORATION_GROUP_HEIGHT
import com.example.settingcreatorhelper.model.SettingConstants.DEFAULT_DECORATION_HEIGHT
import com.example.settingcreatorhelper.model.SettingConstants.DEFAULT_DIVIDER_OFFSET_X
import com.example.settingcreatorhelper.model.SettingConstants.DEFAULT_DIVIDER_OFFSET_Y
import com.example.settingcreatorhelper.model.SettingConstants.DEFAULT_DECORATION_PROP_OUTER
import com.example.settingcreatorhelper.model.SettingConstants.DEFAULT_DIVIDER_GROUP_COLOR
import com.example.settingcreatorhelper.model.SettingConstants.DEFAULT_PADDING_BOTTOM
import com.example.settingcreatorhelper.model.SettingConstants.DEFAULT_PADDING_LEFT
import com.example.settingcreatorhelper.model.SettingConstants.DEFAULT_PADDING_RIGHT
import com.example.settingcreatorhelper.model.SettingConstants.DEFAULT_PADDING_TOP

class SettingListBuilder(private val recyclerView: RecyclerView) {

    // padding
    private var paddingLeft = DEFAULT_PADDING_LEFT
    private var paddingTop = DEFAULT_PADDING_TOP
    private var paddingRight = DEFAULT_PADDING_RIGHT
    private var paddingBottom = DEFAULT_PADDING_BOTTOM

    private val setItemList: ArrayList<SetItem> by lazy { ArrayList() }

    private var isParentPaddingSet: Boolean = false

    // item decoration
    private var decorationHeight: Int? = null
    private var decorationOffsetX: Int? = null
    private var decorationOffsetY: Int? = null
    private var decorationColor: String? = null
    private var isShowItemDecoration: Boolean = false

    /**
     * 存储item的间隔线配置
     */
    private val mItemDecorationProps: ArrayList<DecorationProp> by lazy {
        ArrayList()
    }

    /**
     * group间隔线配置，如果这个被配置了，添加group间隔线时会直接使用这个，不再新建DecorationProp
     */
    private var groupItemDecorationProp: DecorationProp = DEFAULT_DECORATION_PROP_OUTER

    /**
     * group间隔线数量，用于保证最终group间隔线数量成对
     */
    private var groupDecorationCount: Int = 0

    fun paddingY(paddingY: Int?): SettingListBuilder {
        paddingInset(null, paddingY, null, paddingY)
        return this
    }

    fun paddingX(paddingX: Int?): SettingListBuilder {
        paddingInset(paddingX, null, paddingX, null)
        return this
    }

    fun paddingPair(x: Int?, y: Int?): SettingListBuilder {
        paddingInset(x, y, x, y)
        return this
    }

    fun paddingAll(all: Int?): SettingListBuilder {
        paddingInset(all, all, all, all)
        return this
    }

    fun paddingInset(
        left: Int?, top: Int?,
        right: Int?, bottom: Int?
    ): SettingListBuilder {
        isParentPaddingSet = true
        paddingTop = top ?: DEFAULT_PADDING_TOP
        paddingBottom = bottom ?: DEFAULT_PADDING_BOTTOM
        paddingLeft = left ?: DEFAULT_PADDING_LEFT
        paddingRight = right ?: DEFAULT_PADDING_RIGHT
        return this
    }

    fun showDecoration(isShow: Boolean?): SettingListBuilder {
        isShowItemDecoration = isShow == true
        return this
    }

    /**
     * @see decorationInner
     */
    /*fun decorationHeight(height: Int?): SettingListBuilder {
        return decorationInner(height, decorationOffsetX, decorationOffsetY, decorationColor)
    }*/

    /**
     * @see decorationInner
     */
    /*fun decorationOffsetX(offsetX: Int?): SettingListBuilder {
        return decorationInner(decorationHeight, offsetX, decorationOffsetY, decorationColor)
    }*/

    /**
     * @see decorationInner
     */
    /*fun decorationOffsetY(offsetY: Int?): SettingListBuilder {
        return decorationInner(decorationHeight, decorationOffsetX, offsetY, decorationColor)
    }*/

    /**
     * @see decorationInner
     */
    fun decorationOfTheme(height: Int?, offsetX: Int?, offsetY: Int?, color: String?): SettingListBuilder {
        return decorationInner(height, offsetX, offsetY, color)
    }

    /**
     * call this to set decoration prop (in dp), and method [showDecoration] will be
     * called immediately with true to be set
     */
    private fun decorationInner(height: Int?, offsetX: Int?, offsetY: Int?, color: String?): SettingListBuilder {
        this.decorationHeight = height ?: DEFAULT_DECORATION_HEIGHT
        this.decorationOffsetX = offsetX ?: DEFAULT_DIVIDER_OFFSET_X
        this.decorationOffsetY = offsetY ?: DEFAULT_DIVIDER_OFFSET_Y
        this.decorationColor = color ?: DEFAULT_DIVIDER_COLOR
        showDecoration(true)
        return this
    }

    private fun getThemeDecorationProp(): DecorationProp {
        return DecorationProp(
            decorationHeight ?: DEFAULT_DECORATION_HEIGHT,
            decorationOffsetX ?: DEFAULT_DIVIDER_OFFSET_X,
            decorationOffsetY ?: DEFAULT_DIVIDER_OFFSET_Y,
            decorationColor ?: DEFAULT_DIVIDER_COLOR
        )
    }

    fun decorationOfGroup(height: Int?, offsetX: Int?, offsetY: Int?, color: String?) : SettingListBuilder {
        this.groupItemDecorationProp = DecorationProp(
            height ?: DEFAULT_DECORATION_GROUP_HEIGHT,
            offsetX ?: DEFAULT_DIVIDER_OFFSET_X,
            offsetY ?: DEFAULT_DIVIDER_OFFSET_Y,
            color ?: DEFAULT_DIVIDER_GROUP_COLOR
        )
        return this
    }


    fun addItem(invoker: (() -> SetItemBuilder)?): SettingListBuilder {
        invoker ?: return this

        return addItemInner(invoker.invoke())
    }

    /*fun addItem(itemBuilder: SetItemBuilder): SettingListBuilder {
        // 没有定制，在Adapter中设置了padding，则覆盖
        return addItemInner(itemBuilder)
    }*/


    private fun addItemInner(itemBuilder: SetItemBuilder?, fixProp: DecorationProp? = null): SettingListBuilder {
        itemBuilder ?: return this

        // 没有定制，在Adapter中设置了padding，则覆盖
        if (!itemBuilder.isPaddingChanged() && isParentPaddingSet) {
            itemBuilder.paddingInsets(paddingLeft, paddingTop, paddingRight, paddingBottom)
        }
        setItemList.add(itemBuilder.build())

        if (fixProp != null) {
            mItemDecorationProps.add(fixProp)
            return this
        }

        // 分割线配置
        val tempProp: DecorationProp = if (groupDecorationCount == 1) {
            groupDecorationCount += 1
            groupItemDecorationProp
        } else {
            getThemeDecorationProp()
        }
        mItemDecorationProps.add(tempProp)
        return this
    }

    fun addItems(invoker: (() -> ArrayList<SetItemBuilder>)?): SettingListBuilder {
        invoker ?: return this
        invoker.invoke().forEach {
            addItemInner(it)
        }
        return this
    }

    /*fun addItems(itemBuilders: ArrayList<SetItemBuilder>): SettingListBuilder {
        itemBuilders.forEach{
            addItem(it)
        }
        return this
    }*/

    fun addGroupItem(invoker: (() -> SetItemBuilder)?): SettingListBuilder {
        invoker ?: return this

        val builder = invoker.invoke()
        addItemInner(builder, groupItemDecorationProp)
        groupDecorationCount = groupDecorationCount % 2 + 1
        return this
    }

    /**
     * 添加一组item，该组item，与其他item之间会有一定间隙
     */
    @JvmOverloads
    fun addGroupItems(
        innerDecorationProp: DecorationProp? = null,
        invoker: (() -> ArrayList<SetItemBuilder>)?
    ): SettingListBuilder {
        invoker ?: return this

        val itemBuilders = invoker.invoke()
        for (i in 0 until itemBuilders.size) {
            val builder = itemBuilders[i]
            if (i == 0) {
                addItemInner(builder, groupItemDecorationProp)
                groupDecorationCount = groupDecorationCount % 2 + 1
            } else  {
                addItemInner(builder, innerDecorationProp ?: getThemeDecorationProp())
            }
        }

        return this
    }

    fun build(): ArrayList<SetItem> {
        bindDecoration()
        return setItemList
    }

    private fun bindDecoration() {
        if (isShowItemDecoration) {
            recyclerView.addItemDecoration(SettingItemDecoration(mItemDecorationProps))
        }
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
    }
}