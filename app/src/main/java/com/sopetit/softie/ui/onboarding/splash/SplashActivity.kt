package com.sopetit.softie.ui.onboarding.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.sopetit.softie.R
import com.sopetit.softie.databinding.ActivitySplashBinding
import com.sopetit.softie.ui.storytelling.StoryTellingActivity
import com.sopetit.softie.util.binding.BindingActivity
import kotlin.random.Random

class SplashActivity : BindingActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initCreateRandomVersion()
    }

    private fun initCreateRandomVersion() {
        when (Random.nextInt(1, 5)) {
            1 -> makeSplashImg(R.drawable.ic_splash1)
            2 -> makeSplashImg(R.drawable.ic_splash2)
            3 -> makeSplashImg(R.drawable.ic_splash3)
            4 -> makeSplashImg(R.drawable.ic_splash4)
        }
    }

    private fun makeSplashImg(image: Int) {
        binding.ivSplashBackground.setBackgroundResource(image)
        initMakeSplash()
    }

    private fun initMakeSplash() {
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, StoryTellingActivity::class.java)
            startActivity(intent)
            finish()
        }, SPLASH_DELAY)
    }

    companion object {
        const val SPLASH_DELAY = 2000L
    }
}
