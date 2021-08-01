package com.jamgu.settingpie.model

import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jamgu.settingpie.base.SettingAdapter
import com.jamgu.settingpie.base.SettingItemDecoration
import com.jamgu.settingpie.model.SetConstants.DEFAULT_DECORATION_GROUP_HEIGHT
import com.jamgu.settingpie.model.SetConstants.DEFAULT_DECORATION_HEIGHT
import com.jamgu.settingpie.model.SetConstants.DEFAULT_DECORATION_PROP_OUTER
import com.jamgu.settingpie.model.SetConstants.DEFAULT_DECORATION_PROP_THEME
import com.jamgu.settingpie.model.SetConstants.DEFAULT_DIVIDER_COLOR
import com.jamgu.settingpie.model.SetConstants.DEFAULT_DIVIDER_GROUP_COLOR
import com.jamgu.settingpie.model.SetConstants.DEFAULT_DIVIDER_OFFSET_X
import com.jamgu.settingpie.model.SetConstants.DEFAULT_DIVIDER_OFFSET_Y

class SetListBuilder(private val recyclerView: RecyclerView) {

    // padding of theme
    private var themePaddingProp: PaddingProp? = null

    // arrow of theme
    private var themeArrowProp: ArrowProp? = null

    private val setItemList: ArrayList<SetItem> by lazy { ArrayList() }

    private var isShowItemDecoration: Boolean = false

    /**
     * 存储item的间隔线配置
     */
    private val mItemDecorationProps: ArrayList<DecorationProp> by lazy {
        ArrayList()
    }

    /**
     * 整个页面主题的DecorationProp，即除定制外的其他ItemDecoration配置
     */
    private var themeDecorationProp: DecorationProp = DEFAULT_DECORATION_PROP_THEME

    /**
     * group间隔线配置，如果这个被配置了，添加group间隔线时会直接使用这个，不再新建DecorationProp
     */
    private var groupItemDecorationProp: DecorationProp = DEFAULT_DECORATION_PROP_OUTER

    /**
     * group间隔线数量，用于保证最终group间隔线数量成对
     */
    private var groupDecorationCount: Int = 0

    fun paddingY(paddingY: Int?): SetListBuilder {
        return paddingInset(null, paddingY, null, paddingY)
    }

    fun paddingX(paddingX: Int?): SetListBuilder {
        return paddingInset(paddingX, null, paddingX, null)
    }

    fun paddingPair(x: Int?, y: Int?): SetListBuilder {
        return paddingInset(x, y, x, y)
    }

    fun paddingAll(all: Int?): SetListBuilder {
        return paddingInset(all, all, all, all)
    }

    private fun paddingInset(
        left: Int?, top: Int?,
        right: Int?, bottom: Int?
    ): SetListBuilder {
        this.themePaddingProp = PaddingProp(left, top, right, bottom)
        return this
    }

    @JvmOverloads
    fun arrowOfTheme(isShow: Boolean?, @DrawableRes bgRes: Int? = null): SetListBuilder {
        this.themeArrowProp = ArrowProp(isShow, bgRes)
        return this
    }

    fun showDecoration(isShow: Boolean?): SetListBuilder {
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
    fun decorationOfTheme(height: Int?, offsetX: Int?, offsetY: Int?, color: String?): SetListBuilder {
        return decorationInner(height, offsetX, offsetY, color)
    }

    /**
     * call this to set decoration prop (in dp), and method [showDecoration] will be
     * called immediately with true to be set
     */
    private fun decorationInner(height: Int?, offsetX: Int?, offsetY: Int?, color: String?): SetListBuilder {
        this.themeDecorationProp = DecorationProp(
            height ?: DEFAULT_DECORATION_HEIGHT,
            offsetX ?: DEFAULT_DIVIDER_OFFSET_X,
            offsetY ?: DEFAULT_DIVIDER_OFFSET_Y,
            color ?: DEFAULT_DIVIDER_COLOR
        )
        showDecoration(true)
        return this
    }

    @JvmOverloads
    fun decorationOfGroup(height: Int?, color: String? = null) : SetListBuilder {
        this.groupItemDecorationProp = DecorationProp(
            height ?: DEFAULT_DECORATION_GROUP_HEIGHT,
            0,
            0,
            color ?: DEFAULT_DIVIDER_GROUP_COLOR
        )
        return this
    }


    fun addItem(invoker: (() -> SetItemBuilder)?): SetListBuilder {
        invoker ?: return this

        return addItemInner(invoker.invoke())
    }

    /*fun addItem(itemBuilder: SetItemBuilder): SettingListBuilder {
        // 没有定制，在Adapter中设置了padding，则覆盖
        return addItemInner(itemBuilder)
    }*/


    /**
     * add Item/Items 最终都会跑到这
     */
    private fun addItemInner(itemBuilder: SetItemBuilder?, PropWanted: DecorationProp? = null): SetListBuilder {
        itemBuilder ?: return this

        // item没有定制，在Adapter中设置了padding，则覆盖
        if (!itemBuilder.isPaddingSet()) {
            themePaddingProp?.let {
                itemBuilder.paddingPair(it.paddingLeft, it.paddingTop)
            }
        }
        // arrow配置
        if (!itemBuilder.isArrowSet()) {
            themeArrowProp?.let {
                itemBuilder.showArrow(it.isShow, it.bgRes)
            }
        }

        setItemList.add(itemBuilder.build())

        if (PropWanted != null) {
            mItemDecorationProps.add(PropWanted)
            return this
        }

        // 分割线配置
        val tempProp: DecorationProp = if (groupDecorationCount == 1) {
            groupDecorationCount += 1
            groupItemDecorationProp
        } else {
            themeDecorationProp
        }
        mItemDecorationProps.add(tempProp)
        return this
    }

    fun addItems(invoker: (() -> ArrayList<SetItemBuilder>)?): SetListBuilder {
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

    fun addGroupItem(invoker: (() -> SetItemBuilder)?): SetListBuilder {
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
    ): SetListBuilder {
        invoker ?: return this

        val itemBuilders = invoker.invoke()
        for (i in 0 until itemBuilders.size) {
            val builder = itemBuilders[i]
            if (i == 0) {
                addItemInner(builder, groupItemDecorationProp)
                groupDecorationCount = groupDecorationCount % 2 + 1
            } else  {
                addItemInner(builder, innerDecorationProp ?: themeDecorationProp)
            }
        }

        return this
    }

    fun build(): SettingAdapter {
        bindDecoration()
        val adapter = SettingAdapter()
        recyclerView.adapter = adapter
        adapter.setData(setItemList)
        return adapter
    }

    /**
     * 如果要显示分割线，则会在这里进行相应的绑定操作
     */
    private fun bindDecoration() {
        if (isShowItemDecoration) {
            recyclerView.addItemDecoration(SettingItemDecoration(mItemDecorationProps))
        }
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
    }
}