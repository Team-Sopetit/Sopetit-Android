package com.sopetit.softie.ui.onboarding.themechoice

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.sopetit.softie.R
import com.sopetit.softie.databinding.FragmentOnboardingChoiceThemeBinding
import com.sopetit.softie.ui.onboarding.OnboardingActivity.Companion.MAXIMUM_THEME_SELECTION
import com.sopetit.softie.ui.onboarding.OnboardingViewModel
import com.sopetit.softie.util.binding.BindingFragment
import com.sopetit.softie.util.setSingleOnClickListener
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

        initMakeThemeAdapter()
        initChangeFragment()
    }

    private fun initMakeThemeAdapter() {
        _choiceThemeAdapter = ChoiceThemeAdapter()
        themeViewModel.getThemeList()
        binding.rvOnboardingChoiceTheme.apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = choiceThemeAdapter
        }

        setThemeList()
        selectThemes()
    }

    private fun setThemeList() {
        themeViewModel.themeList.observe(viewLifecycleOwner) {
            choiceThemeAdapter.submitList(it)
        }
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
                binding.btnOnboardingChoiceTheme.setSingleOnClickListener {
                    viewModel.changeRoutineChoiceView(true)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _choiceThemeAdapter = null
    }
}
