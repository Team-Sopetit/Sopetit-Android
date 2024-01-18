package com.sopetit.softie.ui.dailyroutine.dailyroutineadd

import android.app.Activity
import android.content.Intent
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
import com.sopetit.softie.domain.entity.Routine
import com.sopetit.softie.domain.entity.Theme
import com.sopetit.softie.ui.main.MainActivity
import com.sopetit.softie.util.OriginalBottomSheet
import com.sopetit.softie.util.binding.BindingActivity
import com.sopetit.softie.util.binding.BindingBottomSheet
import com.sopetit.softie.util.setStatusBarColorFromResource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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

        dailyRoutineAddViewModel.getThemeList()
        dailyRoutineAddViewModel.setThemeId(6)
//        initSetDailyRoutineAdd()
        setupAdapter()
        setViewPager()
        setupList()
        setIndicator()
        setItemDiv()
        setCurrentCard()
        initPagerDiv(0, 90)
        addClickListener()
        observeRoutineCardList()
    }

    private fun addClickListener() {
        backToDailyRoutine()
    }

    private fun setupAdapter() {
        with(binding) {
            dailyRoutineAddCardPagerAdapter = DailyRoutineAddCardPagerAdapter()
            vpDailyRoutineAddCard.adapter = dailyRoutineAddCardPagerAdapter
            dailyRoutineAddThemeAdapter = DailyRoutineAddThemeAdapter().apply {
                setOnItemClickListener(object : DailyRoutineAddThemeAdapter.OnItemClickListener {
                    override fun onItemClick(item: Theme, position: Int) {
                        dailyRoutineAddViewModel.setThemeId(item.themeId)
                    }
                })
            }
            rvDailyRoutineAddTheme.adapter = dailyRoutineAddThemeAdapter
        }
    }

    private fun setupList() {
        dailyRoutineAddViewModel.themeList.observe(this) {
            dailyRoutineAddThemeAdapter.submitList(dailyRoutineAddViewModel.themeList.value)
        }
    }

    private fun observeRoutineCardList() {
        dailyRoutineAddViewModel.themeId.observe(this) {
            dailyRoutineAddViewModel.getDailyRoutine()
        }
        dailyRoutineAddViewModel.dailyRoutineCardThemeList.observe(this) {
            dailyRoutineAddCardPagerAdapter.updateBackground(
                dailyRoutineAddViewModel.dailyRoutineCardThemeList.value?.backgroundImageUrl ?: ""
            )
            dailyRoutineAddCardPagerAdapter.submitList(
                dailyRoutineAddViewModel.dailyRoutineCardThemeList.value?.routine
            )

            initSetDailyRoutineAdd()
        }
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
                    addTransformer(MarginPageTransformer(resources.getDimensionPixelOffset(R.dimen.viewpager_margin)))
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

    private fun backToDailyRoutine() {
        binding.ivDailyRoutineAddBack.setOnClickListener {
            finish()
        }
    }

    private fun setCurrentCard() {
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                val currentRoutineId = getCurrentSelectedRoutineId()
                dailyRoutineAddViewModel.setRoutineId(currentRoutineId)
            }
        })
    }

    private fun getCurrentSelectedRoutine(): Routine {
        val currentItem = binding.vpDailyRoutineAddCard.currentItem
        val itemId = dailyRoutineAddCardPagerAdapter.getItemId(currentItem)
        val itemContent = dailyRoutineAddCardPagerAdapter.content
        return Routine(itemId.toInt(), itemContent)
    }

    private fun getCurrentSelectedRoutineId(): Int {
        val currentItem = binding.vpDailyRoutineAddCard.currentItem
        val itemId = dailyRoutineAddCardPagerAdapter.getItemId(currentItem)
        return itemId.toInt()
    }

    private fun initSetDailyRoutineAdd() {
        binding.btnDailyRoutineAdd.setOnClickListener {
            BindingBottomSheet.Builder().build(
                isDrawable = false,
                imageDrawable = 0,
                imageUri = dailyRoutineAddThemeAdapter.clickedThemeIcon,
                title = getString(R.string.daily_routine_add_question),
                content = getCurrentSelectedRoutine().content,
                isContentVisible = true,
                contentColor = R.color.gray400,
                backBtnContent = getString(R.string.daily_routine_add_no),
                doBtnContent = getString(R.string.daily_routine_add_yes),
                doBtnColor = R.drawable.shape_main1_fill_12_rect,
                backBtnAction = {},
                doBtnAction = {
                    tossMsg()
                    dailyRoutineAddViewModel.postAddDailyRoutine()
                    dailyRoutineAddViewModel.getDailyRoutine()
                }
            ).show(supportFragmentManager, OriginalBottomSheet.BOTTOM_SHEET_TAG)
        }
    }

    private fun tossMsg() {
        val resultIntent = Intent(this, MainActivity::class.java)
        setResult(Activity.RESULT_OK, resultIntent)
        finish()
    }

    companion object {
        const val VIEW_PAGE = 3
        const val PADDING_PAGE = 40
        const val PADDING_CARD = 0
    }
}
