package com.sopetit.softie.ui.onboarding.splash

import androidx.lifecycle.ViewModel
import com.sopetit.softie.domain.usecase.GetMemberUseCase
import com.sopetit.softie.domain.usecase.GetSignedUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getSignedUpUseCase: GetSignedUpUseCase,
    private val getMemberUseCase: GetMemberUseCase
) : ViewModel() {
    fun isSignedUp(): Boolean = getSignedUpUseCase()
    fun isMember(): Boolean = getMemberUseCase()
}
