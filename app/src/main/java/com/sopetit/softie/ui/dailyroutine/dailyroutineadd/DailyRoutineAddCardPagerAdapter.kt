package com.sopetit.softie.ui.dailyroutine.dailyroutineadd

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sopetit.softie.R
import com.sopetit.softie.databinding.ItemDailyRoutineAddCardBinding
import com.sopetit.softie.domain.entity.Routine
import com.sopetit.softie.util.ItemDiffCallback
import timber.log.Timber

class DailyRoutineAddCardPagerAdapter :
    ListAdapter<Routine, DailyRoutineAddCardPagerAdapter.DailyPagerViewHolder>(
        ItemDiffCallback<Routine>(
            onItemsTheSame = { oldItem, newItem -> oldItem.routineId == newItem.routineId },
            onContentsTheSame = { oldItem, newItem -> oldItem == newItem }
        )
    ) {
    lateinit var background: String
    var content: String? = null
    fun updateBackground(backgroundImg: String) {
        background = backgroundImg
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyPagerViewHolder {
        val binding = ItemDailyRoutineAddCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DailyPagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DailyPagerViewHolder, position: Int) {
        if (content == null) content = currentList[0].content
        holder.onBind(currentList[position])
    }

    override fun getItemId(position: Int): Long {
        val itemCount = currentList.size
        return if (position in 0 until itemCount) {
            content = currentList[position].content
            currentList[position].routineId.toLong()
        } else {
            RecyclerView.NO_ID
        }
    }

    inner class DailyPagerViewHolder(
        private val binding: ItemDailyRoutineAddCardBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: Routine) {
            binding.tvDailyRoutineAddCard.load(background) {
                placeholder(R.drawable.ic_daily_routine_card_base)
                error(R.drawable.ic_daily_routine_card_base)
            }
            Timber.d("load Image -> $data")
            binding.tvDailyRoutineAddCardName.text = data.content
        }
    }
}
