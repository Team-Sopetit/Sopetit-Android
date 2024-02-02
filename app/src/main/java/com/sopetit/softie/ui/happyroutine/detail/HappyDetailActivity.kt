package com.sopetit.softie.ui.happyroutine.detail

import android.graphics.Color
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import coil.load
import com.sopetit.softie.R
import com.sopetit.softie.databinding.ActivityHappyAddDetailBinding
import com.sopetit.softie.ui.LoadingIndicator
import com.sopetit.softie.ui.happyroutine.list.HappyAddListActivity.Companion.ICON_IMAGE_URL
import com.sopetit.softie.ui.happyroutine.list.HappyAddListActivity.Companion.ID
import com.sopetit.softie.ui.onboarding.OnboardingActivity.Companion.LOADING_DELAY
import com.sopetit.softie.util.OriginalBottomSheet
import com.sopetit.softie.util.binding.BindingActivity
import com.sopetit.softie.util.binding.BindingBottomSheet
import com.sopetit.softie.util.setSingleOnClickListener
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
    private val happyRoutineAddCardPagerAdapter = HappyDetailCardPagerAdapter()
    private val viewModel by viewModels<HappyDetailCardViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        viewPager = binding.vpHappyAddDetailCard
        setStatusBarColorFromResource(R.color.background)

        val routineId = intent.getIntExtra(ID, -1).toString()
        val imageUrl = intent.getStringExtra(ICON_IMAGE_URL)

        setInitBinding(routineId, imageUrl)
        setCurrentCard()
        setBackEnter()
        setupAdapter(routineId)
        setIndicator()
        initSetLoading()
        initViewPager()
        initPagerDiv(0, 20)
    }

    private fun setInitBinding(routineId: String, imageUrl: String?) {
        viewModel.getHappyCard(routineId)
        viewModel.happyCardResponse.observe(this) { happyCard ->
            happyCard?.let {
                with(binding) {
                    tvHappyAddDetailTitle.text = happyCard.name
                    ivHappyAddDetailIcon.load(happyCard.iconImageUrl)
                    tvHappyAddDetailSubtitle.text = happyCard.title
                    tvHappyAddDetailTitle.setTextColor(Color.parseColor(happyCard.nameColor))
                }
                viewModel.mySubroutineId.observe(this) { mySubRoutineId ->
                    mySubRoutineId?.let {
                        imageUrl?.let {
                            setBottomSheetEnter(it, mySubRoutineId)
                        }
                    }
                }
            }
        }
    }

    private fun setCurrentCard() {
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                val currentSubRoutineId = getCurrentSelectedSubRoutineId()
                viewModel.setSubRoutineId(currentSubRoutineId)
            }
        })
    }

    private fun getCurrentSelectedSubRoutineId(): Int {
        val currentItem = binding.vpHappyAddDetailCard.currentItem
        val itemId = happyRoutineAddCardPagerAdapter.getItemId(currentItem)
        return itemId.toInt()
    }

    private fun setBackEnter() {
        binding.ivHappyAddDetailBack.setSingleOnClickListener {
            finish()
        }
    }

    private fun setBottomSheetEnter(icon: String, subRoutineId: Int) {
        binding.btnHappyDetailAdd.setSingleOnClickListener {
            initHappyRoutineAddBottomSheet(icon, subRoutineId)
        }
    }

    private fun initHappyRoutineAddBottomSheet(icon: String, subRoutineId: Int) {
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
                viewModel.postAddRoutine(subRoutineId)
                moveToProgress()
            }
        ).show(this.supportFragmentManager, OriginalBottomSheet.BOTTOM_SHEET_TAG)
    }

    private fun moveToProgress() {
        setResult(RESULT_OK)
        finish()
    }

    private fun setupAdapter(routineId: String) {
        with(binding) {
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
        val margin = (dp * d * 0.8).toInt()

        with(binding.vpHappyAddDetailCard) {
            clipChildren = false
            clipToPadding = false
            offscreenPageLimit = 3
            setPadding(margin, 0, margin, 0)
        }
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
