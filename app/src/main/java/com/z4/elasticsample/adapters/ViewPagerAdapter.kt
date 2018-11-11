package com.z4.elasticsample.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.z4.elasticsample.fragments.view_pager.ViewPagerItemFragment

class ViewPagerAdapter(var manager: FragmentManager): FragmentPagerAdapter(manager) {

    override fun getItem(position: Int): Fragment {
        return ViewPagerItemFragment.newInstance(position)
    }

    override fun getCount(): Int = 3
}