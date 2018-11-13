package com.z4.elasticsample.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.z4.elastic.IElasticViewBinder
import com.z4.elastic.elastic
import com.z4.elasticsample.R
import com.z4.elasticsample.adapters.ListViewAdapter
import com.z4.elasticsample.adapters.RecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_list_view.*
import kotlinx.android.synthetic.main.fragment_recycler_view.*


class ListViewFragment : Fragment() {
    private var elasticViewBinder: IElasticViewBinder? = null

    companion object {
        fun newInstance(): Fragment = ListViewFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list_view.adapter = ListViewAdapter(items = getItems())

        elasticViewBinder = list_view.elastic().bind()
        elasticViewBinder?.listener = object : IElasticViewBinder.StateListener {

            override fun onStateChanged(state: IElasticViewBinder.State) {
                when(state) {
                    IElasticViewBinder.State.Idle -> Log.wtf("aa", "Idle")
                    IElasticViewBinder.State.Bounce -> Log.wtf("aa", "Bounce")
                    IElasticViewBinder.State.DraggingStart -> Log.wtf("aa", "DraggingStart")
                    IElasticViewBinder.State.DraggingEnd -> Log.wtf("aa", "DraggingEnd")
                }
            }
        }
    }

    override fun onDestroyView() {
        elasticViewBinder?.unbind()
        super.onDestroyView()
    }

    private fun getItems(): List<String> {
        val list = ArrayList<String>()
        list.add("What the mind can conceive and believe, it can achieve.")
        list.add("The minute you settle for less than you deserve, you get even less than you settled for.")
        list.add("Perseverance is not a long race; it is many short races one after the other.")
        list.add("Poverty was the greatest motivating factor in my life.")
        list.add("If you can dream it, you can do it.")
        list.add("If you want to conquer fear, don’t sit home and think about it. Go out and get busy.")
        list.add("If you love life, do not waste time, for time is what life is made up of.")
        list.add("f you don’t set a baseline standard for what you’ll accept in life, you’ll find" +
                " it’s easy to slip into behaviors and attitudes or a quality of life that’s far below what you deserve.")
        list.add("Work joyfully and peacefully, knowing that right thoughts and right efforts will inevitably bring about right results.")

        return list
    }
}