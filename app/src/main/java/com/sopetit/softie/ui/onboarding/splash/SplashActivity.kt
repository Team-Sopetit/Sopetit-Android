package com.sopetit.softie.ui.onboarding.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.sopetit.softie.R
import com.sopetit.softie.databinding.ActivitySplashBinding
import com.sopetit.softie.domain.entity.UpdateType
import com.sopetit.softie.ui.login.LoginActivity
import com.sopetit.softie.ui.main.MainActivity
import com.sopetit.softie.util.binding.BindingActivity
import com.sopetit.softie.util.setStatusBarColorFromResource
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random

@AndroidEntryPoint
class SplashActivity :
    BindingActivity<ActivitySplashBinding>(R.layout.activity_splash),
    UpdateRecommendDialogInterface,
    UpdateForceDialogInterface {
    private val viewModel by viewModels<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initCreateRandomVersion()
        initUpdateTypeObserver()
    }

    private fun initCreateRandomVersion() {
        when (Random.nextInt(1, 5)) {
            1 -> initSplash(R.drawable.ic_splash1, R.color.main2, binding.ivSplash1Bear)
            2 -> initSplash(R.drawable.ic_splash2, R.color.main2, binding.ivSplash23Bear)
            3 -> initSplash(R.drawable.ic_splash3, R.color.main1, binding.ivSplash23Bear)
            4 -> initSplash(R.drawable.ic_splash4, R.color.main1, binding.ivSplash4Bear)
        }
    }

    private fun initSplash(image: Int, color: Int, view: View) {
        makeSplashImg(image)
        setLogoImg(color)
        setServiceIntroduceImg(color)
        setStatusBarColorFromResource(color)
        setViewVisible(view)
    }

    private fun setViewVisible(view: View) {
        view.visibility = View.VISIBLE
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
    }

    private fun initUpdateTypeObserver() {
        viewModel.updateVersion.observe(this) {
            handleUpdateType()
        }
    }

    private fun handleUpdateType() {
        Handler(Looper.getMainLooper()).postDelayed({
            when (viewModel.isUpdate.value) {
                UpdateType.NONE -> startApp()
                UpdateType.FORCE -> showForceUpdateDialog()
                UpdateType.RECOMMEND -> showRecommendUpdateDialog()
                else -> {}
            }
        }, SPLASH_DELAY)
    }

    private fun showForceUpdateDialog() {
        val forceUpdateDialog = UpdateForceDialogFragment(
            this,
            viewModel.updateVersion.value?.notificationTitle
                ?: getString(R.string.dialog_update_title),
            viewModel.updateVersion.value?.notificationContent
                ?: getString(R.string.dialog_update_content)
        )
        forceUpdateDialog.show(this.supportFragmentManager, forceUpdateDialog.tag)
    }

    private fun showRecommendUpdateDialog() {
        val recommendUpdateDialog = UpdateRecommendDialogFragment(
            this,
            viewModel.updateVersion.value?.notificationTitle
                ?: getString(R.string.dialog_update_title),
            viewModel.updateVersion.value?.notificationContent
                ?: getString(R.string.dialog_update_content)
        )
        recommendUpdateDialog.show(this.supportFragmentManager, recommendUpdateDialog.tag)
    }

    override fun startApp() {
        val intent: Intent = if (viewModel.isSignedUp()) {
            Intent(this, MainActivity::class.java)
        } else {
            Intent(this, LoginActivity::class.java)
        }
        startActivity(intent)
        finish()
    }

    override fun finishApp() {
        finish()
    }

    companion object {
        const val SPLASH_DELAY = 2000L
    }
}
