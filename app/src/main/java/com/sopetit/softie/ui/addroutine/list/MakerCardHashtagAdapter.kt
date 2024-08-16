package com.sopetit.softie.ui.addroutine.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sopetit.softie.databinding.ItemAddRoutineListMakerHashtagBinding
import com.sopetit.softie.domain.entity.MakerCard

class MakerCardHashtagAdapter(private var hashtags: List<MakerCard.Hashtag>) :
    RecyclerView.Adapter<MakerCardHashtagAdapter.HashtagViewHolder>() {

    class HashtagViewHolder(private val binding: ItemAddRoutineListMakerHashtagBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(hashtag: MakerCard.Hashtag) {
            with(binding) {
                tvAddRoutineListMakerHashtagContent.text = hashtag.content
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HashtagViewHolder {
        val binding = ItemAddRoutineListMakerHashtagBinding.inflate(
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
    }
}
