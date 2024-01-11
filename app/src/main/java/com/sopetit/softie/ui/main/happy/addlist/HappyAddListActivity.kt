package com.sopetit.softie.ui.main.happy.addlist

import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.sopetit.softie.R
import com.sopetit.softie.databinding.ActivityHappyAddListBinding
import com.sopetit.softie.domain.entity.HappyContent
import com.sopetit.softie.util.HorizontalChipItemDecoration
import com.sopetit.softie.util.VerticalItemDecoration
import com.sopetit.softie.util.binding.BindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HappyAddListActivity :
    BindingActivity<ActivityHappyAddListBinding>(R.layout.activity_happy_add_list) {

    private val viewModel by viewModels<HappyAddListViewModel>()
    private lateinit var itemDeco: RecyclerView.ItemDecoration
    private lateinit var chipDeco: RecyclerView.ItemDecoration

    private lateinit var happyAddListChipContentAdapter: HappyAddListChipContentAdapter
    private lateinit var happyAddListContentAdapter: HappyAddListContentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHappyAddListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val themeId = 0
        happyAddListChipContentAdapter = HappyAddListChipContentAdapter()
        happyAddListContentAdapter = HappyAddListContentAdapter()

        itemDeco = VerticalItemDecoration(applicationContext)
        binding.rvHappyAddList.addItemDecoration(itemDeco)
        chipDeco = HorizontalChipItemDecoration(applicationContext)
        binding.rvHappyAddListChip.addItemDecoration(chipDeco)


        setupAdapter(themeId)
    }

    private fun setupAdapter(themeId: Int) {
        with(binding) {
            rvHappyAddListChip.adapter = happyAddListChipContentAdapter
            rvHappyAddList.adapter = happyAddListContentAdapter
        }
        happyAddListChipContentAdapter.submitList(viewModel.mockHappyChipList.value)
        happyAddListContentAdapter.submitList(viewModel.mockHappyContentList.value)

        happyAddListChipContentAdapter.setOnChipClickListener {
            when (it.themeId) {
                1 -> viewModel.mockHappyContentList.value?.let { it1 -> makeSubmitList(it1) }
                2 -> viewModel.mockHappyContentListOne.value?.let { it2 -> makeSubmitList(it2) }
                3 -> viewModel.mockHappyContentListTwo.value?.let { it3 -> makeSubmitList(it3) }
                4 -> viewModel.mockHappyContentListThree.value?.let { it4 -> makeSubmitList(it4) }
                5 -> viewModel.mockHappyContentListFour.value?.let { it5 -> makeSubmitList(it5) }
                6 -> viewModel.mockHappyContentListFive.value?.let { it6 -> makeSubmitList(it6) }
                else -> viewModel.mockHappyContentList.value?.let { it1 -> makeSubmitList(it1) }
            }
        }
    }

    private fun makeSubmitList(list: List<HappyContent>) {
        happyAddListContentAdapter.submitList(list)
    }
}
