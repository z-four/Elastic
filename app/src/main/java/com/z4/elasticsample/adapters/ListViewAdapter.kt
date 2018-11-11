package com.z4.elasticsample.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.z4.elasticsample.R


class ListViewAdapter(private val items: List<String>) : BaseAdapter() {

    override fun getCount(): Int {
        return items.size
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
            convertView = LayoutInflater.from(context).inflate(R.layout.list_view_item_layout, parent,
                    false)
        }

        convertView?.findViewById<TextView>(R.id.title_text_view)?.text = context.resources.getString(R.string.phrase)
        convertView?.findViewById<TextView>(R.id.description_text_view)?.text = items[position]

        return convertView!!
    }
}