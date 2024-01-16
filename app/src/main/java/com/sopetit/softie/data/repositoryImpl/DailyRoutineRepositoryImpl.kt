package com.sopetit.softie.data.repositoryImpl

import com.sopetit.softie.data.source.DailyRoutineDataSource
import com.sopetit.softie.domain.entity.DailyAchieve
import com.sopetit.softie.domain.entity.DailyRoutine
import com.sopetit.softie.domain.repository.DailyRoutineRepository
import javax.inject.Inject

class DailyRoutineRepositoryImpl @Inject constructor(
    private val dailyRoutineDataSource: DailyRoutineDataSource
) : DailyRoutineRepository {
    override suspend fun getDailyRoutine(): Result<List<DailyRoutine>> =
        runCatching { dailyRoutineDataSource.getDailyRoutine() }.mapCatching { response ->
            requireNotNull(response.data).toUserDailyRoutine()
        }

    override suspend fun patchAchieveDaily(routineId: Int): Result<DailyAchieve> =
        runCatching { dailyRoutineDataSource.patchAchieveDaily(routineId) }.map { response ->
            requireNotNull(response.data).toAchieveDaily()
        }
}
