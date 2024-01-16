package com.sopetit.softie.domain.repository

import com.sopetit.softie.domain.entity.DailyAchieve
import com.sopetit.softie.domain.entity.DailyRoutine

interface DailyRoutineRepository {
    suspend fun getDailyRoutine(): Result<List<DailyRoutine>>
    suspend fun patchAchieveDaily(routineId: Int): Result<DailyAchieve>
}
