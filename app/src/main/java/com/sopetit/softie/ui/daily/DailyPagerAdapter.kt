package com.sopetit.softie.ui.daily

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sopetit.softie.R
import com.sopetit.softie.databinding.ItemDailyCardBinding
import com.sopetit.softie.domain.entity.DailyCard
import com.sopetit.softie.util.ItemDiffCallback

class DailyPagerAdapter : ListAdapter<DailyCard, DailyPagerAdapter.DailyPagerViewHolder>(
    ItemDiffCallback<DailyCard>(
        onItemsTheSame = { oldItem, newItem -> oldItem == newItem },
        onContentsTheSame = { oldItem, newItem -> oldItem == newItem }
    )
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyPagerViewHolder {
        val binding = ItemDailyCardBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return DailyPagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DailyPagerViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }

    class DailyPagerViewHolder(private val binding: ItemDailyCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: DailyCard) {
            binding.clDailyCard.loadLayoutDescription(R.layout.item_daily_card)
            binding.tvDailyCard.text = data.routine
        }
    }
}
