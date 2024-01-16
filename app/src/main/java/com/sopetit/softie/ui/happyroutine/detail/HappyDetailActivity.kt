package com.sopetit.softie.ui.happyroutine.detail

import android.content.Intent
import android.graphics.Color
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.ViewPager2
import coil.load
import com.sopetit.softie.R
import com.sopetit.softie.databinding.ActivityHappyAddDetailBinding
import com.sopetit.softie.ui.LoadingIndicator
import com.sopetit.softie.ui.happyroutine.list.HappyAddListActivity.Companion.ID
import com.sopetit.softie.ui.main.MainActivity
import com.sopetit.softie.ui.onboarding.OnboardingActivity.Companion.LOADING_DELAY
import com.sopetit.softie.util.OriginalBottomSheet
import com.sopetit.softie.util.binding.BindingActivity
import com.sopetit.softie.util.binding.BindingBottomSheet
import com.sopetit.softie.util.setStatusBarColorFromResource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HappyDetailActivity :
    BindingActivity<ActivityHappyAddDetailBinding>(R.layout.activity_happy_add_detail) {
    private lateinit var viewPager: ViewPager2
    private lateinit var happyRoutineAddCardPagerAdapter: HappyDetailCardPagerAdapter

    private val viewModel by viewModels<HappyDetailCardViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        viewPager = binding.vpHappyAddDetailCard
        setStatusBarColorFromResource(R.color.background)

        val routineId = intent.getIntExtra(ID, -1).toString()
        viewModel.getHappyCard(routineId)

        viewModel.happyCardResponse.observe(this) { happyCard ->
            happyCard?.let {
                with(binding) {
                    tvHappyAddDetailTitle.text = happyCard.name
                    ivHappyAddDetailIcon.load(happyCard.iconImageUrl)
                    tvHappyAddDetailSubtitle.text = happyCard.title
                    tvHappyAddDetailTitle.setTextColor(Color.parseColor(happyCard.nameColor))
                }
                setBottomSheetEnter(happyCard.iconImageUrl)
            }
        }

        initSetLoading()
        setBackEnter()
        setupAdapter(routineId)
        setIndicator()
        initViewPager()
        initPagerDiv(0, 90)
    }

    private fun setBackEnter() {
        binding.ivHappyAddDetailBack.setOnClickListener {
            finish()
        }
    }

    private fun setBottomSheetEnter(icon: String) {
        binding.btnHappyDetailAdd.setOnClickListener {
            initHappyRoutineAddBottomSheet(icon)
        }
    }

    private fun initHappyRoutineAddBottomSheet(icon: String) {
        BindingBottomSheet.Builder().build(
            isDrawable = false,
            imageDrawable = 0,
            imageUri = icon,
            title = getString(R.string.happy_add_bottom_sheet_title),
            content = "",
            isContentVisible = true,
            contentColor = R.color.main1,
            backBtnContent = getString(R.string.happy_add_bottom_sheet_back_btn),
            doBtnContent = getString(R.string.happy_add_bottom_sheet_do_btn),
            doBtnColor = R.drawable.shape_main1_fill_12_rect,
            backBtnAction = {},
            doBtnAction = {
                moveToProgress()
            }
        ).show(this.supportFragmentManager, OriginalBottomSheet.BOTTOM_SHEET_TAG)
    }

    private fun moveToProgress() {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("happy_progress_fragment", "happy_progress")
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    private fun setupAdapter(routineId: String) {
        with(binding) {
            happyRoutineAddCardPagerAdapter =
                HappyDetailCardPagerAdapter()
            vpHappyAddDetailCard.adapter = happyRoutineAddCardPagerAdapter
        }
        viewModel.happyCardResponse.observe(this) { happyCard ->
            happyRoutineAddCardPagerAdapter?.submitList(happyCard.subRoutines)
        }
    }

    private fun setIndicator() {
        binding.diHappyAddDetailIndicator.attachTo(binding.vpHappyAddDetailCard)
    }

    private fun initSetLoading() {
        val dialog = LoadingIndicator(this@HappyDetailActivity)
        CoroutineScope(Dispatchers.Main).launch {
            dialog.show()
            delay(LOADING_DELAY)
            dialog.dismiss()
        }
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