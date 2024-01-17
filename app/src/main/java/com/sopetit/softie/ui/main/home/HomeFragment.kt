package com.sopetit.softie.ui.main.home

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
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
    private lateinit var sharedPreferences: SharedPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        setStatusBarColor(R.color.home_background)

        setUserLottieList()
        initLottie()
        setClickListener()
        setObserveHomeResponse()
        sharedPreferences =
            this.requireActivity().getSharedPreferences("user", Context.MODE_PRIVATE)
        sharedPreferences.edit()
            .putString("bearType", viewModel.homeResponse.value?.dollType ?: BROWN).commit()
    }

    private fun setUserLottieList() {
        viewModel.getHome()
        userLottieList = when (viewModel.homeResponse.value?.dollType) {
            BROWN -> brownBearLottieList
            GRAY -> grayBearLottieList
            RED -> redBearLottieList
            WHITE -> pandaBearLottieList
            else -> brownBearLottieList
        }
    }

    private fun initLottie() {
        binding.lottieHomeBear.setAnimation(userLottieList[HELLO])
        setRandomMessage()
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
    }

    private fun setClickSetting() {
        binding.ivHomeSetting.setOnClickListener {
            val intentToSetting = Intent(activity, SettingActivity::class.java)
            startActivity(intentToSetting)
        }
    }

    private fun setClickBear() {
        binding.lottieHomeBear.setOnClickListener {
            playLottieAnimation(userLottieList[HELLO])
            setRandomMessage()
        }
    }

    private fun setClickDaily() {
        binding.clHomeSomWhite.setOnClickListener {
            checkCottonRemain(Cotton.DAILY)
        }
    }

    private fun setClickHappy() {
        binding.clHomeSomColor.setOnClickListener {
            checkCottonRemain(Cotton.HAPPINESS)
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

    companion object {
        const val RUN_OUT = 0
        val START = 0
        val HELLO = 0
        val DAILY = 1
        val HAPPINESS = 2
    }
}
