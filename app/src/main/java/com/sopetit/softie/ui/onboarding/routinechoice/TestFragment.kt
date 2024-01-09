package com.sopetit.softie.ui.onboarding.routinechoice

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.sopetit.softie.R
import com.sopetit.softie.databinding.FragmentOnboardingTestBinding
import com.sopetit.softie.util.binding.BindingFragment

class TestFragment :
    BindingFragment<FragmentOnboardingTestBinding>(R.layout.fragment_onboarding_test) {

    private lateinit var viewModel: RoutineChoiceViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(RoutineChoiceViewModel::class.java)
        binding.viewModel = viewModel

        initChangeFragment()
    }

    private fun initChangeFragment() {
        binding.clOnboardingTest.setOnClickListener {
            viewModel.changeRoutineChoiceView()
        }
    }
}
