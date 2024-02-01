package com.sopetit.softie.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kakao.sdk.auth.model.OAuthToken
import com.sopetit.softie.domain.usecase.InitTokenUseCase
import com.sopetit.softie.domain.usecase.PostLoginUseCase
import com.sopetit.softie.util.KakaoLoginCallback
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val postLoginUseCase: PostLoginUseCase,
    private val initTokenUseCase: InitTokenUseCase
) : ViewModel() {
    private val _isKakaoLogin: MutableLiveData<Boolean> = MutableLiveData(false)
    val isKakaoLogin: LiveData<Boolean> get() = _isKakaoLogin

    private val _isSignedUp: MutableLiveData<Boolean> = MutableLiveData(false)
    val isSignedUp: LiveData<Boolean> get() = _isSignedUp
    private val _isMember: MutableLiveData<Boolean> = MutableLiveData(false)
    val isMember: LiveData<Boolean> get() = _isMember

    val kakaoLoginCallback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        KakaoLoginCallback { accessToken ->
            _isKakaoLogin.value = true
            initTokenUseCase(
                accessToken = accessToken,
                refreshToken = "",
                isMemberDollExist = false,
                isSignedUp = false
            )
        }.handleResult(token, error)
    }

    fun postLogin() {
        viewModelScope.launch {
            postLoginUseCase(SOCIAL_TYPE)
                .onSuccess { response ->
                    initTokenUseCase(
                        response.accessToken,
                        response.refreshToken,
                        response.isMemberDollExist,
                        true
                    )
                    _isSignedUp.value = true
                    _isMember.value = response.isMemberDollExist
                }.onFailure { throwable ->
                    Timber.e("$throwable")
                }
        }
    }

    companion object {
        private const val SOCIAL_TYPE = "KAKAO"
    }
}
