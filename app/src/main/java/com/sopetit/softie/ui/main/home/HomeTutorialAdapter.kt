package com.sopetit.softie.ui.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sopetit.softie.databinding.ItemHomeTutorialBinding
import com.sopetit.softie.domain.entity.Tutorial
import com.sopetit.softie.util.ItemDiffCallback

class HomeTutorialAdapter :
    ListAdapter<Tutorial, HomeTutorialViewHolder>(
        ItemDiffCallback<Tutorial>(
            onItemsTheSame = { oldItem, newItem -> oldItem.tutorialId == newItem.tutorialId },
            onContentsTheSame = { oldItem, newItem -> oldItem == newItem }
        )
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeTutorialViewHolder {
        val binding = ItemHomeTutorialBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HomeTutorialViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeTutorialViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}

class HomeTutorialViewHolder(
    private val binding: ItemHomeTutorialBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(data: Tutorial) {
        binding.ivTutorialItem.setImageResource(data.content)
    }
}
