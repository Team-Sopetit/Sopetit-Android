package com.sopetit.softie.ui.happyroutine.addlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sopetit.softie.databinding.ItemHappyAddListBinding
import com.sopetit.softie.domain.entity.HappyContent
import com.sopetit.softie.util.ItemDiffCallback

class HappyAddListContentAdapter(private val moveToDetail: (Int) -> Unit) :
    ListAdapter<HappyContent, HappyAddListContentAdapter.HappyAddListContentViewHolder>(
        ItemDiffCallback<HappyContent>(
            onItemsTheSame = { oldItem, newItem -> oldItem == newItem },
            onContentsTheSame = { oldItem, newItem -> oldItem == newItem }
        )
    ) {

    inner class HappyAddListContentViewHolder(
        private val binding: ItemHappyAddListBinding,
        private val moveToDetail: (Int) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: HappyContent) {
            binding.tvHappyListItemTitle.text = data.title
            binding.tvHappyListItemContent.text = data.content
            binding.ivHappyListItemIcon.setImageResource(data.imageUrl)

            binding.root.setOnClickListener {
                moveToDetail(data.routineId)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HappyAddListContentViewHolder {
        val binding = ItemHappyAddListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HappyAddListContentViewHolder(binding, moveToDetail)
    }

    override fun onBindViewHolder(holder: HappyAddListContentViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }
}
