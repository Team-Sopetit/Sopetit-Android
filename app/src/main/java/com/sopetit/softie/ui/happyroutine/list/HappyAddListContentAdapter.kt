package com.sopetit.softie.ui.happyroutine.list

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sopetit.softie.databinding.ItemHappyAddListBinding
import com.sopetit.softie.domain.entity.HappyContent
import com.sopetit.softie.util.ItemDiffCallback
import com.sopetit.softie.util.setSingleOnClickListener

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
            binding.tvHappyListItemTitle.text = data.name
            binding.tvHappyListItemTitle.setTextColor(Color.parseColor(data.nameColor))
            binding.tvHappyListItemContent.text = data.title
            binding.ivHappyListItemIcon.load(data.iconImageUrl)

            binding.root.setSingleOnClickListener {
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
