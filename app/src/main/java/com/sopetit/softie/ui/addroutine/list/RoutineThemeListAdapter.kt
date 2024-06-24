package com.sopetit.softie.ui.addroutine.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sopetit.softie.R
import com.sopetit.softie.databinding.ItemAddRoutineListBinding
import com.sopetit.softie.domain.entity.RoutineTheme
import com.sopetit.softie.util.ItemDiffCallback

class RoutineThemeListAdapter :
    ListAdapter<RoutineTheme.Themes, RoutineThemeListAdapter.RoutineThemeListViewHolder>(
        ItemDiffCallback<RoutineTheme.Themes>(
            onItemsTheSame = { oldItem, newItem -> oldItem == newItem },
            onContentsTheSame = { oldItem, newItem -> oldItem == newItem }
        )
    ) {

    inner class RoutineThemeListViewHolder(private val binding: ItemAddRoutineListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: RoutineTheme.Themes) {
            with(binding) {
                val iconItem = when (data.themeId) {
                    1 -> R.drawable.ic_theme1_pink
                    2 -> R.drawable.ic_theme2_red
                    3 -> R.drawable.ic_theme3_orange
                    4 -> R.drawable.ic_theme4_yellow
                    5 -> R.drawable.ic_theme5_green
                    6 -> R.drawable.ic_theme6_sky
                    7 -> R.drawable.ic_theme7_blue
                    else -> R.drawable.ic_bear_base
                }

                ivAddRoutineListItemIcon.load(iconItem)
                tvAddRoutineListItemModifier.text = data.modifier
                tvAddRoutineListItemName.text = data.name
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RoutineThemeListViewHolder {
        val binding = ItemAddRoutineListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RoutineThemeListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RoutineThemeListViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }
}
