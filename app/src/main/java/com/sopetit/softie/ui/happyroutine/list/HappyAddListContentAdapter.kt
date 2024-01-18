package com.sopetit.softie.ui.happyroutine.list

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sopetit.softie.R
import com.sopetit.softie.databinding.ItemHappyAddListBinding
import com.sopetit.softie.domain.entity.HappyContent
import com.sopetit.softie.util.ItemDiffCallback

class HappyAddListContentAdapter(private val moveToDetail: (Int, String) -> Unit) :
    ListAdapter<HappyContent, HappyAddListContentAdapter.HappyAddListContentViewHolder>(
        ItemDiffCallback<HappyContent>(
            onItemsTheSame = { oldItem, newItem -> oldItem == newItem },
            onContentsTheSame = { oldItem, newItem -> oldItem == newItem }
        )
    ) {

    inner class HappyAddListContentViewHolder(
        private val binding: ItemHappyAddListBinding,
        private val moveToDetail: (Int, String) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: HappyContent) {
            binding.tvHappyListItemTitle.text = data.name
            binding.tvHappyListItemTitle.setTextColor(Color.parseColor(data.nameColor))
            binding.tvHappyListItemContent.text = data.title
            binding.ivHappyListItemIcon.load(data.iconImageUrl) {
                placeholder(R.drawable.ic_bear_base)
                error(R.drawable.ic_bear_base)
            }
            binding.root.setOnClickListener {
                moveToDetail(data.routineId, data.iconImageUrl)
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
