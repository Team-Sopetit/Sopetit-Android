package com.sopetit.softie.ui.main.home

import android.animation.Animator
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.airbnb.lottie.LottieAnimationView
import com.sopetit.softie.R
import com.sopetit.softie.databinding.FragmentHomeBinding
import com.sopetit.softie.domain.entity.Cotton
import com.sopetit.softie.ui.onboarding.OnboardingViewModel.Companion.BROWN
import com.sopetit.softie.ui.onboarding.OnboardingViewModel.Companion.GRAY
import com.sopetit.softie.ui.onboarding.OnboardingViewModel.Companion.RED
import com.sopetit.softie.ui.onboarding.OnboardingViewModel.Companion.WHITE
import com.sopetit.softie.ui.setting.SettingActivity
import com.sopetit.softie.util.binding.BindingFragment
import com.sopetit.softie.util.setStatusBarColor
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random

@AndroidEntryPoint
class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val viewModel by viewModels<HomeViewModel>()
    private lateinit var userLottieList: List<Int>
    private lateinit var helloLottie: LottieAnimationView
    private lateinit var dailyLottie: LottieAnimationView
    private lateinit var happinessLottie: LottieAnimationView

    companion object {
        const val RUN_OUT = 0
        val START = 0
        val HELLO = 0
        val DAILY = 1
        val HAPPINESS = 2
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        setStatusBarColor(R.color.home_background)

        setLottieVariable()
        viewModel.getHome()
        setUserLottieList()
        setClickListener()
        setObserveHomeResponse()
        moveToPaymentView()

        val bottomSheet = HomeTutorialFragment()
        bottomSheet.show(requireActivity().supportFragmentManager, "HomeTutorialFragment")
    }

    private fun setLottieListener() {
        setLottieListener(helloLottie)
        setLottieListener(dailyLottie)
        setLottieListener(happinessLottie)
    }

    private fun setLottieVariable() {
        helloLottie = binding.lottieHomeBearHello
        dailyLottie = binding.lottieHomeBearDailyCotton
        happinessLottie = binding.lottieHomeBearHappinessCotton
    }

    private fun setUserLottieList() {
        userLottieList = when (viewModel.getBearType()) {
            BROWN -> listOf(R.raw.brown_hello, R.raw.brown_eating_daily, R.raw.brown_eating_happy)
            GRAY -> listOf(R.raw.gray_hello, R.raw.gray_eating_daily, R.raw.gray_eating_happy)
            RED -> listOf(R.raw.red_hello, R.raw.red_eating_daily, R.raw.red_eating_happy)
            WHITE -> listOf(R.raw.panda_hello, R.raw.panda_eating_daily, R.raw.panda_eating_happy)
            else -> listOf(R.raw.brown_hello, R.raw.brown_eating_daily, R.raw.brown_eating_happy)
        }
        helloLottie.setAnimation(userLottieList[HELLO])
        dailyLottie.setAnimation(userLottieList[DAILY])
        happinessLottie.setAnimation(userLottieList[HAPPINESS])
    }

    private fun setRandomMessage() {
        val speechNum = viewModel.homeResponse.value?.conversations?.size ?: 1
        val randomSpeech = Random.nextInt(START, speechNum)
        binding.tvHomeBearSpeech.text =
            viewModel.homeResponse.value?.conversations?.get(randomSpeech)
    }

    private fun setClickListener() {
        setLottieListener()
        setClickSetting()
        setClickBear()
        setClickDaily()
        setClickHappy()
    }

    private fun setClickSetting() {
        binding.ivHomeSetting.setOnClickListener {
            val intentToSetting = Intent(activity, SettingActivity::class.java)
            startActivity(intentToSetting)
        }
    }

    private fun setClickBear() {
        helloLottie.setOnClickListener { playHelloLottie() }
        happinessLottie.setOnClickListener { playHelloLottie() }
        dailyLottie.setOnClickListener { playHelloLottie() }
    }

    private fun playHelloLottie() {
        viewModel.updateLottieVisibility(
            helloLottie = true,
            dailyLottie = false,
            happinessLottie = false
        )
        helloLottie.playAnimation()
        setRandomMessage()
    }

    private fun setClickDaily() {
        binding.clHomeSomWhite.setOnClickListener {
            viewModel.updateLottieVisibility(
                helloLottie = false,
                dailyLottie = true,
                happinessLottie = false
            )
            checkCottonRemain(dailyLottie, Cotton.DAILY)
        }
    }

    private fun setClickHappy() {
        binding.clHomeSomColor.setOnClickListener {
            viewModel.updateLottieVisibility(
                helloLottie = false,
                dailyLottie = false,
                happinessLottie = true
            )
            checkCottonRemain(binding.lottieHomeBearHappinessCotton, Cotton.HAPPINESS)
        }
    }

    private fun checkCottonRemain(view: LottieAnimationView, cottonType: Cotton) {
        val isCottonRemain: (Int) -> Boolean = { cotton -> cotton > RUN_OUT }

        when (cottonType) {
            Cotton.DAILY -> {
                val cottonNum = viewModel.homeResponse.value?.dailyCottonCount ?: RUN_OUT
                if (isCottonRemain(cottonNum)) {
                    viewModel.checkCotton(cottonType)
                    view.playAnimation()
                }
            }

            Cotton.HAPPINESS -> {
                val cottonNum = viewModel.homeResponse.value?.happinessCottonCount ?: RUN_OUT
                if (isCottonRemain(cottonNum)) {
                    viewModel.checkCotton(cottonType)
                    view.playAnimation()
                }
            }
        }
    }

    private fun setObserveHomeResponse() {
        viewModel.conversations.observe(viewLifecycleOwner) {
            setRandomMessage()
        }
    }

    private fun setLottieListener(view: LottieAnimationView) {
        view.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animator: Animator) {
                view.isClickable = false
                binding.clHomeSomWhite.isClickable = false
                binding.clHomeSomColor.isClickable = false
            }

            override fun onAnimationEnd(animator: Animator) {
                view.isClickable = true
                binding.clHomeSomWhite.isClickable = true
                binding.clHomeSomColor.isClickable = true
            }

            override fun onAnimationCancel(animation: Animator) {}

            override fun onAnimationRepeat(animation: Animator) {}
        })
    }

    private fun moveToPaymentView() {
        binding.ivHomeMoney.setOnClickListener {
            startActivity(
                Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.my_page_policy_link)))
            )
        }
    }
}
