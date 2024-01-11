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
    var selectedThemeArray = mutableListOf<Int>()

    //클릭이벤트 만들어 주기
    private var onItemClickListener: ((Theme) -> Unit)? = null
    fun setOnThemeClickListener(listener: (Theme) -> Unit) {
        onItemClickListener = listener
    }

    inner class DailyThemeViewHolder(private val binding: ItemDailyRoutineAddThemeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: Theme) {
            binding.tvDailyRoutineAddThemeName.text = data.name
            binding.ivDailyRoutineAddThemeBackground.load(data.iconImageUrl)
            binding.root.setOnClickListener {
//                val position = bindingAdapterPosition
//                if (position != RecyclerView.NO_POSITION) {
//                    val isSelected = toggleThemeSelection(data.themeId)
//                    onItemClickListener?.invoke(data)
//                    notifyItemChanged(bindingAdapterPosition)
//                    changeThemeBackground(binding, selectedThemeArray.contains(data.themeId))
//                }
            }
        }
    }

    private fun toggleThemeSelection(routineId: Int): Boolean {
        val isSelected = selectedThemeArray.contains(routineId)
        if (isSelected) {
            selectedThemeArray.remove(routineId) // 선택 해제
        } else {
            selectedThemeArray.add(routineId) // 선택
        }
        return !isSelected // 선택 상태 반전
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
