package com.p2lem8dev.todo.helpers.decorators

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.roundToInt

class RecyclerViewItemOffsetDecorator : RecyclerView.ItemDecoration {

    private val offset: Int

    constructor(offset: Int) {
        this.offset = offset
    }
    constructor(context: Context, itemOffsetId: Int) {
        this.offset = context.resources.getDimension(itemOffsetId).roundToInt()
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.set(0, offset, 0, offset)
    }
}