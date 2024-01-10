package com.sopetit.softie.ui.onboarding.routinechoice

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sopetit.softie.R
import com.sopetit.softie.databinding.FragmentOnboardingChoiceRoutineBinding
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

        initMakeRoutineAdapter()
    }

    private fun initMakeRoutineAdapter() {
        _choiceRoutineAdapter = RoutineChoiceAdapter()
        binding.rvOnboardingChoiceRoutine.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = choiceRoutineAdapter
        }
        routineViewModel.mockRoutineList.observe(viewLifecycleOwner) {
            choiceRoutineAdapter.submitList(it)
        }

        selectRoutines()
    }

    private fun selectRoutines() {
        choiceRoutineAdapter.setOnRoutineClickListener {
            Timber.d("routineChoice: routine -> ${choiceRoutineAdapter.selectedRoutineArray}")
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
