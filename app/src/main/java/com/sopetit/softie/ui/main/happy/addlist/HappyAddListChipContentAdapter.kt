package com.sopetit.softie.ui.main.happy.addlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sopetit.softie.databinding.ItemHappyAddListChipBinding
import com.sopetit.softie.domain.entity.HappyChip
import com.sopetit.softie.util.ItemDiffCallback

class HappyAddListChipContentAdapter :
    ListAdapter<HappyChip, HappyAddListChipContentAdapter.HappyAddListChipContentViewHolder>(
        ItemDiffCallback<HappyChip>(
            onItemsTheSame = { oldItem, newItem -> oldItem == newItem },
            onContentsTheSame = { oldItem, newItem -> oldItem == newItem }
        )
    ) {

    inner class HappyAddListChipContentViewHolder(
        private val binding: ItemHappyAddListChipBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: HappyChip) {
            binding.itemHappyAddChipComponent.text = data.name
            binding.root.setOnClickListener {
                onItemClickListener?.let { it(data) }
            }
        }
    }

    private var onItemClickListener: ((HappyChip) -> Unit)? = null
    fun setOnChipClickListener(listener: (HappyChip) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HappyAddListChipContentViewHolder {
        val binding = ItemHappyAddListChipBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return HappyAddListChipContentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HappyAddListChipContentViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}
