package com.sopetit.softie.data.service

import com.sopetit.softie.data.entity.BaseResponse
import com.sopetit.softie.data.entity.response.DailyRoutineResponse
import retrofit2.http.GET

interface DailyRoutineService {
    @GET("api/v1/routines/daily/member")
    suspend fun getDailyRoutine(): BaseResponse<DailyRoutineResponse>
}
