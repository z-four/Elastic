package com.z4.elasticsample.fragments.view_pager

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.z4.elasticsample.R
import android.R.attr.defaultValue
import android.R.attr.key
import android.graphics.Color
import kotlinx.android.synthetic.main.fragment_view_pager_item.*


class ViewPagerItemFragment : Fragment() {
    private var index = 1

    companion object {
        fun newInstance(index: Int): Fragment {
            val fragment = ViewPagerItemFragment()
            val bundle = Bundle()
            bundle.putInt("index", index)
            fragment.arguments = bundle

            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            index = it.getInt("index", 1)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_view_pager_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        text_view.text = "Item $index"
        val color = when(index) {
            1 -> Color.parseColor("#ff7373")
            0 -> Color.parseColor("#3399ff")
            else -> Color.parseColor("#66cdaa")
        }

        container.setBackgroundColor(color)
    }
}