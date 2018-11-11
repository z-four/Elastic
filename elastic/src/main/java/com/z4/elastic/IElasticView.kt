package com.z4.elastic

import android.view.View

interface IElasticView {
    enum class ScrollDirection {
        UP, DOWN, LEFT, RIGHT
    }

    fun getView(): View
    fun canScrollVertical(dir: ScrollDirection): Boolean
    fun canScrollHorizontal(dir: ScrollDirection): Boolean
}