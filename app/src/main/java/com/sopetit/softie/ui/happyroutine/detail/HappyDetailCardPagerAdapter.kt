package com.sopetit.softie.ui.happyroutine.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sopetit.softie.databinding.ItemHappyAddDetailCardBinding
import com.sopetit.softie.domain.entity.HappyCard
import com.sopetit.softie.util.ItemDiffCallback
import com.sopetit.softie.util.setSingleOnClickListener

class HappyDetailCardPagerAdapter() :
    ListAdapter<HappyCard.SubRoutines, HappyDetailCardPagerAdapter.HappyPagerViewHolder>(
        ItemDiffCallback<HappyCard.SubRoutines>(
            onItemsTheSame = { oldItem, newItem -> oldItem.subRoutineId == newItem.subRoutineId },
            onContentsTheSame = { oldItem, newItem -> oldItem == newItem }
        )
    ) {

    class HappyPagerViewHolder(
        private val binding: ItemHappyAddDetailCardBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: HappyCard.SubRoutines) {
            with(binding) {
                ivHappyRoutineAddCard.load(data.contentImageUrl)
                tvHappyRoutineAddCardDetailTitle.text = data.content
                tvHappyRoutineAddCardDetailTitleBack.text =
                    data.content
                tvHappyRoutineAddCardDetailContentBack.text =
                    data.detailContent
                tvHappyRoutineAddCardDetailTimeBack.text =
                    data.timeTaken
                tvHappyRoutineAddCardDetailPlaceBack.text = data.place

                clHappyRoutineAddCard.setSingleOnClickListener {
                    setCardFlip(clHappyRoutineAddCard, clHappyRoutineAddCardBack)
                }
                clHappyRoutineAddCardBack.setSingleOnClickListener {
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

    override fun getItemId(position: Int): Long {
        val itemCount = currentList.size
        if (position in 0 until itemCount) {
            return currentList[position].subRoutineId.toLong()
        } else {
            return RecyclerView.NO_ID
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HappyPagerViewHolder {
        val binding = ItemHappyAddDetailCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HappyPagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HappyPagerViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }
}
