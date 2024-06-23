package com.sopetit.softie.ui.addroutine.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sopetit.softie.databinding.ItemAddListMakerHashtagChipBinding
import com.sopetit.softie.domain.entity.MakerCard

class MakerCardPagerHashtagAdapter(private var hashtags: List<MakerCard.Hashtag>) :
    RecyclerView.Adapter<MakerCardPagerHashtagAdapter.HashtagViewHolder>() {

    class HashtagViewHolder(private val binding: ItemAddListMakerHashtagChipBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(hashtag: MakerCard.Hashtag) {
            with(binding) {
                tvAddListItemContent.text = hashtag.content
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HashtagViewHolder {
        val binding = ItemAddListMakerHashtagChipBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HashtagViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HashtagViewHolder, position: Int) {
        holder.onBind(hashtags[position])
    }

    override fun getItemCount(): Int = hashtags.size

    fun submitList(newHashtags: List<MakerCard.Hashtag>) {
        hashtags = newHashtags
        notifyDataSetChanged()
    }
}
