package com.sopetit.softie.domain.usecase.auth

import com.sopetit.softie.domain.repository.RefreshTokenRepository
import javax.inject.Inject

class PostRefreshToken @Inject constructor(
    private val refreshTokenRepository: RefreshTokenRepository
) {
    suspend operator fun invoke() = refreshTokenRepository.postRefreshToken()
}
