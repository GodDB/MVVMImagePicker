package com.godgod.feature

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class AlbumPickerItemDecorator : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.run {
            top = 10
            left = 10
            bottom = 10
            right = 10
        }

    }
}