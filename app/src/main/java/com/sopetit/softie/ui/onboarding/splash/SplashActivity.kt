package com.sopetit.softie.ui.onboarding.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import com.sopetit.softie.R
import com.sopetit.softie.databinding.ActivitySplashBinding
import com.sopetit.softie.ui.storytelling.StoryTellingActivity
import com.sopetit.softie.util.binding.BindingActivity

class SplashActivity : BindingActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initCreateRandomVersion()
    }

    private fun initCreateRandomVersion() {
        val versionList: List<Int> = listOf(1, 2, 3, 4)

        when (versionList.random()) {
            1 -> initMakeFragment(SplashVersionFirstFragment())
            2 -> initMakeFragment(SplashVersionSecondFragment())
            3 -> initMakeFragment(SplashVersionThirdFragment())
            4 -> initMakeFragment(SplashVersionFourthFragment())
        }
    }

    private fun initMakeFragment(fragmentVersion: Fragment) {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fcv_splash)
        if (currentFragment == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fcv_splash, fragmentVersion)
                .commit()
        }

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
