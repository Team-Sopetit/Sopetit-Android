package com.sopetit.softie.ui.onboarding.dollselection

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.sopetit.softie.R
import com.sopetit.softie.databinding.FragmentOnboardingDollSelectionBinding
import com.sopetit.softie.ui.onboarding.OnboardingViewModel
import com.sopetit.softie.util.binding.BindingFragment

class DollSelectionFragment :
    BindingFragment<FragmentOnboardingDollSelectionBinding>(R.layout.fragment_onboarding_doll_selection) {

    private lateinit var viewModel: OnboardingViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(OnboardingViewModel::class.java)
        binding.viewModel = viewModel

        initChangeFragment()
    }

    private fun initChangeFragment() {
        binding.clOnboardingChoiceBear.setOnClickListener {
            viewModel.changeBearNameChoiceView()
        }
    }
}
