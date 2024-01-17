package com.sopetit.softie.data.service

import com.sopetit.softie.data.entity.BaseResponse
import com.sopetit.softie.data.entity.request.LoginRequest
import com.sopetit.softie.data.entity.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST

interface AuthService {
    @POST("api/v1/auth")
    suspend fun postLogin(
        @Body body: LoginRequest
    ): BaseResponse<LoginResponse>

    @DELETE("/api/v1/auth")
    suspend fun deleteAuth(): BaseResponse<Unit>
}
