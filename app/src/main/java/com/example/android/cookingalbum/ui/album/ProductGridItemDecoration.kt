package com.example.android.cookingalbum.ui.album

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ProductGridItemDecoration (private val rightPadding: Int, private val leftPadding: Int, private val topPadding: Int)
    : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View,
                                parent: RecyclerView, state: RecyclerView.State) {
        outRect.left = leftPadding
        outRect.right = rightPadding
        outRect.top = topPadding
    }
}