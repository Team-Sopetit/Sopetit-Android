package com.sopetit.softie.data.source

import com.sopetit.softie.data.entity.BaseResponse
import com.sopetit.softie.data.entity.request.LoginRequest
import com.sopetit.softie.data.entity.response.LoginResponse
import com.sopetit.softie.data.service.AuthService
import javax.inject.Inject

class AuthDataSource @Inject constructor(
    private val authService: AuthService
) {

    suspend fun deleteAuth(): BaseResponse<Unit> = authService.deleteAuth()

    suspend fun postLogin(socialType: String): BaseResponse<LoginResponse> =
        authService.postLogin(LoginRequest(socialType))

    suspend fun logOut(): BaseResponse<Unit> = authService.logOut()
}
