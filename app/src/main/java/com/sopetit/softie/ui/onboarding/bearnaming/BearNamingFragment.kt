package com.sopetit.softie.ui.onboarding.bearnaming

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.sopetit.softie.R
import com.sopetit.softie.databinding.FragmentOnboardingBearNamingBinding
import com.sopetit.softie.ui.onboarding.OnboardingViewModel
import com.sopetit.softie.ui.onboarding.OnboardingViewModel.Companion.BROWN_BEAR
import com.sopetit.softie.ui.onboarding.OnboardingViewModel.Companion.GRAY_BEAR
import com.sopetit.softie.ui.onboarding.OnboardingViewModel.Companion.NONE
import com.sopetit.softie.ui.onboarding.OnboardingViewModel.Companion.PANDA_BEAR
import com.sopetit.softie.ui.onboarding.OnboardingViewModel.Companion.RED_BEAR
import com.sopetit.softie.util.binding.BindingFragment

class BearNamingFragment :
    BindingFragment<FragmentOnboardingBearNamingBinding>(R.layout.fragment_onboarding_bear_naming) {

    private lateinit var activityViewModel: OnboardingViewModel
    private val viewModel by viewModels<BearNamingViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activityViewModel = ViewModelProvider(requireActivity())[OnboardingViewModel::class.java]
        binding.viewModel = viewModel

        setLottieFile()
        setEditTextFilter()
        setNicknameObserver()
        initChangeFragment()
    }

    private fun setLottieFile() {
        when (activityViewModel.selectedBearType.value) {
            NONE -> setLottieAnimation(R.raw.brown_hello)
            BROWN_BEAR -> setLottieAnimation(R.raw.brown_hello)
            GRAY_BEAR -> setLottieAnimation(R.raw.gray_hello)
            RED_BEAR -> setLottieAnimation(R.raw.red_hello)
            PANDA_BEAR -> setLottieAnimation(R.raw.panda_hello)
        }
    }

    private fun setLottieAnimation(file: Int) {
        binding.lottieBearNamingGreeting.setAnimation(file)
    }

    private fun setEditTextFilter() {
        binding.etBearNaming.filters = arrayOf(viewModel.filterSpecialCharacter)
    }

    private fun setNicknameObserver() {
        viewModel.nickname.observe(viewLifecycleOwner) {
            viewModel.checkIsNicknameValid()
        }
    }

    private fun initChangeFragment() {
        binding.btnBearSelection.setOnClickListener {
            activityViewModel.setBearNickname(viewModel.nickname.value ?: "")
            activityViewModel.changeThemeChoiceView()
        }
    }
}
