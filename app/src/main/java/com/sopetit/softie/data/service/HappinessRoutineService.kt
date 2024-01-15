package com.sopetit.softie.data.service

import com.sopetit.softie.data.entity.BaseResponse
import com.sopetit.softie.data.entity.response.HappyChipResponse
import retrofit2.http.GET

interface HappinessRoutineService {
    @GET("api/v1/routines/happiness/themes")
    suspend fun getHappyChip(): BaseResponse<HappyChipResponse>
}
