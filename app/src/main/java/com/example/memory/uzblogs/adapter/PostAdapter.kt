package com.example.memory.uzblogs.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.memory.uzblogs.R
import com.example.memory.uzblogs.model.PostModel
import kotlinx.android.synthetic.main.post_item_layout.view.*
import kotlinx.android.synthetic.main.user_item_layout.view.*

/**
 * @author Zokirjon
 * @date 16.08.2021
 */

interface PostAdapterListener {
    fun onClick(item: PostModel)
}

class PostAdapter(val items: List<PostModel>,val adapterListener: PostAdapterListener): RecyclerView.Adapter<PostAdapter.ItemHolder>() {
    inner class ItemHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.post_item_layout, parent, false))
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item = items[position]

        holder.itemView.tvTitle.text = item.text
        holder.itemView.tvDate.text = item.publishDate
        holder.itemView.setOnClickListener {
            adapterListener.onClick(item)
        }

        Glide.with(holder.itemView.context).load(item.image).into(holder.itemView.imgPost)

    }

    override fun getItemCount(): Int {
        return items.size //count() can be used here
    }
}