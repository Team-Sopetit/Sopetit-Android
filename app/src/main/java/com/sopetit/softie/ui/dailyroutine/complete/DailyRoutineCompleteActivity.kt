package com.sopetit.softie.ui.dailyroutine.complete

import android.os.Bundle
import androidx.activity.viewModels
import com.sopetit.softie.R
import com.sopetit.softie.domain.entity.Cotton
import com.sopetit.softie.ui.onboarding.OnboardingViewModel.Companion.BROWN
import com.sopetit.softie.ui.onboarding.OnboardingViewModel.Companion.GRAY
import com.sopetit.softie.ui.onboarding.OnboardingViewModel.Companion.RED
import com.sopetit.softie.ui.onboarding.OnboardingViewModel.Companion.WHITE
import com.sopetit.softie.util.RoutineCompleteActivity
import com.sopetit.softie.util.setStatusBarColorFromResource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DailyRoutineCompleteActivity : RoutineCompleteActivity(Cotton.DAILY) {
    private val viewModel by viewModels<DailyRoutineCompleteViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStatusBarColorFromResource(R.color.white)

        setDailyRoutineImage()
    }

    private fun setDailyRoutineImage() {
        val bearDrawableMap = mapOf(
            BROWN to R.drawable.ic_daily_complete_brown,
            GRAY to R.drawable.ic_daily_complete_gray,
            RED to R.drawable.ic_daily_complete_red,
            WHITE to R.drawable.ic_daily_complete_panda
        )
        val resourceId =
            bearDrawableMap[viewModel.getBearType()] ?: R.drawable.ic_daily_complete_brown

        binding.ivRoutineComplete.setImageResource(resourceId)
    }
}
