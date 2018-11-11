package com.z4.elasticsample.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.z4.elasticsample.R

class GridViewAdapter(private val count: Int = 10) : BaseAdapter() {

    override fun getCount(): Int {
        return count
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        var convertView = view
        val context = parent.context
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.grid_view_item_layout, parent,
                    false)
        }
        convertView?.findViewById<TextView>(R.id.text_view)?.text = "Item $position"

        return convertView!!
    }
}