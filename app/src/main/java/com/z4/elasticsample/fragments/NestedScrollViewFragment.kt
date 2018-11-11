package com.z4.elasticsample.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.z4.elastic.IElasticViewBinder
import com.z4.elastic.elastic
import com.z4.elasticsample.R
import kotlinx.android.synthetic.main.fragment_nested_scroll_view.*


class NestedScrollViewFragment : Fragment() {
    private var elasticViewBinder: IElasticViewBinder? = null

    companion object {
        fun newInstance(): Fragment = NestedScrollViewFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_nested_scroll_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        elasticViewBinder = nested_scroll_view.elastic().bind()
    }

    override fun onDestroyView() {
        elasticViewBinder?.unbind()
        super.onDestroyView()
    }
}