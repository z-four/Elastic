package com.z4.elasticsample.fragments.view_pager

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.z4.elastic.IElasticViewBinder
import com.z4.elastic.elastic
import com.z4.elasticsample.R
import com.z4.elasticsample.adapters.ViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_view_pager.*


class ViewPagerFragment : Fragment() {
    private var elasticViewBinder: IElasticViewBinder? = null

    companion object {
        fun newInstance(): Fragment = ViewPagerFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_view_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view_pager.adapter = ViewPagerAdapter(childFragmentManager)

        elasticViewBinder = view_pager.elastic().bind()
    }

    override fun onDestroyView() {
        elasticViewBinder?.unbind()
        super.onDestroyView()
    }
}