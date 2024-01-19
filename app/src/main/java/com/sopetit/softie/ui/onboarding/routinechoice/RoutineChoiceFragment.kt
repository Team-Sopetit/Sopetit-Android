package com.sopetit.softie.ui.onboarding.routinechoice

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(OnboardingViewModel::class.java)
        binding.viewModel = viewModel
        binding.routineViewModel = routineViewModel

        initSetThemeBackBtn()
        initSetSelectRoutineBtn()
        initMakeRoutineAdapter()
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
