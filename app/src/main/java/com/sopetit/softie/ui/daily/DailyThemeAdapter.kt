package com.sopetit.softie.ui.daily

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sopetit.softie.databinding.ItemDailyThemeBinding
import com.sopetit.softie.domain.entity.DailyTheme
import com.sopetit.softie.util.ItemDiffCallback

class DailyThemeAdapter : ListAdapter<DailyTheme, DailyThemeAdapter.DailyThemeViewHolder>(
    ItemDiffCallback<DailyTheme>(
        onItemsTheSame = { oldItem, newItem -> oldItem == newItem },
        onContentsTheSame = { oldItem, newItem -> oldItem == newItem }
    )
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyThemeViewHolder {
        val binding = ItemDailyThemeBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return DailyThemeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DailyThemeViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }

    class DailyThemeViewHolder(private val binding: ItemDailyThemeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: DailyTheme) {
            binding.ivDailyTheme.load(data.iconImageUrl)
            binding.tvThemeDaily.text = data.content
        }
    }
}
