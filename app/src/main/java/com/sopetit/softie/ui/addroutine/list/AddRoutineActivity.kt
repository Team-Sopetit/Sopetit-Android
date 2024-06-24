package com.sopetit.softie.ui.addroutine.list

import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.sopetit.softie.R
import com.sopetit.softie.databinding.ActivityAddRoutineListBinding
import com.sopetit.softie.util.HorizontalItemDecoration
import com.sopetit.softie.util.VerticalItemDecoration
import com.sopetit.softie.util.binding.BindingActivity
import com.sopetit.softie.util.setSingleOnClickListener
import com.sopetit.softie.util.setStatusBarColorFromResource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddRoutineActivity :
    BindingActivity<ActivityAddRoutineListBinding>(R.layout.activity_add_routine_list) {
    private val makerCardPagerAdapter = MakerCardAdapter()
    private val viewModel by viewModels<AddRoutineViewModel>()
    private var routineThemeListAdapter: RoutineThemeListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        setStatusBarColorFromResource(R.color.background)

        startMakerHelpModal()
        setBackEnter()
        setInitBinding()
        setAddRoutineListCardPagerAdapter()
        setRoutineThemeListAdapter()
        setItemDeco()
        setupObservers()
    }

    private fun startMakerHelpModal() {
        binding.ivAddRoutineListMakerHelp.setSingleOnClickListener {
            MakerHelpDialogFragment().show(supportFragmentManager, "MakerHelpDialog")
        }
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
        val horizontalDecoration = HorizontalItemDecoration(
            context = this,
            firstItemMargin = R.dimen.maker_recycler_view_first_margin,
            itemMargin = R.dimen.maker_recycler_view_margin
        )
        val verticalDecoration = VerticalItemDecoration(
            context = this,
            firstItemMargin = R.dimen.add_routine_recycler_view_first_margin,
            itemMargin = R.dimen.add_routine_recycler_view_margin
        )
        binding.rvAddRoutineListMakerCard.addItemDecoration(horizontalDecoration)
        binding.rvAddList.addItemDecoration(verticalDecoration)
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
