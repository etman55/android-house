package com.atef.clubhouse.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.atef.clubhouse.databinding.ItemChannelBinding
import com.atef.clubhouse.domain.entity.channels.Channel
import com.atef.clubhouse.utils.ImageLoader
import com.atef.clubhouse.utils.adapterProperty

class ChannelsAdapter(
    private val imageLoader: ImageLoader,
    private val onClick: (Channel) -> Unit,
) : RecyclerView.Adapter<ChannelsAdapter.ViewHolder>() {
    var items: List<Channel> by adapterProperty(emptyList())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemChannelBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            imageLoader,
            onClick)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    class ViewHolder(
        private val binding: ItemChannelBinding,
        private val imageLoader: ImageLoader,
        val onClick: (Channel) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(channel: Channel) {
            with(binding) {
                itemView.setOnClickListener { onClick(channel) }
                channel.users.get(0).photoUrl?.let { imageLoader.loadCircleImage(firstUserImg, it) }
                channel.users.get(1).photoUrl?.let { imageLoader.loadCircleImage(secondUserImg, it) }
                var channelsUsersAdapter : ChannelUsersAdapter = ChannelUsersAdapter()
                channelsUsersAdapter?.items = channel.users
                speakersList.apply {
                    adapter = channelsUsersAdapter
                    setHasFixedSize(true)
                }
                channelTopicTxt.text = channel.topic
                numberOfMembersTxt.text = channel.numAll.toString()
                numberOfSpeakersTxt.text = "/  ".plus(channel.numSpeakers.toString())
            }
        }
    }
}