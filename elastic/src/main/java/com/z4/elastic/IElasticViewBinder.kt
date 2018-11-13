package com.z4.elastic

interface IElasticViewBinder {

    interface StateListener {
        fun onStateChanged(state: State)
    }

    enum class State {
        Idle,
        Bounce,
        DraggingStart,
        DraggingEnd,
    }

    var listener: StateListener?

    fun bind(): IElasticViewBinder
    fun unbind()
}