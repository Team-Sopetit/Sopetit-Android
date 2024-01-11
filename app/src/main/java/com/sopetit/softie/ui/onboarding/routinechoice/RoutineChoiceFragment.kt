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
import timber.log.Timber

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

        Timber.d("routineChoice: theme -> ${viewModel.selectedThemeArray.value}")

        initSetThemeBackBtn()
        initSetRoutineBtn()
        initSetSelectRoutineBtn()
        initMakeRoutineAdapter()
    }

    private fun initSetThemeBackBtn() {
        binding.btnOnboardingRoutineBackTheme.setOnClickListener {
            viewModel.changeSecondThemeChoiceView()
        }
    }

    private fun initSetRoutineBtn() {
        binding.rvOnboardingChoiceRoutine.setOnClickListener {
            // TODO 프로필 생성 서버 통신
        }
    }

    private fun initSetSelectRoutineBtn() {
        binding.btnOnboardingRoutineSelectRoutine.setOnClickListener {
            val onboardingActivity = activity as OnboardingActivity

            val intentToMain = Intent(activity, MainActivity::class.java)
            startActivity(intentToMain)
            ActivityCompat.finishAffinity(onboardingActivity)
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
        routineViewModel.mockRoutineList.observe(viewLifecycleOwner) {
            choiceRoutineAdapter.submitList(it)
        }
    }

    private fun selectRoutines() {
        choiceRoutineAdapter.setOnRoutineClickListener {
            Timber.d("routineChoice: routine -> ${choiceRoutineAdapter.selectedRoutineArray}")
            setNoticeVisible()
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
