package com.sopetit.softie.domain.usecase.local

import com.sopetit.softie.domain.repository.AuthRepository
import javax.inject.Inject

class InitTokenUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(
        accessToken: String,
        refreshToken: String,
        isMemberDollExist: Boolean,
        isSignedUp: Boolean
    ) =
        authRepository.initToken(accessToken, refreshToken, isMemberDollExist, isSignedUp)
}
