package com.atef.clubhouse.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.atef.clubhouse.databinding.ItemChannelUserBinding
import com.atef.clubhouse.domain.entity.auth.User
import com.atef.clubhouse.utils.adapterProperty

class ChannelUsersAdapter(
) : RecyclerView.Adapter<ChannelUsersAdapter.ViewHolder>() {
    var items: List<User> by adapterProperty(emptyList())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemChannelUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = if (items.size>=3) 3 else items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    class ViewHolder(
            private val binding: ItemChannelUserBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            with(binding) {
                userNameTxt.text = user.name
            }
        }
    }
}