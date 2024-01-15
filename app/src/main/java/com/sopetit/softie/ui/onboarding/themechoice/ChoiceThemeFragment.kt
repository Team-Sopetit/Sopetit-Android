package com.sopetit.softie.ui.onboarding.themechoice

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import coil.load
import com.sopetit.softie.R
import com.sopetit.softie.databinding.FragmentOnboardingChoiceThemeBinding
import com.sopetit.softie.ui.onboarding.OnboardingActivity.Companion.MAXIMUM_THEME_SELECTION
import com.sopetit.softie.ui.onboarding.OnboardingViewModel
import com.sopetit.softie.ui.onboarding.OnboardingViewModel.Companion.BROWN
import com.sopetit.softie.ui.onboarding.OnboardingViewModel.Companion.GRAY
import com.sopetit.softie.ui.onboarding.OnboardingViewModel.Companion.PANDA
import com.sopetit.softie.ui.onboarding.OnboardingViewModel.Companion.RED
import com.sopetit.softie.util.binding.BindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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

        initGetBearFace()
        initMakeThemeAdapter()
        initChangeFragment()
    }

    private fun initGetBearFace() {
        viewModel.bearFace.observe(viewLifecycleOwner) {
            when (it) {
                BROWN -> themeViewModel.getBearFace(BROWN)
                GRAY -> themeViewModel.getBearFace(GRAY)
                PANDA -> themeViewModel.getBearFace(PANDA)
                RED -> themeViewModel.getBearFace(RED)
                else -> themeViewModel.getBearFace(BROWN)
            }
        }

        initMakeBearFace()
    }

    private fun initMakeBearFace() {
        themeViewModel.bearFace.observe(viewLifecycleOwner) {
            binding.ivOnboardingChoiceThemeTitleBear.load(it)
        }
    }

    private fun initMakeThemeAdapter() {
        _choiceThemeAdapter = ChoiceThemeAdapter()
        themeViewModel.getThemeList()
        binding.rvOnboardingChoiceTheme.apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = choiceThemeAdapter
        }
        themeViewModel.themeList.observe(viewLifecycleOwner) {
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
                    getSelectedThemeId()
                    viewModel.changeRoutineChoiceView(true)
                }
            }
        }
    }

    private fun getSelectedThemeId() {
        viewModel.selectedThemeArray.observe(viewLifecycleOwner) {
            viewModel.setSelectedThemeIdList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _choiceThemeAdapter = null
    }
}
