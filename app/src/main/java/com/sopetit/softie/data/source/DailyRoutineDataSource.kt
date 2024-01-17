package com.sopetit.softie.data.source

import com.sopetit.softie.data.entity.BaseResponse
import com.sopetit.softie.data.entity.response.AchieveDailyResponse
import com.sopetit.softie.data.entity.response.AddDailyRoutineResponse
import com.sopetit.softie.data.entity.response.DailyRoutineListResponse
import com.sopetit.softie.data.entity.response.DailyRoutineResponse
import com.sopetit.softie.data.entity.response.ThemeListResponse
import com.sopetit.softie.data.service.DailyRoutineService
import javax.inject.Inject

class DailyRoutineDataSource @Inject constructor(
    private val dailyRoutineService: DailyRoutineService
) {
    suspend fun getDailyRoutine(): BaseResponse<DailyRoutineResponse> =
        dailyRoutineService.getDailyRoutine()

    suspend fun patchAchieveDaily(routineId: Int): BaseResponse<AchieveDailyResponse> =
        dailyRoutineService.patchAchieveDaily(routineId)

    suspend fun deleteDailyRoutine(routineIdList: List<Int>): BaseResponse<Unit> =
        dailyRoutineService.deleteDailyRoutine(routineIdList)

    suspend fun getTheme(): BaseResponse<ThemeListResponse> = dailyRoutineService.getTheme()

    suspend fun getRoutineList(themeId: List<Int>): BaseResponse<DailyRoutineListResponse> =
        dailyRoutineService.getRoutineList(themeId)

    suspend fun postAddDailyRoutine(routineId: Int): BaseResponse<AddDailyRoutineResponse> =
        dailyRoutineService.postAddDailyRoutine(routineId)
}
