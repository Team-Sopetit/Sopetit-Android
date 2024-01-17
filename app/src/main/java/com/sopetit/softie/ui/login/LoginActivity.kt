package com.sopetit.softie.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.sopetit.softie.R
import com.sopetit.softie.data.service.KakaoLoginService
import com.sopetit.softie.databinding.ActivityLoginBinding
import com.sopetit.softie.ui.onboarding.OnboardingActivity
import com.sopetit.softie.util.binding.BindingActivity
import com.sopetit.softie.util.setStatusBarColorFromResource
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : BindingActivity<ActivityLoginBinding>(R.layout.activity_login) {
    @Inject
    lateinit var kakaoLoginService: KakaoLoginService
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStatusBarColorFromResource(R.color.background)

        initKakaoLoginBtnClickListener()
        observeKakaoLogin()
        observeSignUp()
    }

    private fun initKakaoLoginBtnClickListener() {
        binding.btnLoginKakao.setOnClickListener {
            startKakaoLogin()
        }
    }

    private fun startKakaoLogin() {
        kakaoLoginService.startKakaoLogin(viewModel.kakaoLoginCallback)
    }

    private fun observeKakaoLogin() {
        viewModel.isKakaoLogin.observe(this) { isKakaoLogin ->
            if (isKakaoLogin) {
                viewModel.postLogin()
            } else {
                Timber.e("카카오 로그인 실패")
            }
        }
    }

    private fun observeSignUp() {
        viewModel.isSignedUp.observe(this) { isSignedUp ->
            if (isSignedUp) {
                startActivity(
                    Intent(this, OnboardingActivity::class.java).apply {
                        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    }
                )
                finish()
            }
        }
    }
}
