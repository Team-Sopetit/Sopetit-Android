package com.sopetit.softie.ui.dailyroutine.dailyroutineadd

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
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
    private var onItemClickListener: OnItemClickListener? = null
    private var selectedPosition = 0

    interface OnItemClickListener {
        fun onItemClick(item: Theme, position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    //    private val selectedPositions = HashSet<Int>()
//    private var onItemClickListener: ((Theme) -> Unit)? = null
//    fun setOnThemeClickListener(listener: (Theme) -> Unit) {
//        onItemClickListener = listener
//    }
//
//    private var selectedPosition = RecyclerView.NO_POSITION
//    private var prePosition = RecyclerView.NO_POSITION
//
//    private fun isSelected(theme: Theme) = selectedPositions.contains(theme.themeId)
//
    inner class DailyThemeViewHolder(private val binding: ItemDailyRoutineAddThemeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: Theme) {
            binding.tvDailyRoutineAddThemeName.text = data.name
            binding.ivDailyRoutineAddThemeIcon.load(data.iconImageUrl)
            if (selectedPosition == absoluteAdapterPosition) {
                changeThemeBackground(binding, true)
//            binding.flDailyRoutineAddTheme.isSelected = isSelected(data)
//            binding.root.setOnClickListener {
//                selectTheme(binding, data)
//                //notifyDataSetChanged()
//                onItemClickListener?.let { it(data) }
//                Timber.d("daily routine -> ${selectedPositions}")
            } else {
                changeThemeBackground(binding, false)
            }

            if (onItemClickListener != null) {
                binding.root.setOnClickListener {
                    onItemClickListener?.onItemClick(data, absoluteAdapterPosition)
                    if (selectedPosition != absoluteAdapterPosition) {
                        changeThemeBackground(binding, true)
                        notifyItemChanged(selectedPosition)
                        selectedPosition = absoluteAdapterPosition
                    }
                }
            }
        }
    }

//    private fun selectTheme(binding: ItemDailyRoutineAddThemeBinding, theme: Theme) {
//        prePosition = selectedPosition - 1
//
////        prePosition = selectedPosition
////
////        fun updateSelectedPosition(theme: Theme) {
////            selectedPosition = theme.themeId
////            notifyItemChanged(selectedPosition)
////            notifyItemChanged(prePosition)
////            Timber.d("daily routine update -> ${selectedPosition}")
////        }
//
//        if (isSelected(theme)) {
//            Timber.d("daily routine true -> ${selectedPositions}")
//        } else {
//            selectedPositions.clear()
//            selectedPositions.add(theme.themeId)
//            changeThemeBackground(binding, true)
//            Timber.d("daily routine false -> ${selectedPositions}")
//
//        }
//    }

    //selectedThemeArray.removeAt(theme.themeId)
    //Timber.d("daily routine true -> ${selectedThemeArray}")
    //selectedThemeArray.add(theme.themeId)
    //Timber.d("daily routine false -> ${selectedThemeArray}")

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
        holder.onBind(currentList[position])
    }
}

//    private val clickListener: ItemClickListener<Theme>
//) : ListAdapter<Theme, DailyRoutineAddThemeAdapter.DailyThemeViewHolder>(DIFF_CALLBACK) {
//    private var selectedPosition = RecyclerView.NO_POSITION
//    private var prePosition = RecyclerView.NO_POSITION
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyThemeViewHolder {
//        val itemDailyRoutineAddThemeBinding =
//            ItemDailyRoutineAddThemeBinding.inflate(
//                LayoutInflater.from(parent.context),
//                parent,
//                false
//            )
//        return DailyThemeViewHolder(itemDailyRoutineAddThemeBinding)
//    }
//
//    @SuppressLint("ResourceAsColor")
//    override fun onBindViewHolder(holder: DailyThemeViewHolder, position: Int) {
//        holder.onBind(getItem(position), clickListener)
//        prePosition = selectedPosition
//        holder.binding.ivDailyRoutineAddThemeBackground.isSelected =
//            (position == selectedPosition && position != RecyclerView.NO_POSITION)
//        if (position == selectedPosition && position != RecyclerView.NO_POSITION) {
//            holder.binding.tvDailyRoutineAddThemeName.setTextColor(gray700)
//        } else {
//            holder.binding.tvDailyRoutineAddThemeName.setTextColor(gray400)
//        }
//    }
//
//    private fun changeThemeBackground(
//        binding: ItemDailyRoutineAddThemeBinding,
//        selected: Boolean
//    ) {
//        when (selected) {
//            true -> {
//                binding.ivDailyRoutineAddThemeBackground.setBackgroundResource(R.drawable.ic_daily_theme_background_click)
//                binding.tvDailyRoutineAddThemeName.setTextColor(
//                    ContextCompat.getColor(
//                        binding.root.context,
//                        gray700
//                    )
//                )
//            }
//
//            false -> {
//                binding.ivDailyRoutineAddThemeBackground.setBackgroundResource(R.drawable.ic_daily_theme_background)
//                binding.tvDailyRoutineAddThemeName.setTextColor(
//                    ContextCompat.getColor(
//                        binding.root.context,
//                        gray400
//                    )
//                )
//            }
//        }
//    }
//
//
//    fun clearSelection() {
//        prePosition = selectedPosition
//        selectedPosition = RecyclerView.NO_POSITION
//        notifyItemChanged(prePosition)
//    }
//
//    fun updateSelectedPosition(position: Int) {
//        selectedPosition = position
//        notifyItemChanged(selectedPosition)
//        notifyItemChanged(prePosition)
//    }
//
//    class DailyThemeViewHolder(
//        val binding: ItemDailyRoutineAddThemeBinding
//    ) : RecyclerView.ViewHolder(binding.root) {
//
//        fun onBind(data: Theme, itemClickListener: ItemClickListener<Theme>) {
//            binding.tvDailyRoutineAddThemeName.text = data.name
//            binding.ivDailyRoutineAddThemeIcon.load(data.iconImageUrl)
//            binding.root.setOnClickListener {
//                itemClickListener.onClick(absoluteAdapterPosition, data)
//            }
//        }
//    }
//
//    companion object {
//        private val DIFF_CALLBACK = ItemDiffCallback<Theme>(
//            onItemsTheSame = { old, new -> old.themeId == new.themeId },
//            onContentsTheSame = { old, new -> old == new }
//        )
//    }
//}
//
//fun interface ItemClickListener<T> {
//    fun onClick(pos: Int, item: T)
//}

