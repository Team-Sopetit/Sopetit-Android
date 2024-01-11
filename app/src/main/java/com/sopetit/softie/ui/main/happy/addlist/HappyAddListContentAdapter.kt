package com.sopetit.softie.ui.main.happy.addlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sopetit.softie.databinding.ItemHappyAddListBinding
import com.sopetit.softie.domain.entity.HappyContent
import com.sopetit.softie.util.ItemDiffCallback

class HappyAddListContentAdapter :
    ListAdapter<HappyContent, HappyAddListContentAdapter.HappyAddListContentViewHolder>(
        ItemDiffCallback<HappyContent>(
            onItemsTheSame = { oldItem, newItem -> oldItem == newItem },
            onContentsTheSame = { oldItem, newItem -> oldItem == newItem }
        )
    ) {

    class HappyAddListContentViewHolder(private val binding: ItemHappyAddListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(happyContentData: HappyContent) {
            binding.tvHappyListItemTitle.text = happyContentData.title
            binding.tvHappyListItemContent.text = happyContentData.content
            binding.ivHappyListItemIcon.setImageResource(happyContentData.imageUrl)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HappyAddListContentViewHolder {
        val binding = ItemHappyAddListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return HappyAddListContentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HappyAddListContentViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }
}
