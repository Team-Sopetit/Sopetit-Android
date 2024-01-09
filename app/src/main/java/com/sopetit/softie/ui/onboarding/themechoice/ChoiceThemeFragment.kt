package com.sopetit.softie.ui.onboarding.themechoice

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.sopetit.softie.R
import com.sopetit.softie.databinding.FragmentOnboardingChoiceThemeBinding
import com.sopetit.softie.ui.onboarding.OnboardingViewModel
import com.sopetit.softie.util.binding.BindingFragment

class ChoiceThemeFragment :
    BindingFragment<FragmentOnboardingChoiceThemeBinding>(R.layout.fragment_onboarding_choice_theme) {

    private lateinit var viewModel: OnboardingViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(OnboardingViewModel::class.java)
        binding.viewModel = viewModel

        initChangeFragment()
    }

    private fun initChangeFragment() {
        binding.clOnboardingChoiceTheme.setOnClickListener {
            viewModel.changeRoutineChoiceView()
        }
    }
}
