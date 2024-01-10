package com.sopetit.softie.ui.main.happy.addlist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sopetit.softie.databinding.ItemHappyAddListChipBinding

class HappyAddListChipContentAdapter(context: Context) :
    RecyclerView.Adapter<HappyAddListChipContentViewHolder>() {
    private val inflater by lazy { LayoutInflater.from(context) }
    private var happyAddListChipContentList: List<HappyAddListChipContent> =
        emptyList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HappyAddListChipContentViewHolder {
        val binding = ItemHappyAddListChipBinding.inflate(inflater, parent, false)
        return HappyAddListChipContentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HappyAddListChipContentViewHolder, position: Int) {
        holder.onBind(happyAddListChipContentList[position])
    }

    override fun getItemCount() = happyAddListChipContentList.size

    fun setHomeContentList(homeContentChipList: List<HappyAddListChipContent>) {
        this.happyAddListChipContentList = homeContentChipList.toList()
        notifyDataSetChanged()
    }
}
