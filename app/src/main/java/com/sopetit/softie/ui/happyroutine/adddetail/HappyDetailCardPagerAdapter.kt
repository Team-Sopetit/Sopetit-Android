package com.sopetit.softie.ui.happyroutine.adddetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sopetit.softie.databinding.ItemHappyAddDetailCardBinding
import com.sopetit.softie.domain.entity.HappyCard
import com.sopetit.softie.util.ItemDiffCallback

class HappyDetailCardPagerAdapter :
    ListAdapter<HappyCard, HappyDetailCardPagerAdapter.HappyPagerViewHolder>(
        ItemDiffCallback<HappyCard>(
            onItemsTheSame = { oldItem, newItem -> oldItem == newItem },
            onContentsTheSame = { oldItem, newItem -> oldItem == newItem }
        )
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HappyPagerViewHolder {
        val binding = ItemHappyAddDetailCardBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return HappyPagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HappyPagerViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }

    class HappyPagerViewHolder(private val binding: ItemHappyAddDetailCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: HappyCard) {
            binding.ivHappyRoutineAddCard.setImageResource(data.routines[0].cardImageUrl)
            binding.tvHappyRoutineAddCardDetailTitle.text = data.routines[0].content
        }
    }
}
