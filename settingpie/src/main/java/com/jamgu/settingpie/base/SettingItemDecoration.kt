package com.jamgu.settingpie.base

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.jamgu.settingpie.model.DecorationProp
import com.jamgu.settingpie.util.dp2px

class SettingItemDecoration(val props: ArrayList<DecorationProp>): RecyclerView.ItemDecoration() {

    private val TAG = "SettingItemDecoration"

    private val mPaint: Paint by lazy { Paint() }
    private var isPaintInit: Boolean = false

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        // 绕过第一个
        val adapterPosition = parent.getChildAdapterPosition(view)
        if (adapterPosition != 0) {
            props[adapterPosition].apply {
                outRect.top = height?.dp2px(parent.context) ?: 0
            }
        }
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)

        if (!isPaintInit) {
            mPaint.isAntiAlias = true
            isPaintInit = true
        }


        for (i in 0 until parent.childCount) {
            val view = parent.getChildAt(i)
            val globalPosition = parent.getChildAdapterPosition(view)
            Log.d(TAG, "jamgu -> view : child pos = $i, adapterPos = $globalPosition")
            // 第一个不需要绘制
            if (globalPosition == 0) continue

            if (globalPosition > 0) {
                props[globalPosition].apply {
                    val fixOffsetX = offsetX?.dp2px(parent.context) ?: 0
                    val fixHeight = height?.dp2px(parent.context) ?: 0
                    val fixOffsetY = offsetY?.dp2px(parent.context) ?: 0

                    val dividerLeft = parent.paddingLeft + fixOffsetX
                    val dividerTop = view.top - fixHeight
                    val dividerRight = parent.width - parent.paddingRight + fixOffsetY
                    val dividerBottom = view.top

                    // 设置线的颜色
                    mPaint.color = Color.parseColor(decorationColor)
                    // 画线
                    c.drawRect(dividerLeft.toFloat(), dividerTop.toFloat(),
                        dividerRight.toFloat(), dividerBottom.toFloat(), mPaint)
                }
            }
        }

    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
    }

}