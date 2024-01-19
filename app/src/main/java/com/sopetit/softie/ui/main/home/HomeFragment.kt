package com.sopetit.softie.ui.main.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.sopetit.softie.R
import com.sopetit.softie.databinding.FragmentHomeBinding
import com.sopetit.softie.domain.entity.Cotton
import com.sopetit.softie.ui.onboarding.OnboardingViewModel.Companion.BROWN
import com.sopetit.softie.ui.onboarding.OnboardingViewModel.Companion.GRAY
import com.sopetit.softie.ui.onboarding.OnboardingViewModel.Companion.RED
import com.sopetit.softie.ui.onboarding.OnboardingViewModel.Companion.WHITE
import com.sopetit.softie.ui.setting.SettingActivity
import com.sopetit.softie.util.binding.BindingFragment
import com.sopetit.softie.util.setSingleOnClickListener
import com.sopetit.softie.util.setStatusBarColor
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random

@AndroidEntryPoint
class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val viewModel by viewModels<HomeViewModel>()
    private val brownBearLottieList =
        listOf(R.raw.brown_hello, R.raw.brown_eating_daily, R.raw.brown_eating_happy)
    private val redBearLottieList =
        listOf(R.raw.red_hello, R.raw.red_eating_daily, R.raw.red_eating_happy)
    private val grayBearLottieList =
        listOf(R.raw.gray_hello, R.raw.gray_eating_daily, R.raw.gray_eating_happy)
    private val pandaBearLottieList =
        listOf(R.raw.panda_hello, R.raw.panda_eating_daily, R.raw.panda_eating_happy)
    private lateinit var userLottieList: List<Int>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        setStatusBarColor(R.color.home_background)

        setUserLottieList()
        viewModel.getHome()
        setClickListener()
        setObserveHomeResponse()
    }

    private fun setUserLottieList() {
        userLottieList = when (viewModel.getBearType()) {
            BROWN -> brownBearLottieList
            GRAY -> grayBearLottieList
            RED -> redBearLottieList
            WHITE -> pandaBearLottieList
            else -> brownBearLottieList
        }
    }

    private fun setRandomMessage() {
        val speechNum = viewModel.homeResponse.value?.conversations?.size ?: 1
        val randomSpeech = Random.nextInt(START, speechNum)
        binding.tvHomeBearSpeech.text =
            viewModel.homeResponse.value?.conversations?.get(randomSpeech)
    }

    private fun setClickListener() {
        setClickSetting()
        setClickBear()
        setClickDaily()
        setClickHappy()
        moveToPaymentView()
    }

    private fun setClickSetting() {
        binding.ivHomeSetting.setSingleOnClickListener {
            val intentToSetting = Intent(activity, SettingActivity::class.java)
            startActivity(intentToSetting)
        }
    }

    private fun setClickBear() {
        binding.lottieHomeBear.setSingleOnClickListener {
            playLottieAnimation(userLottieList[HELLO])
            setRandomMessage()
        }
    }

    private fun setClickDaily() {
        binding.clHomeSomWhite.setSingleOnClickListener {
            checkCottonRemain(Cotton.DAILY)
        }
    }

    private fun setClickHappy() {
        binding.clHomeSomColor.setSingleOnClickListener {
            checkCottonRemain(Cotton.HAPPINESS)
        }
    }

    private fun moveToPaymentView() {
        binding.ivHomeMoney.setOnClickListener {
            startActivity(
                Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.my_page_policy_link)))
            )
        }
    }

    private fun checkCottonRemain(cottonType: Cotton) {
        val isCottonRemain: (Int) -> Boolean = { cotton -> cotton > RUN_OUT }

        when (cottonType) {
            Cotton.DAILY -> {
                val cottonNum = viewModel.homeResponse.value?.dailyCottonCount ?: RUN_OUT
                if (isCottonRemain(cottonNum)) {
                    viewModel.checkCotton(cottonType)
                    playLottieAnimation(userLottieList[DAILY])
                }
            }

            Cotton.HAPPINESS -> {
                val cottonNum = viewModel.homeResponse.value?.happinessCottonCount ?: RUN_OUT
                if (isCottonRemain(cottonNum)) {
                    viewModel.checkCotton(cottonType)
                    playLottieAnimation(userLottieList[HAPPINESS])
                }
            }
        }
    }

    private fun playLottieAnimation(lottieFile: Int) {
        binding.lottieHomeBear.setAnimation(lottieFile)
        binding.lottieHomeBear.playAnimation()
    }

    private fun setObserveHomeResponse() {
        viewModel.conversations.observe(viewLifecycleOwner) {
            initLottie()
        }
    }

    private fun initLottie() {
        binding.lottieHomeBear.setAnimation(userLottieList[HELLO])
        setRandomMessage()
    }

    companion object {
        const val RUN_OUT = 0
        val START = 0
        val HELLO = 0
        val DAILY = 1
        val HAPPINESS = 2
    }
}
