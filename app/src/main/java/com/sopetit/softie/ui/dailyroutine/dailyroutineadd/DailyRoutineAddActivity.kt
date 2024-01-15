package com.sopetit.softie.ui.dailyroutine.dailyroutineadd

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.sopetit.softie.R
import com.sopetit.softie.databinding.ActivityDailyRoutineAddBinding
import com.sopetit.softie.domain.entity.Theme
import com.sopetit.softie.util.OriginalBottomSheet
import com.sopetit.softie.util.binding.BindingActivity
import com.sopetit.softie.util.binding.BindingBottomSheet
import com.sopetit.softie.util.setStatusBarColorFromResource

class DailyRoutineAddActivity :
    BindingActivity<ActivityDailyRoutineAddBinding>(R.layout.activity_daily_routine_add) {
    private lateinit var viewPager: ViewPager2
    private lateinit var dailyRoutineAddCardPagerAdapter: DailyRoutineAddCardPagerAdapter
    private lateinit var dailyRoutineAddThemeAdapter: DailyRoutineAddThemeAdapter

    private val dailyRoutineAddViewModel by viewModels<DailyRoutineAddViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewPager = binding.vpDailyRoutineAddCard
        binding.viewModel = dailyRoutineAddViewModel
        setStatusBarColorFromResource(R.color.background)

        setupAdapter()
        setViewPager()
        setupList()
        setIndicator()
        setItemDiv()
        initPagerDiv(0, 90)
        addClickListener()
    }

    private fun addClickListener() {
        addRoutine()
        backToDailyRoutine()
    }

    private fun setupAdapter() {
        with(binding) {
            dailyRoutineAddCardPagerAdapter = DailyRoutineAddCardPagerAdapter()
            vpDailyRoutineAddCard.adapter = dailyRoutineAddCardPagerAdapter
            dailyRoutineAddThemeAdapter = DailyRoutineAddThemeAdapter().apply {
                setOnItemClickListener(object : DailyRoutineAddThemeAdapter.OnItemClickListener {
                    override fun onItemClick(item: Theme, position: Int) {
                        setRoutineList(item.themeId)
                    }
                })
            }
            rvDailyRoutineAddTheme.adapter = dailyRoutineAddThemeAdapter
        }
    }

    private fun setupList() {
        dailyRoutineAddViewModel.mockThemeList.observe(this) {
            dailyRoutineAddCardPagerAdapter.submitList(dailyRoutineAddViewModel.startNewDayCardList.value)
            dailyRoutineAddThemeAdapter.submitList(dailyRoutineAddViewModel.mockThemeList.value)
        }
    }

    private fun setRoutineList(themeId: Int) {
        dailyRoutineAddCardPagerAdapter.submitList(
            dailyRoutineAddViewModel.getDailyCardListForId(themeId)[0].dailyRoutineCardList
        )
    }

    private fun initPagerDiv(previewWidth: Int, itemMargin: Int) {
        val decoMargin = previewWidth + itemMargin
        val pageTransX = decoMargin + previewWidth
        val decoration = PageDecoration(decoMargin)

        binding.vpDailyRoutineAddCard.also {
            it.offscreenPageLimit = VIEW_PAGE.toInt()
            it.addItemDecoration(decoration)
            it.setPageTransformer { page, position ->
                page.translationX = position * -pageTransX
            }
        }
    }

    private fun setViewPager() {
        with(binding.vpDailyRoutineAddCard) {
            adapter = dailyRoutineAddCardPagerAdapter
            offscreenPageLimit = VIEW_PAGE.toInt()

            val dpValue = PADDING_PAGE
            val margin = (dpValue * resources.displayMetrics.density).toInt()
            setPadding(margin, PADDING_CARD, margin, PADDING_CARD)

            setPageTransformer(
                CompositePageTransformer().apply {
                    addTransformer(
                        MarginPageTransformer(
                            resources.getDimensionPixelOffset(R.dimen.viewpager_margin)
                        )
                    )
                }
            )
        }
    }

    private fun setIndicator() {
        binding.diDailyRoutineAddIndicator.attachTo(binding.vpDailyRoutineAddCard)
    }

    private class PageDecoration(private val margin: Int) : RecyclerView.ItemDecoration() {

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            outRect.left = margin
            outRect.right = margin
        }
    }

    class HorizontalItemDecorator(private val divHeight: Int) : RecyclerView.ItemDecoration() {
        @Override
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)
            outRect.left = divHeight
            outRect.right = divHeight
        }
    }

    private fun setItemDiv() {
        binding.rvDailyRoutineAddTheme.addItemDecoration(HorizontalItemDecorator(16))
    }

    private fun addRoutine() {
        binding.btnDailyRoutineAdd.setOnClickListener {
            finish()
        }
    }

    private fun backToDailyRoutine() {
        binding.ivDailyRoutineAddBack.setOnClickListener {
            finish()
        }
    }

    private fun initSetDailyRoutineAdd(btn: View, routineId: Int) {
        btn.setOnClickListener {
            // TODO 서버통신 구현 후 imageUri 버전으로 수정

            BindingBottomSheet.Builder().build(
                isDrawable = false,
                imageDrawable = 0,
                imageUri = "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                title = "데일리 루틴을 추가할까요?",
                content = "한 번 완료하면 이전으로 되돌릴 수 없어요",
                isContentVisible = true,
                contentColor = R.color.gray400,
                backBtnContent = "아니, 더 고민할게",
                doBtnContent = "추가할래",
                doBtnColor = R.drawable.shape_main1_fill_12_rect,
                backBtnAction = {},
                doBtnAction = {
                    finish()
                }
            ).show(supportFragmentManager, OriginalBottomSheet.BOTTOM_SHEET_TAG)
        }
    }

    companion object {
        const val VIEW_PAGE = 3
        const val PADDING_PAGE = 40
        const val PADDING_CARD = 0
    }
}
