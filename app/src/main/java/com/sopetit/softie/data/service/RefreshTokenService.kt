package com.sopetit.softie.data.service

import com.sopetit.softie.data.entity.BaseResponse
import com.sopetit.softie.data.entity.response.RefreshTokenResponse
import retrofit2.http.POST

interface RefreshTokenService {
    @POST("/api/v1/auth/token")
    suspend fun postRefreshToken(): BaseResponse<RefreshTokenResponse>
}
