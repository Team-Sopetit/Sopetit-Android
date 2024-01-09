package com.sopetit.softie.ui.onboarding

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.sopetit.softie.R
import com.sopetit.softie.databinding.FragmentOnboardingChoiceBearBinding
import com.sopetit.softie.util.binding.BindingFragment

class BearChoiceFragment :
    BindingFragment<FragmentOnboardingChoiceBearBinding>(R.layout.fragment_onboarding_choice_bear) {

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
