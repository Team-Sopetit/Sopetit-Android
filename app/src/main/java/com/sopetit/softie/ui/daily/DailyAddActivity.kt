package com.sopetit.softie.ui.daily

import android.os.Bundle
import androidx.activity.viewModels
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.sopetit.softie.R
import com.sopetit.softie.databinding.ActivityDailyAddBinding
import com.sopetit.softie.util.binding.BindingActivity

class DailyAddActivity : BindingActivity<ActivityDailyAddBinding>(R.layout.activity_daily_add) {
    private lateinit var viewPager: ViewPager2
    private lateinit var dailyPagerAdapter: DailyPagerAdapter
    private lateinit var tabLayout: TabLayout

    private val dailyAddViewModel by viewModels<DailyAddViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewPager = binding.vpDailyCard
        dailyPagerAdapter = DailyPagerAdapter()

        setupAdapter()
        initViewPager()
        initTabLayout()
    }

    private fun setupAdapter() {
        binding.vpDailyCard.adapter = dailyPagerAdapter
        dailyPagerAdapter.submitList(dailyAddViewModel.mockDailyList)
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

    private fun initTabLayout() {
        tabLayout = binding.tlDailyIndicator
        TabLayoutMediator(tabLayout, viewPager) { _, _ -> }.attach()
    }

}
