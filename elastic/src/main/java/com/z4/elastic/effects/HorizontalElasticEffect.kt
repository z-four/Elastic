package com.z4.elastic.effects

import android.annotation.SuppressLint
import android.support.v4.view.ViewCompat
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.z4.elastic.IElasticView
import com.z4.elastic.IElasticViewBinder

class HorizontalElasticEffect(view: IElasticView) : BaseElasticEffect(view) {

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(view: View?, event: MotionEvent?): Boolean {
        if (view == null || event == null) return false
        if (!mStateAttrs.bounceEnable) return true
        mEvents.add(event.action)
        Log.wtf("aaa", "stop = " + event.action)

        mStateAttrs.currentState = event.action
        when (event.action) {

            MotionEvent.ACTION_DOWN -> {
                mMotionAttrs.originView = ViewCompat.getTranslationX(view)
                mMotionAttrs.originMotion = event.rawX
                mStateAttrs.canScrolling = mViewAttrs.canScrollHorizontal(mMotionAttrs.originLast)

                return if (mStateAttrs.canScrolling) false
                else {
                    mStateAttrs.scrollStarted = false
                    true
                }
            }

            MotionEvent.ACTION_MOVE -> {
                if (!mEvents.contains(MotionEvent.ACTION_DOWN)) {
                    mMotionAttrs.originView = ViewCompat.getTranslationX(view)
                    mMotionAttrs.originMotion = event.rawX
                    mStateAttrs.canScrolling = mViewAttrs.canScrollHorizontal(mMotionAttrs.originLast)

                    mEvents.add(MotionEvent.ACTION_DOWN)
                }
                mMotionAttrs.originLast = (event.rawX - mMotionAttrs.originMotion) /
                        DEFAULT_DRAG_RATIO_VALUE

                mStateAttrs.canScrolling = mViewAttrs.canScrollHorizontal(mMotionAttrs.originLast)
                mStateAttrs.scrollStarted = Math.abs(mMotionAttrs.originLast) >= MIN_DRAG_VALUE

                if (mStateAttrs.canScrolling) return false
                if (mStateAttrs.scrollStarted) {
                    val value = mMotionAttrs.originView + mMotionAttrs.originLast
                    val state = if (value > 0) IElasticViewBinder.State.DraggingEnd else
                        IElasticViewBinder.State.DraggingStart
                    if (mOldState != state) {
                        listener?.onStateChanged(state)
                        mOldState = state
                    }

                    ViewCompat.setTranslationX(view, value)
                }
                return true
            }

            MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP -> {
                if (!mEvents.contains(MotionEvent.ACTION_DOWN)) {
                    mEvents.clear()
                    return true
                }
                mEvents.clear()
                if (mStateAttrs.canScrolling) {
                    if (mStateAttrs.currentState != MotionEvent.ACTION_MOVE) return false
                    return !mStateAttrs.scrollStarted
                }

                if (mStateAttrs.scrollStarted) {
                    val anim = createAnimator(view, View.TRANSLATION_X)
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