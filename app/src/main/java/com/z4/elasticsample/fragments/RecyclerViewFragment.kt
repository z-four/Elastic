package com.z4.elasticsample.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.z4.elastic.IElasticViewBinder
import com.z4.elastic.elastic
import com.z4.elasticsample.R
import com.z4.elasticsample.adapters.RecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_recycler_view.*


class RecyclerViewFragment: Fragment() {
    private var elasticViewBinder: IElasticViewBinder? = null

    companion object {
        fun newInstance(): Fragment = RecyclerViewFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recycler_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler_view.adapter = RecyclerViewAdapter(10)
        recycler_view.layoutManager = LinearLayoutManager(context)

        elasticViewBinder = recycler_view.elastic().bind()
    }

    override fun onDestroyView() {
        elasticViewBinder?.unbind()
        super.onDestroyView()
    }
}