package com.sopetit.softie.ui.dailyroutine.dailyroutineadd

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sopetit.softie.R
import com.sopetit.softie.databinding.ItemDailyRoutineAddThemeBinding
import com.sopetit.softie.domain.entity.Theme
import com.sopetit.softie.util.ItemDiffCallback

class DailyRoutineAddThemeAdapter :
    ListAdapter<Theme, DailyRoutineAddThemeAdapter.DailyThemeViewHolder>(
        ItemDiffCallback<Theme>(
            onItemsTheSame = { old, new -> old.themeId == new.themeId },
            onContentsTheSame = { old, new -> old == new }
        )
    ) {
    private val selectedPositions = HashSet<Int>()
    private fun isSelected(position: Int) = selectedPositions.contains(position)

    var selectedThemeArray = mutableListOf<Int>()

    private var onItemClickListener: ((Theme) -> Unit)? = null
    fun setOnThemeClickListener(listener: (Theme) -> Unit) {
        onItemClickListener = listener
    }

    inner class DailyThemeViewHolder(private val binding: ItemDailyRoutineAddThemeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: Theme) {
            binding.tvDailyRoutineAddThemeName.text = data.name
            binding.ivDailyRoutineAddThemeIcon.load(data.iconImageUrl)
            binding.root.setOnClickListener {
                selectTheme(absoluteAdapterPosition)
                notifyDataSetChanged()
                onItemClickListener?.let { it(data) }
            }
        }
    }

    private fun selectTheme(position: Int) {
        if (isSelected(position)) {
            selectedPositions.remove(position)
        } else {
            selectedPositions.clear()
            selectedPositions.add(position)
        }
    }

    private fun changeThemeBackground(
        binding: ItemDailyRoutineAddThemeBinding,
        selected: Boolean
    ) {
        when (selected) {
            true -> {
                binding.ivDailyRoutineAddThemeBackground.setBackgroundResource(R.drawable.ic_daily_theme_background_click)
                binding.tvDailyRoutineAddThemeName.setTextColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.gray700
                    )
                )
            }

            false -> {
                binding.ivDailyRoutineAddThemeBackground.setBackgroundResource(R.drawable.ic_daily_theme_background)
                binding.tvDailyRoutineAddThemeName.setTextColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.gray400
                    )
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyThemeViewHolder {
        val binding = ItemDailyRoutineAddThemeBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return DailyThemeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DailyThemeViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}
