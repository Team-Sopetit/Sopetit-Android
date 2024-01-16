package com.sopetit.softie.data.service

import com.sopetit.softie.data.entity.BaseResponse
import com.sopetit.softie.data.entity.response.AchieveDailyResponse
import com.sopetit.softie.data.entity.response.DailyRoutineResponse
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface DailyRoutineService {
    @GET("api/v1/routines/daily/member")
    suspend fun getDailyRoutine(): BaseResponse<DailyRoutineResponse>

    @PATCH("api/v1/routines/daily/member/routine/{routineId}")
    suspend fun patchAchieveDaily(@Path("routineId") routineId: Int): BaseResponse<AchieveDailyResponse>
}
