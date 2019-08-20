package com.z4.elastic

import android.support.v4.view.ViewPager
import android.support.v4.widget.NestedScrollView
import android.support.v7.widget.RecyclerView
import android.widget.AbsListView
import android.widget.HorizontalScrollView
import android.widget.ScrollView
import com.z4.elastic.effects.HorizontalElasticEffect
import com.z4.elastic.effects.VerticalElasticEffect

enum class ElasticEffect {
    VERTICAL, HORIZONTAL
}

fun RecyclerView.elastic(effect: ElasticEffect = ElasticEffect.VERTICAL): IElasticViewBinder {
    if (effect == ElasticEffect.VERTICAL)  {
        return VerticalElasticEffect((object : IElasticView {

            override fun getView() = this@elastic

            override fun canScrollVertical(dir: IElasticView.ScrollDirection): Boolean {
                return if (dir == IElasticView.ScrollDirection.UP) canScrollVertically(-1)
                else canScrollVertically(1)
            }

            override fun canScrollHorizontal(dir: IElasticView.ScrollDirection): Boolean = false
        }))
    } else {
        return HorizontalElasticEffect((object : IElasticView {

            override fun getView() = this@elastic

            override fun canScrollVertical(dir: IElasticView.ScrollDirection): Boolean = false

            override fun canScrollHorizontal(dir: IElasticView.ScrollDirection): Boolean {
                return if (dir == IElasticView.ScrollDirection.LEFT) canScrollHorizontally(-1)
                else canScrollHorizontally(1)
            }
        }))
    }
}

fun AbsListView.elastic(): IElasticViewBinder {
    return VerticalElasticEffect((object : IElasticView {

        override fun getView() = this@elastic

        override fun canScrollVertical(dir: IElasticView.ScrollDirection): Boolean {
            return childCount > 0 && (if (dir == IElasticView.ScrollDirection.UP)
                canScrollListUp() else canScrollListDown())
        }

        override fun canScrollHorizontal(dir: IElasticView.ScrollDirection): Boolean = false

        fun canScrollListUp(): Boolean {
            val firstTop = getChildAt(0).top
            val firstPosition = firstVisiblePosition
            return firstPosition > 0 || firstTop < listPaddingTop
        }

        fun canScrollListDown(): Boolean {
            val childCount = childCount
            val itemsCount = count
            val firstPosition = firstVisiblePosition
            val lastPosition = firstPosition + childCount
            val lastBottom = getChildAt(childCount - 1).bottom
            return lastPosition < itemsCount || lastBottom > height - listPaddingBottom
        }
    }))
}

fun NestedScrollView.elastic(): IElasticViewBinder {
    return VerticalElasticEffect(object : IElasticView {

        override fun getView() = this@elastic

        override fun canScrollVertical(dir: IElasticView.ScrollDirection): Boolean {
            return if (dir == IElasticView.ScrollDirection.UP) canScrollVertically(-1)
            else canScrollVertically(1)
        }

        override fun canScrollHorizontal(dir: IElasticView.ScrollDirection): Boolean = false
    })
}

fun HorizontalScrollView.elastic(): IElasticViewBinder {
    return HorizontalElasticEffect((object : IElasticView {

        override fun getView() = this@elastic

        override fun canScrollVertical(dir: IElasticView.ScrollDirection) = false

        override fun canScrollHorizontal(dir: IElasticView.ScrollDirection): Boolean {
            return if (dir == IElasticView.ScrollDirection.LEFT) canScrollHorizontally(-1)
            else canScrollHorizontally(1)
        }
    }))
}

fun ScrollView.elastic(): IElasticViewBinder {

    return VerticalElasticEffect(object : IElasticView {

        override fun getView() = this@elastic

        override fun canScrollVertical(dir: IElasticView.ScrollDirection): Boolean {
            return if (dir == IElasticView.ScrollDirection.UP) canScrollVertically(-1)
            else canScrollVertically(1)
        }

        override fun canScrollHorizontal(dir: IElasticView.ScrollDirection): Boolean = false
    })
}

fun ViewPager.elastic(): IElasticViewBinder {
    var currPosition = 0

    addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

        override fun onPageScrollStateChanged(state: Int) {}

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

        override fun onPageSelected(position: Int) {
            currPosition = position
        }
    })

    return HorizontalElasticEffect(object : IElasticView {

        override fun getView() = this@elastic

        override fun canScrollVertical(dir: IElasticView.ScrollDirection): Boolean = false

        override fun canScrollHorizontal(dir: IElasticView.ScrollDirection): Boolean {
            return if (dir == IElasticView.ScrollDirection.LEFT) currPosition != 0
            else currPosition != adapter!!.count - 1
        }
    })
}