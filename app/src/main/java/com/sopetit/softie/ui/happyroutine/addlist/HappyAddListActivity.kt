package com.sopetit.softie.ui.happyroutine.addlist

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.sopetit.softie.R
import com.sopetit.softie.databinding.ActivityHappyAddListBinding
import com.sopetit.softie.domain.entity.HappyContent
import com.sopetit.softie.ui.happyroutine.adddetail.HappyDetailActivity
import com.sopetit.softie.util.HorizontalChipItemDecoration
import com.sopetit.softie.util.VerticalItemDecoration
import com.sopetit.softie.util.binding.BindingActivity
import com.sopetit.softie.util.setStatusBarColorFromResource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HappyAddListActivity :
    BindingActivity<ActivityHappyAddListBinding>(R.layout.activity_happy_add_list) {

    private val viewModel by viewModels<HappyAddListViewModel>()
    private lateinit var itemDeco: RecyclerView.ItemDecoration
    private lateinit var chipDeco: RecyclerView.ItemDecoration

    private var happyAddListChipContentAdapter: HappyAddListChipContentAdapter? = null
    private var happyAddListContentAdapter: HappyAddListContentAdapter? = null

    private val themeId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHappyAddListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setStatusBarColorFromResource(R.color.background)

        setChipAdapters()
        setBackEnter()
        setItemDeco()
        setupAdapter(themeId)
    }

    private fun setChipAdapters() {
        happyAddListChipContentAdapter = HappyAddListChipContentAdapter()
        happyAddListContentAdapter = HappyAddListContentAdapter(::moveToDetail)
    }

    private fun setBackEnter() {
        binding.ivHappyAddBackArrow.setOnClickListener {
            finish()
        }
    }

    private fun setItemDeco() {
        itemDeco = VerticalItemDecoration(applicationContext)
        binding.rvHappyAddList.addItemDecoration(itemDeco)
        chipDeco = HorizontalChipItemDecoration(applicationContext)
        binding.rvHappyAddListChip.addItemDecoration(chipDeco)
    }

    private fun setupAdapter(themeId: Int) {
        with(binding) {
            rvHappyAddListChip.adapter = happyAddListChipContentAdapter
            rvHappyAddList.adapter = happyAddListContentAdapter
        }
        happyAddListChipContentAdapter?.submitList(viewModel.mockHappyChipList.value)
        happyAddListContentAdapter?.submitList(viewModel.mockHappyContentList.value)

        happyAddListChipContentAdapter?.setOnChipClickListener { handleChipClick(it.themeId) }
    }

    private fun handleChipClick(themeId: Int) {
        val listToSubmit = when (themeId) {
            1 -> viewModel.mockHappyContentList.value
            2 -> viewModel.mockHappyContentListOne.value
            3 -> viewModel.mockHappyContentListTwo.value
            4 -> viewModel.mockHappyContentListThree.value
            5 -> viewModel.mockHappyContentListFour.value
            6 -> viewModel.mockHappyContentListFive.value
            else -> viewModel.mockHappyContentList.value
        }

        listToSubmit?.let { makeSubmitList(it) }
    }

    private fun makeSubmitList(list: List<HappyContent>) {
        happyAddListContentAdapter?.submitList(list)
    }

    private fun moveToDetail(id: Int) {
        Intent(this, HappyDetailActivity::class.java).apply {
            putExtra(ID, id)
            startActivity(this)
        }
    }

    companion object {
        const val ID = "id"
    }
}
