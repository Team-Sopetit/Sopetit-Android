package com.sopetit.softie.ui.onboarding.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopetit.softie.BuildConfig
import com.sopetit.softie.domain.entity.UpdateType
import com.sopetit.softie.domain.entity.UpdateVersion
import com.sopetit.softie.domain.usecase.local.GetMemberUseCase
import com.sopetit.softie.domain.usecase.local.GetSignedUpUseCase
import com.sopetit.softie.domain.usecase.version.GetUpdateVersionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import kotlin.math.pow

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getSignedUpUseCase: GetSignedUpUseCase,
    private val getMemberUseCase: GetMemberUseCase,
    private val getUpdateVersionUseCase: GetUpdateVersionUseCase
) : ViewModel() {
    private val versionName: String = BuildConfig.VERSION_NAME
    private val _isUpdate: MutableLiveData<UpdateType> = MutableLiveData()
    val isUpdate: LiveData<UpdateType> get() = _isUpdate
    private val _updateVersion: MutableLiveData<UpdateVersion> = MutableLiveData()
    val updateVersion: LiveData<UpdateVersion> get() = _updateVersion
    fun isSignedUp(): Boolean = getSignedUpUseCase()
    fun isMember(): Boolean = getMemberUseCase()

    init {
        getUpdateVersion()
    }

    private fun getUpdateVersion() {
        viewModelScope.launch {
            getUpdateVersionUseCase()
                .onSuccess { response ->
                    _updateVersion.value = response
                    isForceUpdate()
                }
                .onFailure { throwable ->
                    Timber.e("$throwable")
                }
        }
    }

    private fun isForceUpdate() {
        val storeAppVersionNum =
            versionStringToNumber(_updateVersion.value?.storeAppVersion ?: MAXIMUM_STORE_VERSION)
        val forceAppVersionNum =
            versionStringToNumber(_updateVersion.value?.forceAppVersion ?: MAXIMUM_FORCE_VERSION)
        val versionNameNum = versionStringToNumber(versionName)

        if (storeAppVersionNum > forceAppVersionNum) {
            if (versionNameNum < forceAppVersionNum) _isUpdate.value = UpdateType.FORCE
            else if (versionNameNum == storeAppVersionNum) _isUpdate.value = UpdateType.NONE
            else _isUpdate.value = UpdateType.RECOMMEND
        } else {
            _isUpdate.value = UpdateType.NONE
        }
    }

    private fun versionStringToNumber(version: String): Int {
        val versionNumbers = version.split(".").map { it.toInt() }
        var result = 0

        for (i in versionNumbers.indices) {
            result += versionNumbers[i] * BASE_POWER.pow((versionNumbers.size - i - 1))
                .toInt()
        }

        return result
    }

    companion object {
        private const val BASE_POWER = 10.0
        private const val MAXIMUM_STORE_VERSION = "999.9.9"
        private const val MAXIMUM_FORCE_VERSION = "999.0.0"
    }
}
