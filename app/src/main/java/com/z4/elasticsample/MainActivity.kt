package com.z4.elasticsample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper
import android.support.annotation.NonNull
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetBehavior.STATE_COLLAPSED
import android.support.design.widget.BottomSheetBehavior.STATE_HIDDEN
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.LinearLayout
import com.z4.elasticsample.adapters.RecyclerViewMenuAdapter
import com.z4.elasticsample.fragments.*
import com.z4.elasticsample.fragments.view_pager.ViewPagerFragment
import kotlinx.android.synthetic.main.bottom_sheet_layout.*


class MainActivity : AppCompatActivity() {
    private var bottomSheetBehavior: BottomSheetBehavior<View>? = null
    private var isMenuShown = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configure()
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    private fun configure() {
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorLightGray)

        bottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet)
        bottomSheetBehavior?.state = BottomSheetBehavior.STATE_HIDDEN

        menu_button.setOnClickListener { toggleMenu() }

        createMenu()
    }

    private fun toggleMenu() {
        shadow_view.animate().alpha(if (isMenuShown) 0.0f else 0.7f).start()
        bottomSheetBehavior?.isHideable = isMenuShown
        bottomSheetBehavior?.state = if (isMenuShown) STATE_HIDDEN else STATE_COLLAPSED

        isMenuShown = !isMenuShown
    }

    private fun createMenu() {
        val adapter = RecyclerViewMenuAdapter(resources.getStringArray(R.array.views))
        menu_recycler_view.layoutManager = LinearLayoutManager(this)
        menu_recycler_view.adapter = adapter

        adapter.onItemClick = {
            toggleMenu()

           val fragment = when (it) {
               1 -> ScrollViewFragment.newInstance()
               2 -> NestedScrollViewFragment.newInstance()
               3 -> ListViewFragment.newInstance()
               4 -> GridViewFragment.newInstance()
               5 -> ViewPagerFragment.newInstance()
               else -> RecyclerViewFragment.newInstance()
           }

            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.content_fragment, fragment)
                    .commit()
        }
    }
}
