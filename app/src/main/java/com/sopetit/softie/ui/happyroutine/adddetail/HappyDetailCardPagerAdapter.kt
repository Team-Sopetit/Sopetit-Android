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
            onContentsTheSame = { oldItem, newItem -> oldItem == newItem }
        )
    ) {

    class HappyPagerViewHolder(
        private val binding: ItemHappyAddDetailCardBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: HappyCard.Routines) {
            with(binding) {
                ivHappyRoutineAddCard.setImageResource(data.cardImageUrl)
                tvHappyRoutineAddCardDetailTitle.text = data.detailTitle
                tvHappyRoutineAddCardDetailTitleBack.text = data.detailTitle
                tvHappyRoutineAddCardDetailContentBack.text = data.detailContent
                tvHappyRoutineAddCardDetailTimeBack.text = data.detailTime
                tvHappyRoutineAddCardDetailPlaceBack.text = data.detailPlace

                clHappyRoutineAddCard.setOnClickListener {
                    setCardFlip(clHappyRoutineAddCard, clHappyRoutineAddCardBack)
                }
                clHappyRoutineAddCardBack.setOnClickListener {
                    setCardFlip(clHappyRoutineAddCardBack, clHappyRoutineAddCard)
                }
            }
        }

        private fun setCardFlip(viewFront: View, viewToBack: View) {
            val isVisible = viewFront.visibility == View.VISIBLE
            if (isVisible) {
                viewFront.visibility = View.INVISIBLE
                viewToBack.visibility = View.VISIBLE
            } else {
                viewFront.visibility = View.VISIBLE
                viewToBack.visibility = View.INVISIBLE
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
