package com.sopetit.softie.util

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class HorizontalItemDecoration(
    val context: Context,
    val firstItemMargin: Int,
    val itemMargin: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)

        if (position == 0) {
            outRect.left = context.resources.getDimensionPixelSize(firstItemMargin)
        } else {
            outRect.left = 0
        }
        outRect.right = context.resources.getDimensionPixelSize(itemMargin)
    }
}
