package com.sopetit.softie.ui.happyroutine.adddetail

import android.content.Intent
import android.graphics.Color
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.sopetit.softie.R
import com.sopetit.softie.databinding.ActivityHappyAddDetailBinding
import com.sopetit.softie.ui.happyroutine.addlist.HappyAddListActivity.Companion.ID
import com.sopetit.softie.ui.main.MainActivity
import com.sopetit.softie.util.OriginalBottomSheet
import com.sopetit.softie.util.binding.BindingActivity
import com.sopetit.softie.util.binding.BindingBottomSheet
import com.sopetit.softie.util.setStatusBarColorFromResource

class HappyDetailActivity :
    BindingActivity<ActivityHappyAddDetailBinding>(R.layout.activity_happy_add_detail) {
    private lateinit var viewPager: ViewPager2
    private lateinit var happyRoutineAddCardPagerAdapter: HappyDetailCardPagerAdapter

    private val viewModel by viewModels<HappyDetailCardViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewPager = binding.vpHappyAddDetailCard
        setStatusBarColorFromResource(R.color.background)

        val categoryId = intent.getIntExtra(ID, -1)
        val viewModel = ViewModelProvider(this).get(HappyDetailCardViewModel::class.java)
        val happyCard = viewModel.mockHappyCardList.value?.get(categoryId - 1)

        happyCard?.let {
            with(binding) {
                tvHappyAddDetailTitle.text = happyCard.name
                ivHappyAddDetailIcon.setImageResource(happyCard.iconImageUrl)
                tvHappyAddDetailSubtitle.text = happyCard.title
                tvHappyAddDetailTitle.setTextColor(Color.parseColor(happyCard.nameColor))
            }
        }

        setBackEnter()
        if (happyCard != null) {
            setSnackbarEnter(happyCard.iconImageUrl)
        }
        setupAdapter(categoryId)
        setIndicator()
        initViewPager()
        initPagerDiv(0, 90)
    }

    private fun setBackEnter() {
        binding.ivHappyAddDetailBack.setOnClickListener {
            finish()
        }
    }

    private fun setSnackbarEnter(icon: Int) {
        binding.btnHappyDetailAdd.setOnClickListener {
            initHappyRoutineAddBottomSheet(icon)
        }
    }

    private fun initHappyRoutineAddBottomSheet(icon: Int) {
        BindingBottomSheet.Builder().build(
            isDrawable = true,
            imageDrawable = icon,
            imageUri = "",
            title = getString(R.string.happy_add_bottom_sheet_title),
            content = "",
            isContentVisible = true,
            contentColor = R.color.main1,
            backBtnContent = getString(R.string.happy_add_bottom_sheet_back_btn),
            doBtnContent = getString(R.string.happy_add_bottom_sheet_do_btn),
            doBtnColor = R.drawable.shape_main1_fill_12_rect,
            backBtnAction = {},
            doBtnAction = { moveToProgress() }
        ).show(this.supportFragmentManager, OriginalBottomSheet.BOTTOM_SHEET_TAG)
    }

    private fun moveToProgress() {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("happy_progress_fragment", "happy_progress")
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    private fun setupAdapter(categoryId: Int) {
        with(binding) {
            happyRoutineAddCardPagerAdapter =
                HappyDetailCardPagerAdapter()
            vpHappyAddDetailCard.adapter = happyRoutineAddCardPagerAdapter
        }
        happyRoutineAddCardPagerAdapter.submitList(
            viewModel.getHappyCardListForId(categoryId)[0].routines
        )
    }

    private fun setIndicator() {
        binding.diHappyAddDetailIndicator.attachTo(binding.vpHappyAddDetailCard)
    }

    private fun initViewPager() {
        viewPager.adapter = happyRoutineAddCardPagerAdapter

        val dp = resources.getDimensionPixelSize(R.dimen.view_margin)
        val d = resources.displayMetrics.density
        val margin = (dp * d).toInt()

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
