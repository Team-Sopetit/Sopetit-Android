package com.sopetit.softie.ui.addroutine.list

import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.sopetit.softie.R
import com.sopetit.softie.databinding.ActivityAddListBinding
import com.sopetit.softie.util.VerticalItemDecoration
import com.sopetit.softie.util.binding.BindingActivity
import com.sopetit.softie.util.setSingleOnClickListener
import com.sopetit.softie.util.setStatusBarColorFromResource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddListActivity : BindingActivity<ActivityAddListBinding>(R.layout.activity_add_list) {
    private lateinit var viewPager: ViewPager2
    private val makerCardPagerAdapter = MakerCardPagerAdapter()
    private val viewModel by viewModels<AddListViewModel>()
    private var routineThemeListAdapter: RoutineThemeListAdapter? = null
    private lateinit var itemDeco: RecyclerView.ItemDecoration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        viewPager = binding.vpAddListMakerCard
        setStatusBarColorFromResource(R.color.background)

        startMakerHelpModal()
        setBackEnter()
        setInitBinding()
        setHappyDetailCardPagerAdapter()
        setCurrentCard()
        setRoutineThemeListAdapter()
        setItemDeco()
        setupObservers()
    }

    private fun startMakerHelpModal() {
        binding.ivAddListMakerHelp.setSingleOnClickListener {
            MakerHelpDialogFragment().show(supportFragmentManager, "MakerHelpDialog")
        }
    }

    private fun setBackEnter() {
        binding.ivAddListBackArrow.setSingleOnClickListener {
            finish()
        }
    }

    private fun setInitBinding() {
        viewModel.getMakerCard()
        viewModel.getRoutineTheme()
    }

    private fun setHappyDetailCardPagerAdapter() {
        with(binding) {
            vpAddListMakerCard.adapter = makerCardPagerAdapter
        }
    }

    private fun setCurrentCard() {
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                val currentMakerId = getCurrentSelectedMakerId()
                viewModel.setMakerId(currentMakerId)
            }
        })
    }

    private fun getCurrentSelectedMakerId(): Int {
        val currentItem = binding.vpAddListMakerCard.currentItem
        val itemId = makerCardPagerAdapter.getItemId(currentItem)
        return itemId.toInt()
    }


    private fun setRoutineThemeListAdapter() {
        with(binding) {
            routineThemeListAdapter = RoutineThemeListAdapter()
            rvAddList.adapter = routineThemeListAdapter
        }
    }

    private fun setItemDeco() {
        itemDeco = VerticalItemDecoration(applicationContext)
        binding.rvAddList.addItemDecoration(itemDeco)
    }

    private fun setupObservers() {
        viewModel.addRoutineThemeListResponse.observe(this) { routineTheme ->
            routineTheme?.let {
                routineThemeListAdapter?.submitList(routineTheme.themes)
            }
        }

        viewModel.addMakerCardResponse.observe(this) { makerCards ->
            makerCards?.let {
                makerCardPagerAdapter.submitList(makerCards)
            }
        }
    }
}
