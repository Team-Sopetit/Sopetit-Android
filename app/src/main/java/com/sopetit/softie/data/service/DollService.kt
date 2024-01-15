package com.sopetit.softie.data.service

import com.sopetit.softie.data.entity.BaseResponse
import com.sopetit.softie.data.entity.response.DollResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface DollService {

    @GET("api/v1/dolls/image/{type}")
    suspend fun getDollType(
        @Path("type") type: String
    ): BaseResponse<DollResponse>
}
