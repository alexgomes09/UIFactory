package com.alexgomes.simplecalendar

import android.graphics.Rect
import android.view.View

/**
 * Created by alexgomes on 2017-06-04.
 */

class SpaceItemDecoration : androidx.recyclerview.widget.RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: androidx.recyclerview.widget.RecyclerView, state: androidx.recyclerview.widget.RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.left = 15
        outRect.right = 15
        outRect.top = 15
        outRect.bottom = 15
    }
}
