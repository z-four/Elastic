package com.z4.elasticsample.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.z4.elasticsample.R
import kotlinx.android.synthetic.main.recycler_view_menu_item_layout.view.*

class RecyclerViewMenuAdapter(private val items: Array<String>) :
        RecyclerView.Adapter<RecyclerViewMenuAdapter.ViewHolder>() {
    var onItemClick: ((index: Int) -> Unit)? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_view_menu_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.text_view.text = items[position]
        holder.itemView.setOnClickListener { onItemClick?.let { it1 -> it1(position) } }
    }

    override fun getItemCount() = items.size
}