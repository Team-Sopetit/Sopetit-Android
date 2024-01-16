package com.sopetit.softie.data.service

import com.sopetit.softie.data.entity.BaseResponse
import com.sopetit.softie.data.entity.response.HappyCardResponse
import com.sopetit.softie.data.entity.response.HappyChipResponse
import com.sopetit.softie.data.entity.response.HappyContentResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface HappinessRoutineService {
    @GET("api/v1/routines/happiness/routine/{routineId}")
    suspend fun getHappyCard(
        @Path("routineId") routineId: String
    ): BaseResponse<HappyCardResponse>

    @GET("api/v1/routines/happiness/themes")
    suspend fun getHappyChip(): BaseResponse<HappyChipResponse>

    @GET("api/v1/routines/happiness")
    suspend fun getHappyContent(
        @Query("themeId") themeId: Int
    ): BaseResponse<HappyContentResponse>
}
