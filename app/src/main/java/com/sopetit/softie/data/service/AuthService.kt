package com.sopetit.softie.data.service

import com.sopetit.softie.data.entity.BaseResponse
import retrofit2.http.DELETE

interface AuthService {

    @DELETE("/api/v1/auth")
    suspend fun deleteAuth(): BaseResponse<Unit>
}
