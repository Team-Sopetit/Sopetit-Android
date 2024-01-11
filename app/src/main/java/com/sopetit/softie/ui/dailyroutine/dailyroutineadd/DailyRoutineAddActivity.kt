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
import com.sopetit.softie.util.binding.BindingActivity

class DailyRoutineAddActivity :
    BindingActivity<ActivityDailyRoutineAddBinding>(R.layout.activity_daily_routine_add) {
    private lateinit var viewPager: ViewPager2
    private lateinit var dailyRoutineAddCardPagerAdapter: DailyRoutineAddCardPagerAdapter
    private lateinit var dailyRoutineAddThemeAdapter: DailyRoutineAddThemeAdapter

    private val dailyRoutineAddCardViewModel by viewModels<DailyRoutineAddCardViewModel>()
    private val dailyRoutineAddThemeViewModel by viewModels<DailyRoutineAddThemeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dailyRoutineAddCardPagerAdapter = DailyRoutineAddCardPagerAdapter()
        dailyRoutineAddThemeAdapter = DailyRoutineAddThemeAdapter()

        viewPager = binding.vpDailyRoutineAddCard

        setupAdapter()
        //marginPage()
        initViewPager()
        setIndicator()
        setDiv()
        initPagerDiv(19, 20)
    }

    private fun setupAdapter() {
        with(binding) {
            vpDailyRoutineAddCard.adapter = dailyRoutineAddCardPagerAdapter
            rvDailyRoutineAddTheme.adapter = dailyRoutineAddThemeAdapter
        }
        dailyRoutineAddCardPagerAdapter.submitList(dailyRoutineAddCardViewModel.mockDailyList)
        dailyRoutineAddThemeAdapter.submitList(dailyRoutineAddThemeViewModel.mockThemeList.value)
    }

    private fun marginPage() {
        val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.viewpager_margin)
        val pagerWidth = resources.getDimensionPixelOffset(R.dimen.viewpager_margin)
        val screenWidth = resources.displayMetrics.widthPixels
        val offsetPx = screenWidth - pageMarginPx - pagerWidth

        binding.vpDailyRoutineAddCard.setPageTransformer { page, position ->
            page.translationX = position * -offsetPx
        }
    }

    private fun initViewPager() {

        viewPager.adapter = dailyRoutineAddCardPagerAdapter

        viewPager.apply {
            clipChildren = false
            clipToPadding = false
            offscreenPageLimit = 1
        }

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(
            MarginPageTransformer(
                resources.getDimensionPixelOffset(
                    R.dimen.viewpager_margin
                )
            )
        )
        viewPager.setPageTransformer(compositePageTransformer)
    }

    private fun setIndicator() {
        binding.diDailyRoutineAddIndicator.attachTo(binding.vpDailyRoutineAddCard)
    }

    private fun initPagerDiv(previewWidth: Int, itemMargin: Int) {
        val decoMargin = previewWidth + itemMargin
        val pageTransX = decoMargin + previewWidth
        val decoration = PageDecoration(decoMargin)

        binding.vpDailyRoutineAddCard.also {
            it.offscreenPageLimit = 1
            it.addItemDecoration(decoration)
            it.setPageTransformer { page, position ->
                page.translationX = position * -pageTransX
            }
        }
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

    private fun selectTheme() {
        dailyRoutineAddThemeAdapter.setOnThemeClickListener {

        }
    }

    private fun setDiv() {
        binding.rvDailyRoutineAddTheme.addItemDecoration(HorizontalItemDecorator(16))
    }
}
