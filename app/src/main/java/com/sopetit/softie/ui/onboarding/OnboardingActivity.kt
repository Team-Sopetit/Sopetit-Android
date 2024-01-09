package com.sopetit.softie.ui.onboarding

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.sopetit.softie.R
import com.sopetit.softie.databinding.ActivityOnboardingBinding
import com.sopetit.softie.ui.onboarding.routinechoice.RoutineChoiceFragment
import com.sopetit.softie.ui.onboarding.routinechoice.RoutineChoiceViewModel
import com.sopetit.softie.ui.onboarding.routinechoice.TestFragment
import com.sopetit.softie.util.binding.BindingActivity

class OnboardingActivity :
    BindingActivity<ActivityOnboardingBinding>(R.layout.activity_onboarding) {

    private val viewModel by viewModels<RoutineChoiceViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        initMakeFragment()
        initChangeFragment()
    }

    private fun initMakeFragment() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fcv_onboarding_fragment)
        if (currentFragment == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fcv_onboarding_fragment, TestFragment())
                .commit()
        }
    }

    private fun initChangeFragment() {
        initChangeRoutineChoice()
    }

    private fun initChangeRoutineChoice() {
        viewModel.routineChoiceView.observe(this) {
            if (it) {
                changeFragment(RoutineChoiceFragment())
            }
        }
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_onboarding_fragment, fragment)
            .commit()
    }
}
