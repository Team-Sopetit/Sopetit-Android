package com.sopetit.softie.util

import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.sopetit.softie.R
import com.sopetit.softie.databinding.ActivityRoutineCompleteBinding
import com.sopetit.softie.domain.entity.Cotton
import com.sopetit.softie.util.binding.BindingActivity

abstract class RoutineCompleteActivity(
    private val cotton: Cotton
) : BindingActivity<ActivityRoutineCompleteBinding>(R.layout.activity_routine_complete) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val handler = Handler()

        binding.tvRoutineCompleteTitle.alpha = 0f
        binding.tvRoutineCompleteSubtitle.alpha = 0f

        handler.postDelayed({
            binding.tvRoutineCompleteTitle.visibility = View.VISIBLE
            val fadeInTitle =
                ObjectAnimator.ofFloat(binding.tvRoutineCompleteTitle, "alpha", 0f, 1f)
            fadeInTitle.duration = 500
            fadeInTitle.start()
        }, 500L)

        handler.postDelayed({
            binding.tvRoutineCompleteSubtitle.visibility = View.VISIBLE
            val fadeInSubtitle =
                ObjectAnimator.ofFloat(binding.tvRoutineCompleteSubtitle, "alpha", 0f, 1f)
            fadeInSubtitle.duration = 500
            fadeInSubtitle.start()
        }, 1000L)

        handler.postDelayed({
            finish()
        }, 3000L)

        setTextAndLottieByCottonType()
    }

    private fun setTextAndLottieByCottonType() {
        when (cotton) {
            Cotton.DAILY -> {
                setText(
                    getString(R.string.daily_routine_complete_title),
                    getString(R.string.daily_routine_complete_subtitle)
                )
                setLottie(R.raw.daily_complete_som)
            }

            Cotton.HAPPINESS -> {
                setText(
                    getString(R.string.happy_routine_complete_title),
                    getString(R.string.happy_routine_complete_subtitle)
                )
                setLottie(R.raw.happy_complete_som)
            }
        }
    }

    private fun setText(title: String, subtitle: String) {
        binding.tvRoutineCompleteTitle.text = title
        binding.tvRoutineCompleteSubtitle.text = subtitle
    }

    private fun setLottie(lottieFile: Int) {
        binding.lottieRoutineComplete.setAnimation(lottieFile)
        binding.lottieRoutineComplete.playAnimation()
    }
}
