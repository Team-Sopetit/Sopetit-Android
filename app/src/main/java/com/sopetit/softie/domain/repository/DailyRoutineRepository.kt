package com.sopetit.softie.domain.repository

import com.sopetit.softie.domain.entity.AddRoutine
import com.sopetit.softie.domain.entity.DailyAchieve
import com.sopetit.softie.domain.entity.DailyRoutine
import com.sopetit.softie.domain.entity.Routine
import com.sopetit.softie.domain.entity.Theme

interface DailyRoutineRepository {
    suspend fun getDailyRoutine(): Result<List<DailyRoutine>>
    suspend fun patchAchieveDaily(routineId: Int): Result<DailyAchieve>
    suspend fun deleteDailyRoutine(routineId: Int): Result<Unit>
    suspend fun getTheme(): Result<List<Theme>>

    suspend fun getRoutineList(themeId: List<Int>): Result<List<Routine>>

    suspend fun postAddDailyRoutine(routineId: Int): Result<AddRoutine>
}
