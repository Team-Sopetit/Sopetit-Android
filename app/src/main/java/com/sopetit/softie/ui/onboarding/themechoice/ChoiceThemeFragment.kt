package com.sopetit.softie.ui.onboarding.themechoice

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.sopetit.softie.R
import com.sopetit.softie.databinding.FragmentOnboardingChoiceThemeBinding
import com.sopetit.softie.ui.onboarding.OnboardingViewModel
import com.sopetit.softie.util.binding.BindingFragment

class ChoiceThemeFragment :
    BindingFragment<FragmentOnboardingChoiceThemeBinding>(R.layout.fragment_onboarding_choice_theme) {

    private lateinit var viewModel: OnboardingViewModel
    private val themeViewModel by viewModels<ChoiceThemeViewModel>()

    private var _choiceThemeAdapter: ChoiceThemeAdapter? = null
    private val choiceThemeAdapter
        get() = requireNotNull(_choiceThemeAdapter)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(OnboardingViewModel::class.java)
        binding.viewModel = viewModel
        binding.themeViewModel = themeViewModel

        initSetTranslucentBackground()
        initSetSpeechText()
        initMakeThemeAdapter()
        initChangeFragment()
    }

    private fun initSetTranslucentBackground() {
        binding.clOnboardingChoiceThemeTranslucentBackground.setOnClickListener {
            themeViewModel.setLayoutTranslucent(false)
            viewModel.setLayoutTranslucent(false)
        }
    }

    private fun initSetSpeechText() {
        val nickname = viewModel.bearNickname.value ?: ""
        val message = getString(R.string.onboarding_choice_theme_speech).format(nickname)

        binding.tvOnboardingChoiceThemeSpeech.text =
            SpannableStringBuilder(message).apply {
                setSpan(
                    ForegroundColorSpan(
                        ContextCompat.getColor(
                            requireActivity(),
                            R.color.onboarding_speech
                        )
                    ),
                    SPAN_START,
                    SPAN_START + nickname.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
    }

    private fun initMakeThemeAdapter() {
        _choiceThemeAdapter = ChoiceThemeAdapter()
        binding.rvOnboardingChoiceTheme.apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = choiceThemeAdapter
        }
        themeViewModel.mockThemeList.observe(viewLifecycleOwner) {
            choiceThemeAdapter.submitList(it)
        }

        selectThemes()
    }

    private fun selectThemes() {
        choiceThemeAdapter.setOnThemeClickListener {
            viewModel.setSelectedThemeArray(choiceThemeAdapter.selectedThemeArray)
            setThemeBtn()
        }
    }

    private fun setThemeBtn() {
        viewModel.selectedThemeArray.observe(viewLifecycleOwner) {
            if (it.size == MAXIMUM_THEME_SELECTION) {
                themeViewModel.setThemeBtnEnabled(true)
            } else {
                themeViewModel.setThemeBtnEnabled(false)
            }
        }
    }

    private fun initChangeFragment() {
        themeViewModel.themeBtnEnabled.observe(viewLifecycleOwner) {
            if (it) {
                binding.btnOnboardingChoiceTheme.setOnClickListener {
                    viewModel.changeRoutineChoiceView()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _choiceThemeAdapter = null
    }

    companion object {
        const val SPAN_START = 5
        const val MAXIMUM_THEME_SELECTION = 3
    }
}
