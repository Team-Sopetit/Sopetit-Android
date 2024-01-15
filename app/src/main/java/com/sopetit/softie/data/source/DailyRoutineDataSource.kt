package com.sopetit.softie.data.source

import com.sopetit.softie.data.entity.BaseResponse
import com.sopetit.softie.data.entity.response.ThemeListResponse
import com.sopetit.softie.data.service.DailyRoutineService
import javax.inject.Inject

class DailyRoutineDataSource @Inject constructor(
    private val dailyRoutineService: DailyRoutineService
) {
    suspend fun getTheme(): BaseResponse<ThemeListResponse> = dailyRoutineService.getTheme()
}
