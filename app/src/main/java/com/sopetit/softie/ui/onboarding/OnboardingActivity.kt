package com.sopetit.softie.ui.onboarding

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.MotionEvent
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.sopetit.softie.R
import com.sopetit.softie.databinding.ActivityOnboardingBinding
import com.sopetit.softie.ui.LoadingIndicator
import com.sopetit.softie.ui.onboarding.bearnaming.BearNamingFragment
import com.sopetit.softie.ui.onboarding.bearselection.BearSelectionFragment
import com.sopetit.softie.ui.onboarding.routinechoice.RoutineChoiceFragment
import com.sopetit.softie.ui.onboarding.themechoice.ChoiceThemeFragment
import com.sopetit.softie.util.binding.BindingActivity
import com.sopetit.softie.util.binding.BindingAdapter.setImage
import com.sopetit.softie.util.hideKeyboard
import com.sopetit.softie.util.setStatusBarColorFromResource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class OnboardingActivity :
    BindingActivity<ActivityOnboardingBinding>(R.layout.activity_onboarding) {

    private val viewModel by viewModels<OnboardingViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        setStatusBarColorFromResource(R.color.background)

        initMakeFragment()
        initChangeFragment()
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        hideKeyboard(currentFocus)
        return super.dispatchTouchEvent(ev)
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
                changeFragment(BearNamingFragment())
            }
        }
    }

    private fun initChangeThemeChoice() {
        viewModel.themeChoiceView.observe(this) { themeChoiceView ->
            if (themeChoiceView) {
                changeFragment(ChoiceThemeFragment())
                initSetLoading()
                viewModel.setLayoutTranslucent(true)
                setStatusBarColorFromResource(R.color.onboarding_translucent)
                initSetBearFace()
                initSetSpeechText()
                initSetTranslucentBackground()
                changeSecondThemeChoice()
            }
        }
    }

    private fun initSetLoading() {
        val dialog = LoadingIndicator(this@OnboardingActivity)
        CoroutineScope(Dispatchers.Main).launch {
            dialog.show()
            delay(LOADING_DELAY)
            dialog.dismiss()
        }
    }

    private fun initSetBearFace() {
        viewModel.bearFace.observe(this) { bearFace ->
            binding.ivOnboardingThemeTitleBear.setImage(bearFace)
        }
    }

    private fun changeSecondThemeChoice() {
        viewModel.secondThemeChoiceView.observe(this) { isSecondThemeView ->
            if (isSecondThemeView) {
                changeFragment(ChoiceThemeFragment())
                viewModel.setLayoutTranslucent(false)
                setStatusBarColorFromResource(R.color.background)
                viewModel.changeRoutineChoiceView(false)
            }
        }
    }

    private fun initSetSpeechText() {
        val nickname = viewModel.bearNickname.value ?: ""
        val message = getString(R.string.onboarding_choice_theme_speech).format(nickname)

        binding.tvOnboardingThemeSpeech.text =
            SpannableStringBuilder(message).apply {
                setSpan(
                    ForegroundColorSpan(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.onboarding_speech
                        )
                    ),
                    SPAN_START,
                    SPAN_START + nickname.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
    }

    private fun initSetTranslucentBackground() {
        binding.clOnboardingThemeTranslucentBackgroundContent.setOnClickListener {
            viewModel.setLayoutTranslucent(false)
            setStatusBarColorFromResource(R.color.background)
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

    companion object {
        const val SPAN_START = 5
        const val MAXIMUM_THEME_SELECTION = 3
        const val LOADING_DELAY = 1000L
    }
}
