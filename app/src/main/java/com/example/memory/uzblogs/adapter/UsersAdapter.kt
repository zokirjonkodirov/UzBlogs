package com.example.memory.uzblogs.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.memory.uzblogs.R
import com.example.memory.uzblogs.model.UserModel
import kotlinx.android.synthetic.main.user_item_layout.view.*

/**
 * @author Zokirjon
 * @date 16.08.2021
 */

interface UserAdapterListener {
    fun onClick(item: UserModel)
}

class UsersAdapter(val items: List<UserModel>, val adapterListener: UserAdapterListener): RecyclerView.Adapter<UsersAdapter.ItemHolder>() {
    inner class ItemHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.user_item_layout, parent, false))
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item = items[position]
        holder.itemView.tvName.text = item.firstName

        holder.itemView.setOnClickListener {
            adapterListener.onClick(item)
        }

        Glide.with(holder.itemView.context).load(item.picture).into(holder.itemView.imageUser)

    }

    override fun getItemCount(): Int {
        return items.size
    }
}