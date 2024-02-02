package com.sopetit.softie.ui.dailyroutine.dailyroutineadd

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sopetit.softie.R
import com.sopetit.softie.databinding.ItemDailyRoutineAddThemeBinding
import com.sopetit.softie.domain.entity.Theme
import com.sopetit.softie.util.ItemDiffCallback
import com.sopetit.softie.util.binding.BindingAdapter.setCoilImage
import com.sopetit.softie.util.setSingleOnClickListener

class DailyRoutineAddThemeAdapter :
    ListAdapter<Theme, DailyRoutineAddThemeAdapter.DailyThemeViewHolder>(
        ItemDiffCallback<Theme>(
            onItemsTheSame = { old, new -> old.themeId == new.themeId },
            onContentsTheSame = { old, new -> old == new }
        )
    ) {
    private var onItemClickListener: OnItemClickListener? = null
    private var selectedPosition = 0
    var clickedThemeId: Int = 1
    var clickedThemeIcon: String? = null

    interface OnItemClickListener {
        fun onItemClick(item: Theme, position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    inner class DailyThemeViewHolder(
        private val binding: ItemDailyRoutineAddThemeBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: Theme) {
            binding.tvDailyRoutineAddThemeName.text = data.name
            binding.ivDailyRoutineAddThemeIcon.setCoilImage(data.iconImageUrl)
            if (selectedPosition == absoluteAdapterPosition) {
                changeThemeBackground(binding, true)
                changeThemeTextColor(binding, true)
            } else {
                changeThemeBackground(binding, false)
                changeThemeTextColor(binding, false)
            }

            if (onItemClickListener != null) {
                binding.root.setSingleOnClickListener {
                    onItemClickListener?.onItemClick(data, absoluteAdapterPosition)
                    if (selectedPosition != absoluteAdapterPosition) {
                        changeThemeBackground(binding, true)
                        changeThemeTextColor(binding, true)
                        notifyItemChanged(selectedPosition)
                        selectedPosition = absoluteAdapterPosition
                    }
                    clickedThemeId = data.themeId
                    clickedThemeIcon = data.iconImageUrl
                }
            }
        }
    }

    private fun changeThemeBackground(
        binding: ItemDailyRoutineAddThemeBinding,
        selected: Boolean
    ) {
        when (selected) {
            true -> {
                binding.ivDailyRoutineAddThemeBackground.setBackgroundResource(R.drawable.ic_daily_theme_background_click)
            }

            false -> {
                binding.ivDailyRoutineAddThemeBackground.setBackgroundResource(R.drawable.ic_daily_theme_background)
            }
        }
    }

    private fun changeThemeTextColor(
        binding: ItemDailyRoutineAddThemeBinding,
        selected: Boolean
    ) {
        when (selected) {
            true -> {
                binding.tvDailyRoutineAddThemeName.setTextColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.gray700
                    )
                )
            }

            false -> {
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
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DailyThemeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DailyThemeViewHolder, position: Int) {
        if (clickedThemeIcon == null) clickedThemeIcon = currentList[0].iconImageUrl
        holder.onBind(currentList[position])
    }
}
