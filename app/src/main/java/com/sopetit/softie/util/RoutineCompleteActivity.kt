package com.sopetit.softie.util

import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.sopetit.softie.R
import com.sopetit.softie.databinding.ActivityRoutineCompleteBinding
import com.sopetit.softie.domain.entity.Cotton
import com.sopetit.softie.util.binding.BindingActivity

abstract class RoutineCompleteActivity(
    private val cotton: Cotton
) : BindingActivity<ActivityRoutineCompleteBinding>(R.layout.activity_routine_complete) {
    private lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handler = Handler(Looper.getMainLooper())

        setTextAndLottieByCottonType()
        setTextAlpha()
        performRoutineCompletion()
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

    private fun setTextAlpha() {
        binding.tvRoutineCompleteTitle.alpha = TRANSPARENT
        binding.tvRoutineCompleteSubtitle.alpha = TRANSPARENT
    }

    private fun performRoutineCompletion() {
        fadeInViewWithDelay(binding.tvRoutineCompleteTitle)
        fadeInViewWithDelay(binding.tvRoutineCompleteSubtitle)
        startScreenOffDelay()
    }

    private fun fadeInViewWithDelay(view: View) {
        handler.postDelayed({
            view.visibility = View.VISIBLE
            val fadeInAnimator = ObjectAnimator.ofFloat(view, ALPHA, TRANSPARENT, OPAQUE)
            fadeInAnimator.duration = FADE_IN
            fadeInAnimator.start()
        }, FADE_IN)
    }

    private fun startScreenOffDelay() {
        handler.postDelayed({
            finish()
        }, FADE_OUT)
    }

    companion object {
        const val FADE_IN = 500L
        const val FADE_OUT = 3000L
        const val ALPHA = "alpha"
        const val TRANSPARENT = 0f
        const val OPAQUE = 1f
    }
}
