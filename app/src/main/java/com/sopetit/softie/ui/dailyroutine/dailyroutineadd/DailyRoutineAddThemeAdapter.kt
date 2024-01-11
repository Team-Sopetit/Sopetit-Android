package com.sopetit.softie.ui.dailyroutine.dailyroutineadd

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sopetit.softie.R
import com.sopetit.softie.databinding.ItemDailyRoutineAddThemeBinding
import com.sopetit.softie.domain.entity.DailyTheme
import com.sopetit.softie.util.ItemDiffCallback

class DailyRoutineAddThemeAdapter :
    ListAdapter<DailyTheme, DailyRoutineAddThemeAdapter.DailyThemeViewHolder>(
        ItemDiffCallback<DailyTheme>(
            onItemsTheSame = { oldItem, newItem -> oldItem == newItem },
            onContentsTheSame = { oldItem, newItem -> oldItem == newItem }
        )
    ) {
    var selectedThemeArray = mutableListOf<Int>()

    inner class DailyThemeViewHolder(private val binding: ItemDailyRoutineAddThemeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            toggleThemeSelection(getItem(0).routineId)
        }

        fun onBind(data: DailyTheme) {
            binding.data = data
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val isSelected = toggleThemeSelection(data.routineId)
                    onItemClickListener?.invoke(data)
                    notifyItemChanged(bindingAdapterPosition)
                    changeThemeBackground(binding, selectedThemeArray.contains(data.routineId))
                }
            }
        }
    }

    //클릭이벤트 만들어 주기
    private var onItemClickListener: ((DailyTheme) -> Unit)? = null
    fun setOnThemeClickListener(listener: (DailyTheme) -> Unit) {
        onItemClickListener = listener
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
