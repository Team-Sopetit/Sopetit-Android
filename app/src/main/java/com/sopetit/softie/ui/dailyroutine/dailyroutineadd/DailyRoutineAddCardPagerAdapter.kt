package com.sopetit.softie.ui.dailyroutine.dailyroutineadd

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sopetit.softie.databinding.ItemDailyRoutineAddCardBinding
import com.sopetit.softie.domain.entity.DailyCard
import com.sopetit.softie.util.ItemDiffCallback
import timber.log.Timber

class DailyRoutineAddCardPagerAdapter :
    ListAdapter<DailyCard, DailyRoutineAddCardPagerAdapter.DailyPagerViewHolder>(
        ItemDiffCallback<DailyCard>(
            onItemsTheSame = { oldItem, newItem -> oldItem == newItem },
            onContentsTheSame = { oldItem, newItem -> oldItem == newItem }
        )
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyPagerViewHolder {
        val binding = ItemDailyRoutineAddCardBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return DailyPagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DailyPagerViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }

    class DailyPagerViewHolder(private val binding: ItemDailyRoutineAddCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: DailyCard) {
            binding.tvDailyRoutineAddCard.load(data.backgroundImageUrl)
            Timber.d("load Image -> ${data}")
            binding.tvDailyRoutineAddCardName.text = data.content
        }
    }
}