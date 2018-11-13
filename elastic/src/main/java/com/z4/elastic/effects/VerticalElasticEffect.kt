package com.z4.elastic.effects

import android.support.v4.view.ViewCompat
import android.view.MotionEvent
import android.view.View
import com.z4.elastic.IElasticView
import com.z4.elastic.IElasticViewBinder

class VerticalElasticEffect(view: IElasticView) : BaseElasticEffect(view) {

    override fun onTouch(view: View?, event: MotionEvent?): Boolean {
        if (view == null || event == null) return false
        if (!mStateAttrs.bounceEnable) return true

        mStateAttrs.currentState = event.action

        when (event.action) {

            MotionEvent.ACTION_DOWN -> {
                mMotionAttrs.originView = ViewCompat.getTranslationY(view)
                mMotionAttrs.originMotion = event.rawY

                mStateAttrs.canScrolling = mViewAttrs.canScrollVertical(mMotionAttrs.originLast)

                return if (mStateAttrs.canScrolling) false
                else {
                    mStateAttrs.scrollStarted = false
                    true
                }
            }

            MotionEvent.ACTION_MOVE -> {
                mMotionAttrs.originLast = (event.rawY - mMotionAttrs.originMotion) /
                        DEFAULT_DRAG_RATIO_VALUE

                mStateAttrs.canScrolling = mViewAttrs.canScrollVertical(mMotionAttrs.originLast)
                mStateAttrs.scrollStarted = Math.abs(mMotionAttrs.originLast) >= MIN_DRAG_VALUE

                if (mStateAttrs.canScrolling) return false
                view.parent?.requestDisallowInterceptTouchEvent(true)

                if (mStateAttrs.scrollStarted) {
                    val value = mMotionAttrs.originView + mMotionAttrs.originLast
                    val state = if (value > 0) IElasticViewBinder.State.DraggingEnd else
                        IElasticViewBinder.State.DraggingStart
                    if (mOldState != state) {
                        listener?.onStateChanged(state)
                        mOldState = state
                    }
                    ViewCompat.setTranslationY(view, value)
                }
                return true
            }

            MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP -> {
                if (mStateAttrs.canScrolling) {
                    if (mStateAttrs.currentState != MotionEvent.ACTION_MOVE) return false
                    return !mStateAttrs.scrollStarted
                }

                if (mStateAttrs.scrollStarted) {
                    val anim = createAnimator(view, View.TRANSLATION_Y)
                    anim.addListener(this)
                    anim.start()

                    mStateAttrs.scrollStarted = false
                    return true
                }
                return false
            }
        }
        return true
    }
}