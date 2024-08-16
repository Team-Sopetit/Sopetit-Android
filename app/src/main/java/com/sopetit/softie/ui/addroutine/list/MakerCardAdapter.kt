package com.sopetit.softie.ui.addroutine.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sopetit.softie.R
import com.sopetit.softie.databinding.ItemAddRoutineListMakerCardBinding
import com.sopetit.softie.domain.entity.MakerCard

class MakerCardAdapter : RecyclerView.Adapter<MakerCardAdapter.MakerPagerViewHolder>() {

    private var dataList = listOf<MakerCard>()

    class MakerPagerViewHolder(private val binding: ItemAddRoutineListMakerCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val hashtagAdapter = MakerCardHashtagAdapter(emptyList())

        init {
            binding.rvAddRoutineListMakerHashtag.adapter = hashtagAdapter
        }

        fun onBind(data: MakerCard) {
            with(binding) {
                ivAddRoutineListMakerCardImage.load(data.artistImageUrl) {
                    placeholder(R.drawable.ic_happy_card_base)
                    error(R.drawable.ic_happy_card_base)
                }
                tvAddRoutineListMakerCardDetailSubtitle.text = data.subTitle
                tvAddRoutineListMakerCardDetailTitle.text = data.title

                (binding.rvAddRoutineListMakerHashtag.adapter as MakerCardHashtagAdapter)?.submitList(
                    data.hashtag
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MakerPagerViewHolder {
        val binding = ItemAddRoutineListMakerCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MakerPagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MakerPagerViewHolder, position: Int) {
        holder.onBind(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size

    fun submitList(newDataList: List<MakerCard>) {
        dataList = newDataList
        notifyDataSetChanged()
    }
}
