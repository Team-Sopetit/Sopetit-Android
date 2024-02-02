package com.sopetit.softie.domain.repository

import com.sopetit.softie.domain.entity.AccessToken

interface RefreshTokenRepository {
    suspend fun postRefreshToken(): Result<AccessToken>
    fun setAccessToken(accessToken: String)
}
