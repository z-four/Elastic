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
import com.z4.elasticsample.adapters.GridViewAdapter
import com.z4.elasticsample.adapters.ListViewAdapter
import com.z4.elasticsample.adapters.RecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_grid_view.*
import kotlinx.android.synthetic.main.fragment_list_view.*
import kotlinx.android.synthetic.main.fragment_recycler_view.*


class GridViewFragment : Fragment() {
    private var elasticViewBinder: IElasticViewBinder? = null

    companion object {
        fun newInstance(): Fragment = GridViewFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_grid_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        grid_view.adapter = GridViewAdapter()

        elasticViewBinder = grid_view.elastic().bind()
    }

    override fun onDestroyView() {
        elasticViewBinder?.unbind()
        super.onDestroyView()
    }
}