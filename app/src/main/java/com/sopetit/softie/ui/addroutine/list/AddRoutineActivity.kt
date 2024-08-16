package com.sopetit.softie.ui.addroutine.list

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.activity.viewModels
import com.skydoves.balloon.createBalloon
import com.skydoves.balloon.overlay.BalloonOverlayAnimation
import com.skydoves.balloon.overlay.BalloonOverlayCircle
import com.sopetit.softie.R
import com.sopetit.softie.databinding.ActivityAddRoutineListBinding
import com.sopetit.softie.util.HorizontalItemDecoration
import com.sopetit.softie.util.VerticalItemDecoration
import com.sopetit.softie.util.binding.BindingActivity
import com.sopetit.softie.util.setSingleOnClickListener
import com.sopetit.softie.util.setStatusBarColorFromResource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddRoutineActivity : BindingActivity<ActivityAddRoutineListBinding>(R.layout.activity_add_routine_list) {
    private val makerCardPagerAdapter = MakerCardAdapter()
    private val viewModel by viewModels<AddRoutineViewModel>()
    private var routineThemeListAdapter: RoutineThemeListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        setStatusBarColorFromResource(R.color.background)

        startMakerTooltipModal()
        setBackEnter()
        setInitBinding()
        setAddRoutineListCardPagerAdapter()
        setRoutineThemeListAdapter()
        setItemDeco()
        setupUI()
        setupObservers()
    }

    private fun startMakerTooltipModal() {
        binding.ivAddRoutineListMakerHelp.setSingleOnClickListener { view ->
            showTooltip(view)
        }
    }

    private fun showTooltip(anchor: View) {
        val balloon =
            createBalloon(this) {
                setLayout(R.layout.tooltip_maker_help)
                setBackgroundDrawableResource(R.drawable.shape_gray0_fill_10_rect)
                setIsVisibleArrow(false)
                setDismissWhenClicked(false)
                setIsVisibleOverlay(true)
                setOverlayShape(BalloonOverlayCircle(radius = 0f))
                setOverlayColorResource(R.color.gray950)
                setBalloonOverlayAnimation(BalloonOverlayAnimation.FADE)
                setDismissWhenOverlayClicked(false)
                setLifecycleOwner(lifecycleOwner)
            }

        balloon.setOnBalloonInitializedListener { view ->
            view.findViewById<ImageView>(R.id.iv_maker_help_tooltip_exit)?.setOnClickListener {
                balloon.dismiss()
            }
        }

        balloon.showAlignBottom(anchor)
    }

    private fun setBackEnter() {
        binding.ivAddRoutineListBackArrow.setSingleOnClickListener {
            finish()
        }
    }

    private fun setInitBinding() {
        viewModel.getMakerCard()
        viewModel.getRoutineTheme()
    }

    private fun setAddRoutineListCardPagerAdapter() {
        with(binding) {
            rvAddRoutineListMakerCard.adapter = makerCardPagerAdapter
        }
    }

    private fun setRoutineThemeListAdapter() {
        with(binding) {
            routineThemeListAdapter = RoutineThemeListAdapter()
            rvAddList.adapter = routineThemeListAdapter
        }
    }

    private fun setItemDeco() {
        val horizontalDecoration =
            HorizontalItemDecoration(
                context = this,
                firstItemMargin = R.dimen.maker_recycler_view_first_margin,
                itemMargin = R.dimen.maker_recycler_view_margin
            )
        val verticalDecoration =
            VerticalItemDecoration(
                context = this,
                firstItemMargin = R.dimen.add_routine_recycler_view_first_margin,
                itemMargin = R.dimen.add_routine_recycler_view_margin
            )
        binding.rvAddRoutineListMakerCard.addItemDecoration(horizontalDecoration)
        binding.rvAddList.addItemDecoration(verticalDecoration)
    }

    private fun setupUI() {
        binding.ivAddRoutineListMakerHelp.setOnClickListener {
            showTooltip(it)
        }
    }

    private fun setupObservers() {
        viewModel.addRoutineThemeListResponse.observe(this) { routineTheme ->
            routineTheme?.let {
                routineThemeListAdapter?.submitList(routineTheme.themes)
            }
        }

        viewModel.addMakerCardResponse.observe(this) { makerCards ->
            makerCards?.let {
                makerCardPagerAdapter.submitList(makerCards)
            }
        }
    }
}
