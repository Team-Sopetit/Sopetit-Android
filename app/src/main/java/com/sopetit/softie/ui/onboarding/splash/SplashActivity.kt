package com.sopetit.softie.ui.onboarding.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.sopetit.softie.R
import com.sopetit.softie.databinding.ActivitySplashBinding
import com.sopetit.softie.ui.storytelling.StoryTellingActivity
import com.sopetit.softie.util.binding.BindingActivity

class SplashActivity : BindingActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initMakeFragment()
    }

    private fun initMakeFragment() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fcv_splash)
        if (currentFragment == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fcv_splash, SplashVersionFirstFragment())
                .commit()
        }
        initMakeSplash()
    }

    private fun initMakeSplash() {
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, StoryTellingActivity::class.java)
            startActivity(intent)
            finish()
        }, 1000)
    }
}
