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
        @Query("themes") themes: Int
    ): BaseResponse<DailyRoutineListResponse>
}
