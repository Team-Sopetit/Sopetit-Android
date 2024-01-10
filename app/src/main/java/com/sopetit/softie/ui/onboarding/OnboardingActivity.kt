package com.sopetit.softie.ui.onboarding

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.sopetit.softie.R
import com.sopetit.softie.databinding.ActivityOnboardingBinding
import com.sopetit.softie.ui.onboarding.bearselection.BearSelectionFragment
import com.sopetit.softie.ui.onboarding.routinechoice.RoutineChoiceFragment
import com.sopetit.softie.ui.onboarding.themechoice.ChoiceThemeFragment
import com.sopetit.softie.util.binding.BindingActivity

class OnboardingActivity :
    BindingActivity<ActivityOnboardingBinding>(R.layout.activity_onboarding) {

    private val viewModel by viewModels<OnboardingViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        initStatusBarColor()
        initMakeFragment()
        initChangeFragment()
    }

    private fun initStatusBarColor() {
        window.statusBarColor = ContextCompat.getColor(this, R.color.background)
    }

    private fun initMakeFragment() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fcv_onboarding_fragment)
        if (currentFragment == null) {
            viewModel.changeBearChoiceView()
            supportFragmentManager.beginTransaction()
                .add(R.id.fcv_onboarding_fragment, BearSelectionFragment())
                .commit()
        }
    }

    private fun initChangeFragment() {
        initChangeRoutineChoice()
        initChangeSetBearName()
        initChangeThemeChoice()
    }

    private fun initChangeSetBearName() {
        viewModel.bearNameChoiceView.observe(this) { bearNameChoice ->
            if (bearNameChoice) {
                changeFragment(SetBearNameFragment())
            }
        }
    }

    private fun initChangeThemeChoice() {
        viewModel.themeChoiceView.observe(this) { themeChoiceView ->
            if (themeChoiceView) {
                changeFragment(ChoiceThemeFragment())
                viewModel.setLayoutTranslucent(true)
            }
        }
    }

    private fun initChangeRoutineChoice() {
        viewModel.routineChoiceView.observe(this) { routineChoiceView ->
            if (routineChoiceView) {
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
