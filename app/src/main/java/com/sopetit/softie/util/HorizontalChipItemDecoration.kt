package com.sopetit.softie.util

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.sopetit.softie.R

class HorizontalChipItemDecoration(val context: Context) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)

        if (position == 0) {
            outRect.left = context.resources.getDimensionPixelSize(R.dimen.chip_first_margin)
        } else {
            outRect.left = 0
        }
        outRect.right = context.resources.getDimensionPixelSize(R.dimen.chip_margin)
    }
}
