package com.sopetit.softie.ui.onboarding.themechoice

import android.os.Bundle
import android.view.View
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

        initChangeFragment()
        initMakeThemeAdapter()
    }

    private fun initMakeThemeAdapter() {
        _choiceThemeAdapter = ChoiceThemeAdapter()
        binding.rcvOnboardingChoiceTheme.apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = choiceThemeAdapter
        }
        themeViewModel.mockThemeList.observe(viewLifecycleOwner) {
            choiceThemeAdapter.submitList(it)
        }
    }

    private fun initChangeFragment() {
        // TODO 버튼 클릭시 '루틴 선택' fragment로 화면 전환 (밑의 주석 삭제 예정)
//        binding.clOnboardingChoiceTheme.setOnClickListener {
//            viewModel.changeRoutineChoiceView()
//        }
    }
}
