package com.sopetit.softie.ui.happyroutine.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sopetit.softie.databinding.ItemHappyAddListChipBinding
import com.sopetit.softie.domain.entity.HappyChip
import com.sopetit.softie.util.ItemDiffCallback
import com.sopetit.softie.util.setSingleOnClickListener

class HappyAddListChipContentAdapter :
    ListAdapter<HappyChip, HappyAddListChipContentAdapter.HappyAddListChipContentViewHolder>(
        ItemDiffCallback<HappyChip>(
            onItemsTheSame = { oldItem, newItem -> oldItem.themeId == newItem.themeId },
            onContentsTheSame = { oldItem, newItem -> oldItem == newItem }
        )
    ) {
    private var onItemClickListener: ((HappyChip) -> Unit)? = null
    private var isInitialSelection = true

    inner class HappyAddListChipContentViewHolder(
        private val binding: ItemHappyAddListChipBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: HappyChip) {
            binding.itemHappyAddChipComponent.text = data.name
            val isSelected = isSelected(adapterPosition, data.themeId)
            binding.itemHappyAddChipComponent.isChecked = isSelected

            binding.root.setOnClickListener {
                chipCurrentSelection(adapterPosition, data.themeId)
                notifyDataSetChanged()
                onItemClickListener?.let { it(data) }
            }
        }
    }

    private fun isSelected(position: Int, themeId: Int) = if (isInitialSelection) {
        isInitialSelection = false
        themeId == 0
    } else {
        selectedPosition == position
    }

    private var selectedPosition = -1

    private fun chipCurrentSelection(position: Int, themeId: Int) {
        selectedPosition = position
    }

    fun setOnChipClickListener(listener: (HappyChip) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HappyAddListChipContentViewHolder {
        val binding = ItemHappyAddListChipBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HappyAddListChipContentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HappyAddListChipContentViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}
