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
import timber.log.Timber

class DailyRoutineAddActivity :
    BindingActivity<ActivityDailyRoutineAddBinding>(R.layout.activity_daily_routine_add) {
    private lateinit var viewPager: ViewPager2
    private lateinit var dailyRoutineAddCardPagerAdapter: DailyRoutineAddCardPagerAdapter
    private lateinit var dailyRoutineAddThemeAdapter: DailyRoutineAddThemeAdapter


//    private var _dailyRoutineAddCardPagerAdapter: DailyRoutineAddCardPagerAdapter? = null
//    private val dailyRoutineAddCardPagerAdapter
//        get() = requireNotNull(_dailyRoutineAddCardPagerAdapter)
//
//    private var _dailyRoutineAddThemeAdapter: DailyRoutineAddThemeAdapter? = null
//    private val dailyRoutineAddThemeAdapter
//        get() = requireNotNull(_dailyRoutineAddThemeAdapter)


    private val dailyRoutineAddViewModel by viewModels<DailyRoutineAddViewModel>()

    val themeId = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewPager = binding.vpDailyRoutineAddCard
        binding.viewModel = dailyRoutineAddViewModel

        setupAdapter()
        setViewPager()
        setIndicator()
//        clickTheme()
        setItemDiv()
    }

    private fun setupAdapter() {
        with(binding) {
            dailyRoutineAddCardPagerAdapter = DailyRoutineAddCardPagerAdapter()
            vpDailyRoutineAddCard.adapter = dailyRoutineAddCardPagerAdapter
            dailyRoutineAddThemeAdapter = DailyRoutineAddThemeAdapter()
            rvDailyRoutineAddTheme.adapter = dailyRoutineAddThemeAdapter
        }
//        with(dailyRoutineAddCardPagerAdapter) {
//            submitList(dailyRoutineAddViewModel.mockDailyFirstCardList.value)
////            submitList(dailyRoutineAddViewModel.mockDailySecondCardList.value)
////            submitList(dailyRoutineAddViewModel.mockDailyThirdCardList.value)
////            submitList(dailyRoutineAddViewModel.mockDailyFourthCardList.value)
////            submitList(dailyRoutineAddViewModel.mockDailyFifthCardList.value)
//        }
//        dailyRoutineAddThemeAdapter.submitList(dailyRoutineAddViewModel.mockThemeList.value)

        dailyRoutineAddViewModel.mockThemeList.observe(this) {
            dailyRoutineAddCardPagerAdapter.submitList(dailyRoutineAddViewModel.mockDailyFirstCardList.value)
            dailyRoutineAddThemeAdapter.submitList(dailyRoutineAddViewModel.mockThemeList.value)
        }
        clickTheme()
    }

    private fun setViewPager() {
        with(binding.vpDailyRoutineAddCard) {
            adapter = dailyRoutineAddCardPagerAdapter
            offscreenPageLimit = 3

            val dpValue = 40
            val margin = (dpValue * resources.displayMetrics.density).toInt()
            setPadding(margin, 0, margin, 0)

            setPageTransformer(CompositePageTransformer().apply {
                addTransformer(MarginPageTransformer(resources.getDimensionPixelOffset(R.dimen.viewpager_margin)))
            })
        }
    }

    private fun setIndicator() {
        binding.diDailyRoutineAddIndicator.attachTo(binding.vpDailyRoutineAddCard)
    }

    private fun clickTheme() {
        dailyRoutineAddThemeAdapter.setOnThemeClickListener {
            Timber.d("daily routine act -> ${it.themeId}")
            // Timber.d("daily routine act -> ${dailyRoutineAddThemeAdapter.selectedThemeArray}")
            when (it.themeId) {
                1 -> dailyRoutineAddCardPagerAdapter.submitList(dailyRoutineAddViewModel.mockDailyFirstCardList.value)
                2 -> dailyRoutineAddCardPagerAdapter.submitList(dailyRoutineAddViewModel.mockDailySecondCardList.value)
                3 -> dailyRoutineAddCardPagerAdapter.submitList(dailyRoutineAddViewModel.mockDailyThirdCardList.value)
                4 -> dailyRoutineAddCardPagerAdapter.submitList(dailyRoutineAddViewModel.mockDailyFourthCardList.value)
                5 -> dailyRoutineAddCardPagerAdapter.submitList(dailyRoutineAddViewModel.mockDailyFifthCardList.value)
                6 -> dailyRoutineAddCardPagerAdapter.submitList(dailyRoutineAddViewModel.mockDailySixthCardList.value)
                7 -> dailyRoutineAddCardPagerAdapter.submitList(dailyRoutineAddViewModel.mockDailySeventhCardList.value)
                8 -> dailyRoutineAddCardPagerAdapter.submitList(dailyRoutineAddViewModel.mockDailyEighthCardList.value)
                9 -> dailyRoutineAddCardPagerAdapter.submitList(dailyRoutineAddViewModel.mockDailyNinthCardList.value)
                10 -> dailyRoutineAddCardPagerAdapter.submitList(dailyRoutineAddViewModel.mockDailyTenthCardList.value)
                11 -> dailyRoutineAddCardPagerAdapter.submitList(dailyRoutineAddViewModel.mockDailyEleventhCardList.value)
                12 -> dailyRoutineAddCardPagerAdapter.submitList(dailyRoutineAddViewModel.mockDailyTwelfthCardList.value)
                else -> dailyRoutineAddCardPagerAdapter.submitList(dailyRoutineAddViewModel.mockDailyFirstCardList.value)
            }
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
}
