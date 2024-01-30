package com.sopetit.softie.ui.onboarding.routinechoice

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sopetit.softie.R
import com.sopetit.softie.databinding.FragmentOnboardingChoiceRoutineBinding
import com.sopetit.softie.ui.main.MainActivity
import com.sopetit.softie.ui.onboarding.OnboardingActivity
import com.sopetit.softie.ui.onboarding.OnboardingViewModel
import com.sopetit.softie.util.binding.BindingFragment
import com.sopetit.softie.util.setSingleOnClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoutineChoiceFragment :
    BindingFragment<FragmentOnboardingChoiceRoutineBinding>(R.layout.fragment_onboarding_choice_routine) {

    private lateinit var viewModel: OnboardingViewModel
    private val routineViewModel by viewModels<RoutineChoiceViewModel>()

    private var _choiceRoutineAdapter: RoutineChoiceAdapter? = null
    private val choiceRoutineAdapter
        get() = requireNotNull(_choiceRoutineAdapter)

    private lateinit var menu: Menu

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(OnboardingViewModel::class.java)
        binding.viewModel = viewModel
        binding.routineViewModel = routineViewModel

        initSetThemeBackBtn()
        initSetSelectRoutineBtn()
        initMakeRoutineAdapter()
        setItemDiv()
    }

    private fun initSetThemeBackBtn() {
        binding.btnOnboardingRoutineBackTheme.setSingleOnClickListener {
            viewModel.changeSecondThemeChoiceView()
        }
    }

    private fun initSetSelectRoutineBtn() {
        binding.btnOnboardingRoutineSelectRoutine.setSingleOnClickListener {
            postMemberInfo()
        }
    }

    private fun postMemberInfo() {
        viewModel.selectedRoutineArray.value?.let {
            routineViewModel.postNewMember(
                viewModel.selectedBearType.value ?: "",
                viewModel.bearNickname.value ?: "",
                it
            )
        }
        moveToHome()
    }

    private fun moveToHome() {
        val onboardingActivity = activity as OnboardingActivity
        val intentToMain = Intent(activity, MainActivity::class.java)

        routineViewModel.isPostNewMember.observe(viewLifecycleOwner) { isPostSuccess ->
            if (isPostSuccess) {
                startActivity(intentToMain)
                ActivityCompat.finishAffinity(onboardingActivity)
            }
        }
    }

//    private fun initMakeRoutineAdapter() {
//        _choiceRoutineAdapter = RoutineChoiceAdapter()
//        binding.rvOnboardingChoiceRoutine.apply {
//            layoutManager = LinearLayoutManager(requireContext())
//            adapter = choiceRoutineAdapter
//        }
//
//        viewModel.selectedThemeArray.value?.let { routineViewModel.getRoutineList(it.toList()) }
//        routineViewModel.routineList.observe(viewLifecycleOwner) {
//            choiceRoutineAdapter.submitList(it)
//        }
//
//        val tracker = SelectionTracker.Builder(
//            "routineSelection",
//            binding.rvOnboardingChoiceRoutine,
//            StableIdKeyProvider(binding.rvOnboardingChoiceRoutine),
////            RoutineChoiceAdapter.SelectionKeyProvider(binding.rvOnboardingChoiceRoutine),
//            RoutineChoiceAdapter.SelectionDetailsLookup(binding.rvOnboardingChoiceRoutine),
//            StorageStrategy.createLongStorage()
//        ).withSelectionPredicate(
//            SelectionPredicates.createSelectAnything()
//        ).build()
//
//        choiceRoutineAdapter.setSelectionTracker(tracker)
//
//        tracker.addObserver(object : SelectionTracker.SelectionObserver<Long>() {
//            override fun onItemStateChanged(key: Long, selected: Boolean) {
//                super.onItemStateChanged(key, selected)
//
//                Timber.d("routine choice -> 아이템: ${choiceRoutineAdapter.getItemId(key.toInt())}")
//            }
//
//            override fun onSelectionChanged() {
//                super.onSelectionChanged()
//
//                if (tracker.hasSelection()) {
////                    val selectedItems = tracker.selection
//                    Timber.d("routine choice -> 선택된 아이템 개수: ${tracker.selection.size()}")
//                    Timber.d("routine choice -> 선택된 아이템: ${tracker.selection}")
//                }
//            }
//        })
//
////        Timber.d("routine choice -> tracker: $tracker")
////        Timber.d("routine choice -> tracker selection: ${tracker.selection}")
//    }

    private fun initMakeRoutineAdapter() {
        _choiceRoutineAdapter = RoutineChoiceAdapter()
        binding.rvOnboardingChoiceRoutine.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = choiceRoutineAdapter
        }
        updateRoutines()
        selectRoutines()
    }

    private fun updateRoutines() {
        viewModel.selectedThemeArray.value?.let { routineViewModel.getRoutineList(it.toList()) }
        routineViewModel.routineList.observe(viewLifecycleOwner) {
            choiceRoutineAdapter.submitList(it)
        }
    }

    private fun selectRoutines() {
        choiceRoutineAdapter.setOnRoutineClickListener {
            viewModel.setSelectedRoutineArray(choiceRoutineAdapter.selectedRoutineArray)
            setNoticeVisible()
            setRoutineBtn()
        }
    }

    class HorizontalItemDecorator(
        private val marginTop: Int,
        private val marginBottom: Int,
        private val itemMargin: Int
    ) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)

            val position = parent.getChildAdapterPosition(view)
            val itemCount = parent.adapter?.itemCount ?: 0

            if (position == 0) {
                outRect.top = marginTop
                outRect.bottom = itemMargin
            } else if (position == itemCount - 1) {
                outRect.top = itemMargin
                outRect.bottom = marginBottom
            } else {
                outRect.top = itemMargin
                outRect.bottom = itemMargin
            }
        }
    }

    private fun setItemDiv() {
        binding.rvOnboardingChoiceRoutine.addItemDecoration(HorizontalItemDecorator(30, 30, 0))
    }

    private fun setRoutineBtn() {
        viewModel.selectedRoutineArray.observe(viewLifecycleOwner) {
            if (it.size > 0) {
                routineViewModel.setRoutineBtnEnabled(true)
            } else {
                routineViewModel.setRoutineBtnEnabled(false)
            }
        }
    }

    private fun setNoticeVisible() {
        choiceRoutineAdapter.isRoutineNoticeVisible.observe(viewLifecycleOwner) { isVisible ->
            routineViewModel.setNoticeVisible(isVisible)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _choiceRoutineAdapter = null
    }

    companion object {
        const val MAXIMUM_ROUTINE_SELECTION = 3
    }
}
