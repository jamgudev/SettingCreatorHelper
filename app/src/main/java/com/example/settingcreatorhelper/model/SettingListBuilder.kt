package com.example.settingcreatorhelper.model

import com.example.settingcreatorhelper.model.SettingConstants.DEFAULT_PADDING_BOTTOM
import com.example.settingcreatorhelper.model.SettingConstants.DEFAULT_PADDING_LEFT
import com.example.settingcreatorhelper.model.SettingConstants.DEFAULT_PADDING_RIGHT
import com.example.settingcreatorhelper.model.SettingConstants.DEFAULT_PADDING_TOP

class SettingListBuilder {
    private var paddingLeft = DEFAULT_PADDING_LEFT
    private var paddingTop = DEFAULT_PADDING_TOP
    private var paddingRight = DEFAULT_PADDING_RIGHT
    private var paddingBottom = DEFAULT_PADDING_BOTTOM

    private val setItemList: ArrayList<SetItem> by lazy { ArrayList() }

    private var isParentPaddingSet: Boolean = false

    fun paddingY(paddingY: Int): SettingListBuilder {
        paddingInset(DEFAULT_PADDING_LEFT, top = paddingY, DEFAULT_PADDING_RIGHT, bottom = paddingY)
        return this
    }

    fun paddingX(paddingX: Int): SettingListBuilder {
        paddingInset(left = paddingX, DEFAULT_PADDING_TOP, right = paddingX, DEFAULT_PADDING_BOTTOM)
        return this
    }

    fun paddingPair(x: Int, y: Int): SettingListBuilder {
        paddingInset(x, y, x, y)
        return this
    }

    fun paddingAll(all: Int): SettingListBuilder {
        paddingInset(all, all, all, all)
        return this
    }

    fun paddingInset(
        left: Int, top: Int,
        right: Int, bottom: Int
    ): SettingListBuilder {
        isParentPaddingSet = true
        paddingTop = top
        paddingBottom = bottom
        paddingLeft = left
        paddingRight = right
        return this
    }

    fun addItem(invoker: () -> SetItemBuilder): SettingListBuilder {
        addItem(invoker.invoke())
        return this
    }

    private fun addItem(itemBuilder: SetItemBuilder): SettingListBuilder {
        // 没有定制，在Adapter中设置了padding，则覆盖
        if (!itemBuilder.isPaddingChanged() && isParentPaddingSet) {
            itemBuilder.paddingInsets(paddingLeft, paddingTop, paddingRight, paddingBottom)
        }
        setItemList.add(itemBuilder.build())
        return this
    }

    fun addItems(invoker: () -> ArrayList<SetItemBuilder>) {
        invoker.invoke().forEach{
            addItem(it)
        }
    }

    fun build(): ArrayList<SetItem> {
        return setItemList
    }

    /*fun addItems(itemBuilders: ArrayList<SetItemBuilder>): SettingListBuilder {
        itemBuilders.forEach{
            addItem(it)
        }
        return this
    }*/

    /**
     * 添加一组item，改组item，与其他item之间会有一定间隙
     */
    fun group(itemBuilders: ArrayList<SetItemBuilder>): SettingListBuilder {
        // TODO group实现
        return this
    }


}