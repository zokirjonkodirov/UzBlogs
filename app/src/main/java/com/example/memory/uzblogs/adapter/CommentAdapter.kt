package com.example.memory.uzblogs.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.memory.uzblogs.R
import com.example.memory.uzblogs.model.CommentModel
import kotlinx.android.synthetic.main.comment_item_layout.view.*

/**
 * @author user
 * @date 18.08.2021
 */
class CommentAdapter(val items: List<CommentModel>): RecyclerView.Adapter<CommentAdapter.ItemHOlder>() {
    inner class ItemHOlder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHOlder {
        return ItemHOlder(LayoutInflater.from(parent.context).inflate(R.layout.comment_item_layout, parent, false))
    }

    override fun onBindViewHolder(holder: ItemHOlder, position: Int) {
        val item = items[position]

        Glide.with(holder.itemView.context).load(item.owner.picture).into(holder.itemView.userAvatar)

        holder.itemView.userName.text = item.owner.firstName
        holder.itemView.commentText.text = item.message
    }

    override fun getItemCount(): Int {
        return items.size
    }
}