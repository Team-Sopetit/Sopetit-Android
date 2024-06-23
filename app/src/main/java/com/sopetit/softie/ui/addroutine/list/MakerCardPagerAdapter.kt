package com.sopetit.softie.ui.addroutine.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sopetit.softie.R
import com.sopetit.softie.databinding.ItemAddListMakerCardBinding
import com.sopetit.softie.domain.entity.MakerCard

class MakerCardPagerAdapter : RecyclerView.Adapter<MakerCardPagerAdapter.MakerPagerViewHolder>() {

    private var dataList = listOf<MakerCard>()

    class MakerPagerViewHolder(private val binding: ItemAddListMakerCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val hashtagAdapter = MakerCardPagerHashtagAdapter(emptyList())

        init {
            binding.rvAddListMakerHashtagChip.adapter = hashtagAdapter
        }

        fun onBind(data: MakerCard) {
            with(binding) {
                ivAddListMakerCardImage.load(data.artistImageUrl) {
                    placeholder(R.drawable.ic_happy_card_base)
                    error(R.drawable.ic_happy_card_base)
                }
                tvAddListMakerCardDetailSubtitle.text = data.subTitle
                tvAddListMakerCardDetailTitle.text = data.title

                (binding.rvAddListMakerHashtagChip.adapter as MakerCardPagerHashtagAdapter)?.submitList(
                    data.hashtag
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MakerPagerViewHolder {
        val binding = ItemAddListMakerCardBinding.inflate(
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
