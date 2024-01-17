package com.sopetit.softie.ui.onboarding.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.sopetit.softie.R
import com.sopetit.softie.databinding.ActivitySplashBinding
import com.sopetit.softie.ui.login.LoginActivity
import com.sopetit.softie.ui.main.MainActivity
import com.sopetit.softie.util.binding.BindingActivity
import com.sopetit.softie.util.setStatusBarColorFromResource
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random

@AndroidEntryPoint
class SplashActivity : BindingActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    private val viewModel by viewModels<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initCreateRandomVersion()
    }

    private fun initCreateRandomVersion() {
        when (Random.nextInt(1, 5)) {
            1 -> initSplash(R.drawable.ic_splash_backround1, R.color.main2)
            2 -> initSplash(R.drawable.ic_splash_backround2, R.color.main2)
            3 -> initSplash(R.drawable.ic_splash_backround3, R.color.main1)
            4 -> initSplash(R.drawable.ic_splash_backround4, R.color.main1)
        }
    }

    private fun initSplash(image: Int, color: Int) {
        makeSplashImg(image)
        setLogoImg(color)
        setServiceIntroduceImg(color)
        setStatusBarColorFromResource(color)
    }

    private fun setLogoImg(color: Int) {
        if (color == R.color.main1) binding.ivSplashLogo.setImageResource(R.drawable.ic_logo_main2)
        else binding.ivSplashLogo.setImageResource(R.drawable.ic_logo_main1)
    }

    private fun setServiceIntroduceImg(color: Int) {
        if (color == R.color.main1) binding.tvSplashSubtitle.setTextColor(
            ContextCompat.getColor(this, R.color.main2)
        )
        else binding.tvSplashSubtitle.setTextColor(ContextCompat.getColor(this, R.color.main1))
    }

    private fun makeSplashImg(image: Int) {
        binding.ivSplashBackground.setBackgroundResource(image)
        initMakeSplash()
    }

    private fun initMakeSplash() {
        lateinit var intent: Intent
        Handler(Looper.getMainLooper()).postDelayed({
            intent = if (viewModel.isSignedUp()) {
                Intent(this, MainActivity::class.java)
            } else {
                Intent(this, LoginActivity::class.java)
            }
//            intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, SPLASH_DELAY)
    }

    companion object {
        const val SPLASH_DELAY = 2000L
    }
}
