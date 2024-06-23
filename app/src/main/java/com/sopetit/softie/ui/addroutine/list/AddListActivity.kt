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

        setInitBinding()
        setHappyDetailCardPagerAdapter()
        setCurrentCard()
        setBackEnter()
        startMakerHelpModal()
        setRoutineThemeListAdapter()
        setItemDeco()
        setupObservers()
    }

    private fun setInitBinding() { // 초기 바인딩 설정
        viewModel.getMakerCard()
        viewModel.getRoutineTheme()
    }

    private fun setHappyDetailCardPagerAdapter() { // maker 카드 뷰페이저 어댑터
        with(binding) {
            vpAddListMakerCard.adapter = makerCardPagerAdapter
        }
    }

    private fun setCurrentCard() { // 현재 보고 있는 카드 선택
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                val currentMakerId = getCurrentSelectedMakerId()
                viewModel.setMakerId(currentMakerId)
            }
        })
    }

    private fun getCurrentSelectedMakerId(): Int { // 현재 보고 있는 뷰페이저의 maker's id 가져오기
        val currentItem = binding.vpAddListMakerCard.currentItem
        val itemId = makerCardPagerAdapter.getItemId(currentItem)
        return itemId.toInt()
    }

    private fun setBackEnter() { // 이전 버튼
        binding.ivAddListBackArrow.setSingleOnClickListener {
            finish()
        }
    }

    private fun startMakerHelpModal() { // 메이커 도움말 모달 실행
        binding.ivAddListMakerHelp.setSingleOnClickListener {
            // 모달 띄우기
        }
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
