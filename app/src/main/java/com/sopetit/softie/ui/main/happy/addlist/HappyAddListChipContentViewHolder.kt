package com.sopetit.softie.ui.main.happy.addlist

import androidx.recyclerview.widget.RecyclerView
import com.sopetit.softie.databinding.ItemHappyAddListChipBinding

class HappyAddListChipContentViewHolder(private val binding: ItemHappyAddListChipBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(HappyAddListChipContentData: HappyAddListChipContent) {
        binding.itemHappyAddChipComponent.text = HappyAddListChipContentData.name
    }
}
