package com.sopetit.softie.ui.setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopetit.softie.domain.usecase.auth.DeleteAuthUseCase
import com.sopetit.softie.domain.usecase.auth.LogOutUseCase
import com.sopetit.softie.domain.usecase.local.GetBearTypeUseCase
import com.sopetit.softie.domain.usecase.local.InitIsSignUpStateUseCase
import com.sopetit.softie.domain.usecase.local.InitTokenUseCase
import com.sopetit.softie.ui.onboarding.OnboardingViewModel.Companion.BROWN
import com.sopetit.softie.ui.onboarding.OnboardingViewModel.Companion.GRAY
import com.sopetit.softie.ui.onboarding.OnboardingViewModel.Companion.RED
import com.sopetit.softie.ui.onboarding.OnboardingViewModel.Companion.WHITE
import com.sopetit.softie.ui.setting.SettingActivity.Companion.SETTING_INIT
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val deleteAuthUseCase: DeleteAuthUseCase,
    private val logOutUseCase: LogOutUseCase,
    private val initIsSignUpStateUseCase: InitIsSignUpStateUseCase,
    private val initTokenUseCase: InitTokenUseCase,
    private val getBearTypeUseCase: GetBearTypeUseCase
) : ViewModel() {

    private val _settingFragment: MutableLiveData<String> = MutableLiveData(SETTING_INIT)
    val settingFragment: LiveData<String>
        get() = _settingFragment

    private val _isDeleteAuthResponse: MutableLiveData<Boolean> = MutableLiveData()
    val isDeleteAuthResponse: LiveData<Boolean>
        get() = _isDeleteAuthResponse

    private val _isLogOutResponse: MutableLiveData<Boolean> = MutableLiveData()
    val isLogOutResponse: LiveData<Boolean>
        get() = _isLogOutResponse

    private val _bearType: MutableLiveData<String> = MutableLiveData()
    val bearType: LiveData<String>
        get() = _bearType

    fun setSettingFragment(clickFragment: String) {
        _settingFragment.value = clickFragment
    }

    fun setCryingBearType() {
        when (getBearTypeUseCase()) {
            BROWN -> _bearType.value = BROWN
            GRAY -> _bearType.value = GRAY
            RED -> _bearType.value = RED
            WHITE -> _bearType.value = WHITE
            else -> _bearType.value = BROWN
        }
    }

    fun setDeleteAuth() {
        viewModelScope.launch {
            deleteAuthUseCase()
                .onSuccess {
                    _isDeleteAuthResponse.value = true
                    initIsSignUpStateUseCase(false)
                    initTokenUseCase("", "", isMemberDollExist = false, isSignedUp = false)
                    Timber.d("회원 탈퇴 성공")
                }.onFailure { throwable ->
                    _isDeleteAuthResponse.value = false
                    Timber.e("서버 통신 실패 -> ${throwable.message}")
                }
        }
    }

    fun setLogOut() {
        viewModelScope.launch {
            logOutUseCase()
                .onSuccess {
                    _isLogOutResponse.value = true
                    initIsSignUpStateUseCase(false)
                    initTokenUseCase("", "", isMemberDollExist = true, isSignedUp = false)
                    Timber.d("로그 아웃 성공")
                }.onFailure { throwable ->
                    _isLogOutResponse.value = false
                    Timber.e("서버 통신 실패 -> ${throwable.message}")
                }
        }
    }
}
