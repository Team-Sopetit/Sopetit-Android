package com.sopetit.softie.data.service

import com.sopetit.softie.data.entity.BaseResponse
import com.sopetit.softie.data.entity.response.VersionResponse
import retrofit2.http.GET

interface VersionService {
    @GET("api/v1/versions/client/app")
    suspend fun getUpdateVersion(): BaseResponse<VersionResponse>
}
