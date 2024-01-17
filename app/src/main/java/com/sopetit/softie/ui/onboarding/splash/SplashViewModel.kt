package com.sopetit.softie.ui.onboarding.splash // ktlint-disable filename

import androidx.lifecycle.ViewModel
import com.sopetit.softie.domain.usecase.GetSignedUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getSignedUpUseCase: GetSignedUpUseCase
) : ViewModel() {
    fun isSignedUp(): Boolean = getSignedUpUseCase()
}
