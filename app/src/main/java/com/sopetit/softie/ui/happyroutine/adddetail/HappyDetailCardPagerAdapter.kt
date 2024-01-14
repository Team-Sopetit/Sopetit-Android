package com.sopetit.softie.ui.happyroutine.adddetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sopetit.softie.databinding.ItemHappyAddDetailCardBinding
import com.sopetit.softie.domain.entity.HappyCard
import com.sopetit.softie.util.ItemDiffCallback

class HappyDetailCardPagerAdapter() :
    ListAdapter<HappyCard.Routines, HappyDetailCardPagerAdapter.HappyPagerViewHolder>(
        ItemDiffCallback<HappyCard.Routines>(
            onItemsTheSame = { oldItem, newItem -> oldItem.routineId == newItem.routineId },
            onContentsTheSame = { oldItem, newItem -> oldItem.routineId == newItem.routineId }
        )
    ) {

    class HappyPagerViewHolder(
        private val binding: ItemHappyAddDetailCardBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: HappyCard.Routines) {
            binding.ivHappyRoutineAddCard.setImageResource(data.cardImageUrl)
            binding.tvHappyRoutineAddCardDetailTitle.text = data.detailTitle
            binding.tvHappyRoutineAddCardDetailTitleBack.text = data.detailTitle
            binding.tvHappyRoutineAddCardDetailContentBack.text = data.detailContent
            binding.tvHappyRoutineAddCardDetailTimeBack.text = data.detailTime
            binding.tvHappyRoutineAddCardDetailPlaceBack.text = data.detailPlace
            binding.clHappyRoutineAddCard.setOnClickListener {
                val isVisible = binding.clHappyRoutineAddCard.visibility == View.VISIBLE
                if (isVisible) {
                    binding.clHappyRoutineAddCard.visibility = View.INVISIBLE
                    binding.clHappyRoutineAddCardBack.visibility = View.VISIBLE
                } else {
                    binding.clHappyRoutineAddCard.visibility = View.VISIBLE
                    binding.clHappyRoutineAddCardBack.visibility = View.INVISIBLE
                }
            }
            binding.clHappyRoutineAddCardBack.setOnClickListener {
                val isVisible = binding.clHappyRoutineAddCardBack.visibility == View.VISIBLE
                if (isVisible) {
                    binding.clHappyRoutineAddCardBack.visibility = View.INVISIBLE
                    binding.clHappyRoutineAddCard.visibility = View.VISIBLE
                } else {
                    binding.clHappyRoutineAddCardBack.visibility = View.VISIBLE
                    binding.clHappyRoutineAddCard.visibility = View.INVISIBLE
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HappyPagerViewHolder {
        val binding = ItemHappyAddDetailCardBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return HappyPagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HappyPagerViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }
}
