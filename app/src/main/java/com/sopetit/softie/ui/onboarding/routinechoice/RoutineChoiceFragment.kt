package com.sopetit.softie.ui.onboarding.routinechoice

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.sopetit.softie.R
import com.sopetit.softie.databinding.FragmentOnboardingChoiceRoutineBinding
import com.sopetit.softie.ui.onboarding.OnboardingViewModel
import com.sopetit.softie.util.binding.BindingFragment
import timber.log.Timber

class RoutineChoiceFragment :
    BindingFragment<FragmentOnboardingChoiceRoutineBinding>(R.layout.fragment_onboarding_choice_routine) {

    private lateinit var viewModel: OnboardingViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(OnboardingViewModel::class.java)
        binding.viewModel = viewModel

        Timber.d("routineChoice: ${viewModel.selectedThemeArray.value}")
    }
}
