package com.sopetit.softie.ui.onboarding

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.sopetit.softie.R
import com.sopetit.softie.databinding.FragmentOnboardingSetBearNameBinding
import com.sopetit.softie.util.binding.BindingFragment

class SetBearNameFragment :
    BindingFragment<FragmentOnboardingSetBearNameBinding>(R.layout.fragment_onboarding_set_bear_name) {

    private lateinit var viewModel: OnboardingViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(OnboardingViewModel::class.java)
        binding.viewModel = viewModel

        initChangeFragment()
    }

    private fun initChangeFragment() {
        binding.clOnboardingSetBearName.setOnClickListener {
            viewModel.changeThemeChoiceView()
        }
    }
}
