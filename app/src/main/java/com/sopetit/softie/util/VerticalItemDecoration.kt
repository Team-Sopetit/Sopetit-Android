package com.sopetit.softie.util

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.sopetit.softie.R

class VerticalItemDecoration(val context: Context) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)

        if (position == 0) {
            outRect.top = context.resources.getDimensionPixelSize(R.dimen.happy_list_first_margin)
        } else {
            outRect.top = 0
        }
        outRect.bottom = context.resources.getDimensionPixelSize(R.dimen.happy_list_margin)
    }
}
