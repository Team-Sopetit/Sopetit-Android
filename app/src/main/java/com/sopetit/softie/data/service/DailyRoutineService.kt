package com.sopetit.softie.data.service

import com.sopetit.softie.data.entity.BaseResponse
import com.sopetit.softie.data.entity.response.DailyRoutineListResponse
import com.sopetit.softie.data.entity.response.ThemeListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface DailyRoutineService {

    @GET("api/v1/routines/daily/themes")
    suspend fun getTheme(): BaseResponse<ThemeListResponse>

    @GET("api/v1/routines/daily")
    suspend fun getRoutineList(
        @Query("themes") themes: List<Int>
    ): BaseResponse<DailyRoutineListResponse>
import com.sopetit.softie.data.entity.response.AchieveDailyResponse
import com.sopetit.softie.data.entity.response.DailyRoutineResponse
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface DailyRoutineService {
    @GET("api/v1/routines/daily/member")
    suspend fun getDailyRoutine(): BaseResponse<DailyRoutineResponse>

    @PATCH("api/v1/routines/daily/member/routine/{routineId}")
    suspend fun patchAchieveDaily(@Path("routineId") routineId: Int): BaseResponse<AchieveDailyResponse>

    @DELETE("api/v1/routines/daily/member/routine/{routineId}")
    suspend fun deleteDailyRoutine(@Path("routineId") routineId: Int): BaseResponse<Unit>
}
