package com.sopetit.softie.ui.onboarding.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopetit.softie.BuildConfig
import com.sopetit.softie.domain.entity.UpdateType
import com.sopetit.softie.domain.usecase.GetSignedUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.math.pow

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getSignedUpUseCase: GetSignedUpUseCase
) : ViewModel() {
    val versionName: String = BuildConfig.VERSION_NAME
    private val _isUpdate: MutableLiveData<UpdateType> = MutableLiveData()
    val isUpdate: LiveData<UpdateType> get() = _isUpdate
    fun isSignedUp(): Boolean = getSignedUpUseCase()

    init {
        isForceUpdate()
    }

    private fun isForceUpdate() {
        val storeAppVersion = "1.0.2"
        val forceAppVersion = "1.0.1"
        val storeAppVersionInt = versionStringToNumber(storeAppVersion)
        val forceAppVersionInt = versionStringToNumber(forceAppVersion)
        val versionNameInt = versionStringToNumber(versionName)

        if (storeAppVersionInt > forceAppVersionInt) {
            if (versionNameInt < forceAppVersionInt) _isUpdate.value = UpdateType.FORCE
            else _isUpdate.value = UpdateType.RECOMMEND
        } else {
            _isUpdate.value = UpdateType.NONE
        }
    }

    private fun versionStringToNumber(version: String): Int {
        val versionNumbers = version.split(".").map { it.toInt() }
        var result = 0

        for (i in versionNumbers.indices) {
            result += versionNumbers[i] * BASE_POWER.pow((versionNumbers.size - i - 1) * EXPONENT)
                .toInt()
        }

        return result
    }

    companion object {
        private const val BASE_POWER = 10.0
        private const val EXPONENT = 2
    }
}
