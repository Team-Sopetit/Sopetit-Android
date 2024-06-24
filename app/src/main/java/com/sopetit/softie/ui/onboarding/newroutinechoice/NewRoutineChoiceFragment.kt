package com.sopetit.softie.ui.onboarding.newroutinechoice

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.sopetit.softie.R
import com.sopetit.softie.databinding.FragmentOnboardingChoiceRoutineNewBinding
import com.sopetit.softie.util.binding.BindingFragment

class NewRoutineChoiceFragment :
    BindingFragment<FragmentOnboardingChoiceRoutineNewBinding>(R.layout.fragment_onboarding_choice_routine_new) {

    private val routineViewModel by viewModels<NewRoutineChoiceViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.routineViewModel = routineViewModel
    }

}
