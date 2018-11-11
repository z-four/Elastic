package com.z4.elastic.effects

import android.animation.Animator
import android.animation.ObjectAnimator
import android.util.Property
import android.view.MotionEvent
import android.view.View
import android.view.animation.DecelerateInterpolator
import com.z4.elastic.IElasticViewBinder
import com.z4.elastic.IElasticView
import com.z4.elastic.IElasticView.ScrollDirection.*

abstract class BaseElasticEffect(view: IElasticView) : IElasticViewBinder, View.OnTouchListener,
        Animator.AnimatorListener {

    companion object {
        const val ANIMATION_DURATION = 450L
        const val MIN_DRAG_VALUE = 15f
        const val DEFAULT_DRAG_RATIO_VALUE = 4.5f
    }

    class MotionAttrs {
        var originMotion = 0f
        var originView = 0f
        var originLast = 0f
    }

    class StateAttrs {
        var bounceEnable = true
        var scrollStarted = false
        var canScrolling = false
        var currentState = MotionEvent.ACTION_CANCEL
    }

    class ViewAttrs(view: IElasticView) {
        private val mView = view

        fun getView(): View = mView.getView()

        fun canScrollVertical(originY: Float): Boolean {
            return ((mView.canScrollVertical(UP) && originY > 0) ||
                    (mView.canScrollVertical(DOWN) && originY < 0))
        }

        fun canScrollHorizontal(originX: Float): Boolean {
            return ((mView.canScrollHorizontal(LEFT) && originX > 0) ||
                    (mView.canScrollHorizontal(RIGHT) && originX < 0))
        }
    }

    private val interpolator = DecelerateInterpolator()
    protected val mStateAttrs = StateAttrs()
    protected val mMotionAttrs = MotionAttrs()
    protected val mViewAttrs: ViewAttrs

    init { mViewAttrs = ViewAttrs(view) }

    //--- OnTouch listener

    override fun onTouch(view: View?, event: MotionEvent?) = false

    //--- Animation

    protected fun createAnimator(view: View, property: Property<View, Float>, value: Float = 0.0f): ObjectAnimator {
        val bounceBackAnim = ObjectAnimator.ofFloat(view, property, value)
        bounceBackAnim.duration = ANIMATION_DURATION
        bounceBackAnim.interpolator = interpolator
        return bounceBackAnim
    }

    override fun onAnimationRepeat(p0: Animator?) {}
    override fun onAnimationCancel(p0: Animator?) {}
    override fun onAnimationEnd(p0: Animator?) { mStateAttrs.bounceEnable = true }
    override fun onAnimationStart(p0: Animator?) { mStateAttrs.bounceEnable = false }

    //--- IBinder

    override fun bind(): IElasticViewBinder {
        mViewAttrs.getView().setOnTouchListener(this)
        return this
    }

    override fun unbind() {
        if (mStateAttrs.currentState != MotionEvent.ACTION_CANCEL ||
                mStateAttrs.currentState != MotionEvent.ACTION_UP && mStateAttrs.bounceEnable) {
            mViewAttrs.getView().setOnTouchListener(null)
        }
    }
}