package com.sopetit.softie.ui.onboarding.bearselection

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.sopetit.softie.R
import com.sopetit.softie.databinding.FragmentOnboardingBearSelectionBinding
import com.sopetit.softie.ui.onboarding.OnboardingViewModel
import com.sopetit.softie.ui.onboarding.OnboardingViewModel.Companion.BROWN
import com.sopetit.softie.util.binding.BindingFragment
import com.sopetit.softie.util.setSingleOnClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BearSelectionFragment :
    BindingFragment<FragmentOnboardingBearSelectionBinding>(R.layout.fragment_onboarding_bear_selection) {

    private lateinit var activityViewModel: OnboardingViewModel
    private val viewModel by viewModels<BearSelectionViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activityViewModel =
            ViewModelProvider(requireActivity())[OnboardingViewModel::class.java]
        binding.viewModel = viewModel

        initChangeFragment()
    }

    private fun initChangeFragment() {
        binding.btnBearSelection.setSingleOnClickListener {
            viewModel.setBearType()
            activityViewModel.changeBearNameChoiceView()
            activityViewModel.setSelectedBearType(viewModel.selectedBearType.value ?: BROWN)

            activityViewModel.selectedBearType.observe(viewLifecycleOwner) {
                activityViewModel.setDollFace(it)
            }
        }
    }
}
