package com.sopetit.softie.data.source

import com.sopetit.softie.data.entity.BaseResponse
import com.sopetit.softie.data.entity.response.RefreshTokenResponse
import com.sopetit.softie.data.service.RefreshTokenService
import javax.inject.Inject

class RefreshTokenDataSource @Inject constructor(
    private val refreshTokenService: RefreshTokenService
) {
    suspend fun postRefreshToken(): BaseResponse<RefreshTokenResponse> =
        refreshTokenService.postRefreshToken()
}
