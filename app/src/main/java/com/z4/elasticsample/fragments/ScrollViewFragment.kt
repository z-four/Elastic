package com.z4.elasticsample.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.z4.elastic.IElasticViewBinder
import com.z4.elastic.elastic
import com.z4.elasticsample.R
import kotlinx.android.synthetic.main.fragment_scroll_view.*


class ScrollViewFragment : Fragment() {
    private var horizontalScrollViewElasticBinder: IElasticViewBinder? = null
    private var verticalScrollViewElasticBinder: IElasticViewBinder? = null

    companion object {
        fun newInstance(): Fragment = ScrollViewFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_scroll_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        horizontalScrollViewElasticBinder = horizontal_scroll_view.elastic().bind()
        verticalScrollViewElasticBinder = vertical_scroll_view.elastic().bind()
    }

    override fun onDestroyView() {
        horizontalScrollViewElasticBinder?.unbind()
        verticalScrollViewElasticBinder?.unbind()
        super.onDestroyView()
    }
}