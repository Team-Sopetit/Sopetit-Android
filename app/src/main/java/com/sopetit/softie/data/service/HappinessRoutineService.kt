package com.sopetit.softie.data.service

import com.sopetit.softie.data.entity.BaseResponse
import com.sopetit.softie.data.entity.response.HappyCardResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface HappinessRoutineService {
    @GET("api/v1/routines/happiness/routine/{routineId}")
    suspend fun getHappyCard(
        @Path("routineId") routineId: String
    ): BaseResponse<HappyCardResponse>
}
