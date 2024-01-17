package com.sopetit.softie.ui.setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopetit.softie.domain.usecase.InitSIgnUpStateUseCase
import com.sopetit.softie.domain.usecase.InitTokenUseCase
import com.sopetit.softie.domain.usecase.auth.DeleteAuthUseCase
import com.sopetit.softie.ui.setting.SettingActivity.Companion.SETTING_INIT
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val deleteAuthUseCase: DeleteAuthUseCase,
    private val initSIgnUpStateUseCase: InitSIgnUpStateUseCase,
    private val initTokenUseCase: InitTokenUseCase
) : ViewModel() {

    private val _settingFragment: MutableLiveData<String> = MutableLiveData(SETTING_INIT)
    val settingFragment: LiveData<String>
        get() = _settingFragment

    private val _isDeleteAuthResponse: MutableLiveData<Boolean> = MutableLiveData()
    val isDeleteAuthResponse: LiveData<Boolean>
        get() = _isDeleteAuthResponse

    fun setSettingFragment(clickFragment: String) {
        _settingFragment.value = clickFragment
    }

    fun setDeleteAuth() {
        viewModelScope.launch {
            deleteAuthUseCase.invoke()
                .onSuccess {
                    _isDeleteAuthResponse.value = true
                    initSIgnUpStateUseCase(false)
                    initTokenUseCase("", "")
                }.onFailure { throwable ->
                    _isDeleteAuthResponse.value = false
                    Timber.e("서버 통신 실패 -> ${throwable.message}")
                }
        }
    }
}
