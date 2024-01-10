package com.sopetit.softie.ui.main.happy.addlist

import androidx.recyclerview.widget.RecyclerView
import com.sopetit.softie.databinding.ItemHappyAddListBinding

class HappyAddListContentViewHolder(private val binding: ItemHappyAddListBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(HappyAddListContentData: HappyAddListContent) {
        binding.tvHappyListItemTitle.text = HappyAddListContentData.title
        binding.tvHappyListItemContent.text = HappyAddListContentData.content
        binding.ivHappyListItemIcon.setImageResource(HappyAddListContentData.imageUrl)
    }
}
