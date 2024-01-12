package com.sopetit.softie.ui.happyroutine.adddetail

import android.graphics.Color
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.sopetit.softie.R
import com.sopetit.softie.databinding.ActivityHappyAddDetailBinding
import com.sopetit.softie.util.binding.BindingActivity

class HappyDetailActivity :
    BindingActivity<ActivityHappyAddDetailBinding>(R.layout.activity_happy_add_detail) {
    private lateinit var viewPager: ViewPager2
    private lateinit var happyRoutineAddCardPagerAdapter: HappyDetailCardPagerAdapter

    private val viewModel by viewModels<HappyDetailCardViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewPager = binding.vpHappyAddDetailCard

        setBackEnter()
        setupBinding()
        setupAdapter()
        setIndicator()
        initViewPager()
        initPagerDiv(0, 90)
    }

    private fun setBackEnter() {
        binding.ivHappyAddDetailBack.setOnClickListener {
            finish()
        }
    }

    private fun setupBinding() {
        val happyCard = viewModel.mockHappyCardList.value?.get(1)
        happyCard?.let {
            binding.tvHappyAddDetailTitle.text = it.name
            binding.ivHappyAddDetailIcon.setImageResource(it.iconImageUrl)
            binding.tvHappyAddDetailSubtitle.text = it.title
            binding.tvHappyAddDetailTitle.setTextColor(Color.parseColor(it.nameColor))
        }
    }

    private fun setupAdapter() {
        with(binding) {
            happyRoutineAddCardPagerAdapter = HappyDetailCardPagerAdapter()
            vpHappyAddDetailCard.adapter = happyRoutineAddCardPagerAdapter
        }
        happyRoutineAddCardPagerAdapter.submitList(viewModel.mockHappyCardList.value)
    }

    private fun setIndicator() {
        binding.diHappyAddDetailIndicator.attachTo(binding.vpHappyAddDetailCard)
    }

    private fun initViewPager() {
        viewPager.adapter = happyRoutineAddCardPagerAdapter

        val dpValue = 40
        val d = resources.displayMetrics.density
        val margin = (dpValue * d).toInt()

        with(binding.vpHappyAddDetailCard) {
            clipChildren = false
            clipToPadding = false
            offscreenPageLimit = 3
            setPadding(margin, 0, margin, 0)
        }
        val compositePageTransformer = CompositePageTransformer()
        binding.vpHappyAddDetailCard.setPageTransformer(compositePageTransformer)
    }

    private fun initPagerDiv(previewWidth: Int, itemMargin: Int) {
        val decoMargin = previewWidth + itemMargin
        val pageTransX = decoMargin + previewWidth
        val decoration = PageDecoration(decoMargin)

        binding.vpHappyAddDetailCard.also {
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
}
