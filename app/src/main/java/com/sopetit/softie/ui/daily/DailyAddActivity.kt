package com.sopetit.softie.ui.daily

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.sopetit.softie.R
import com.sopetit.softie.databinding.ActivityDailyAddBinding
import com.sopetit.softie.util.binding.BindingActivity

class DailyAddActivity : BindingActivity<ActivityDailyAddBinding>(R.layout.activity_daily_add) {
    private lateinit var viewPager: ViewPager2
    private lateinit var dailyPagerAdapter: DailyPagerAdapter
    private lateinit var dailyThemeAdapter: DailyThemeAdapter

    private val dailyAddViewModel by viewModels<DailyAddViewModel>()
    private val dailyThemeViewModel by viewModels<DailyThemeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dailyPagerAdapter = DailyPagerAdapter()
        dailyThemeAdapter = DailyThemeAdapter()

        viewPager = binding.vpDailyCard

        setupAdapter()
        //marginPage()
        initViewPager()
        setIndicator()
        setDiv()
        initPagerDiv(19, 20)
    }

    private fun setupAdapter() {
        with(binding) {
            vpDailyCard.adapter = dailyPagerAdapter
            rvThemeDaily.adapter = dailyThemeAdapter
        }
        dailyPagerAdapter.submitList(dailyAddViewModel.mockDailyList)
        dailyThemeAdapter.submitList(dailyThemeViewModel.mockDailyThemeList.value)
    }

    private fun marginPage() {
        val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.viewpager_margin)
        val pagerWidth = resources.getDimensionPixelOffset(R.dimen.viewpager_margin)
        val screenWidth = resources.displayMetrics.widthPixels
        val offsetPx = screenWidth - pageMarginPx - pagerWidth

        binding.vpDailyCard.setPageTransformer { page, position ->
            page.translationX = position * -offsetPx
        }
    }

    private fun initViewPager() {

        viewPager.adapter = dailyPagerAdapter

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
        binding.diDailyIndicator.attachTo(binding.vpDailyCard)
    }

    private fun initPagerDiv(previewWidth: Int, itemMargin: Int) {
        val decoMargin = previewWidth + itemMargin
        val pageTransX = decoMargin + previewWidth
        val decoration = PageDecoration(decoMargin)

        binding.vpDailyCard.also {
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

    private fun setDiv() {
        binding.rvThemeDaily.addItemDecoration(HorizontalItemDecorator(16))
    }
}
