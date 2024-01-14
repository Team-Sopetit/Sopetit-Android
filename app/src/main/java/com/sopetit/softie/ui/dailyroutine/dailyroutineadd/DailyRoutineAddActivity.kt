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
import com.sopetit.softie.util.binding.BindingActivity
import timber.log.Timber

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

        setupAdapter()
        setViewPager()
        setupList()
        setIndicator()
        setItemDiv()
        initPagerDiv(0, 90)
    }

    private fun setupAdapter() {
        with(binding) {
            dailyRoutineAddCardPagerAdapter = DailyRoutineAddCardPagerAdapter()
            vpDailyRoutineAddCard.adapter = dailyRoutineAddCardPagerAdapter
            dailyRoutineAddThemeAdapter = DailyRoutineAddThemeAdapter().apply {
                setOnItemClickListener(object : DailyRoutineAddThemeAdapter.OnItemClickListener {
                    override fun onItemClick(item: Theme, position: Int) {
                        setRoutineList(item)
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

    private fun setRoutineList(item: Theme) {
        Timber.d("daily routine act -> ${item.themeId}")
        dailyRoutineAddCardPagerAdapter.submitList(dailyRoutineAddViewModel.themeDailyRoutineList[item.themeId].value)
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

    companion object {
        const val VIEW_PAGE = 3
        const val PADDING_PAGE = 40
        const val PADDING_CARD = 0
    }
}
