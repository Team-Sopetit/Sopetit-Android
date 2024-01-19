package com.sopetit.softie.domain.usecase

import com.sopetit.softie.domain.repository.AuthRepository
import javax.inject.Inject

class InitTokenUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(accessToken: String, refreshToken: String, isMemberDollExist: Boolean) =
        authRepository.initToken(accessToken, refreshToken, isMemberDollExist)
}
