package com.sopetit.softie.ui.happyroutine.list

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.sopetit.softie.R
import com.sopetit.softie.databinding.ActivityHappyAddListBinding
import com.sopetit.softie.ui.happyroutine.detail.HappyDetailActivity
import com.sopetit.softie.util.HorizontalChipItemDecoration
import com.sopetit.softie.util.VerticalItemDecoration
import com.sopetit.softie.util.binding.BindingActivity
import com.sopetit.softie.util.setSingleOnClickListener
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

    private val addHappinessRoutineResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHappyAddListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setStatusBarColorFromResource(R.color.background)

        viewModel.getHappyChip()

        setChipAdapters()
        setBackEnter()
        setItemDeco()
        setupAdapter()
    }

    private fun setChipAdapters() {
        happyAddListChipContentAdapter = HappyAddListChipContentAdapter()
        happyAddListContentAdapter = HappyAddListContentAdapter(::moveToDetail)
    }

    private fun setBackEnter() {
        binding.ivHappyAddBackArrow.setSingleOnClickListener {
            finish()
        }
    }

    private fun setItemDeco() {
        itemDeco = VerticalItemDecoration(applicationContext)
        binding.rvHappyAddList.addItemDecoration(itemDeco)
        chipDeco = HorizontalChipItemDecoration(applicationContext)
        binding.rvHappyAddListChip.addItemDecoration(chipDeco)
    }

    private fun setupAdapter() {
        with(binding) {
            rvHappyAddListChip.adapter = happyAddListChipContentAdapter
            rvHappyAddList.adapter = happyAddListContentAdapter
        }
        viewModel.happyChipResponse.observe(this) {
            happyAddListChipContentAdapter?.submitList(viewModel.happyChipResponse.value)
        }
        viewModel.happyContentResponse.observe(this) {
            happyAddListContentAdapter?.submitList(viewModel.happyContentResponse.value)
        }
        viewModel.getHappyContent(0)
        happyAddListChipContentAdapter?.setOnChipClickListener {
            viewModel.getHappyContent(it.themeId)
        }
    }

    private fun moveToDetail(id: Int, iconImageUrl: String) {
        val intentToDetail = Intent(this, HappyDetailActivity::class.java).apply {
            putExtra(ID, id)
            putExtra(ICON_IMAGE_URL, iconImageUrl)
        }
        addHappinessRoutineResult.launch(intentToDetail)
        finish()
    }

    companion object {
        const val ID = "id"
        const val ICON_IMAGE_URL = "iconImageUrl"
    }
}
