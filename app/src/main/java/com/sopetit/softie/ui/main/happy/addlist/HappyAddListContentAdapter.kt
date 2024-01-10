package com.sopetit.softie.ui.main.happy.addlist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sopetit.softie.databinding.ItemHappyAddListBinding

class HappyAddListContentAdapter(context: Context) :
    RecyclerView.Adapter<HappyAddListContentViewHolder>() {
    private val inflater by lazy { LayoutInflater.from(context) }
    private var happyAddListContentList: List<HappyAddListContent> =
        emptyList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HappyAddListContentViewHolder {
        val binding = ItemHappyAddListBinding.inflate(inflater, parent, false)
        return HappyAddListContentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HappyAddListContentViewHolder, position: Int) {
        holder.onBind(happyAddListContentList[position])
    }

    override fun getItemCount() = happyAddListContentList.size

    fun setHomeContentList(homeContentList: List<HappyAddListContent>) {
        this.happyAddListContentList = homeContentList.toList()
        notifyDataSetChanged()
    }
}
