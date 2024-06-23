package com.sopetit.softie.ui.addroutine.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sopetit.softie.R
import com.sopetit.softie.databinding.ItemAddListBinding
import com.sopetit.softie.domain.entity.RoutineTheme
import com.sopetit.softie.util.ItemDiffCallback

class RoutineThemeListAdapter :
    ListAdapter<RoutineTheme.Themes, RoutineThemeListAdapter.RoutineThemeListViewHolder>(
        ItemDiffCallback<RoutineTheme.Themes>(
            onItemsTheSame = { oldItem, newItem -> oldItem == newItem },
            onContentsTheSame = { oldItem, newItem -> oldItem == newItem }
        )
    ) {

    inner class RoutineThemeListViewHolder(private val binding: ItemAddListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: RoutineTheme.Themes) {
            with(binding) {
                when (data.themeId) {
                    1 -> ivAddListItemIcon.load(R.drawable.ic_theme1_pink)
                    2 -> ivAddListItemIcon.load(R.drawable.ic_theme2_red)
                    3 -> ivAddListItemIcon.load(R.drawable.ic_theme3_orange)
                    4 -> ivAddListItemIcon.load(R.drawable.ic_theme4_yellow)
                    5 -> ivAddListItemIcon.load(R.drawable.ic_theme5_green)
                    6 -> ivAddListItemIcon.load(R.drawable.ic_theme6_sky)
                    7 -> ivAddListItemIcon.load(R.drawable.ic_theme7_blue)
                    else -> ivAddListItemIcon.load(R.drawable.ic_bear_base)
                }
                tvAddListItemContent.text = data.modifier
                tvAddListItemTitle.text = data.name
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RoutineThemeListViewHolder {
        val binding = ItemAddListBinding.inflate(
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
