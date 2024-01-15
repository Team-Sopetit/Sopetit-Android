package com.sopetit.softie.data.service

import com.sopetit.softie.data.entity.BaseResponse
import com.sopetit.softie.data.entity.response.ThemeListResponse
import retrofit2.http.GET

interface DailyRoutineService {

    @GET("api/v1/routines/daily/themes")
    suspend fun getTheme(): BaseResponse<ThemeListResponse>
}
