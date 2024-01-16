package com.sopetit.softie.data.repositoryImpl

import com.sopetit.softie.data.source.DailyRoutineDataSource
import com.sopetit.softie.domain.entity.DailyAchieve
import com.sopetit.softie.domain.entity.DailyRoutine
import com.sopetit.softie.domain.entity.Routine
import com.sopetit.softie.domain.entity.Theme
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

    override suspend fun deleteDailyRoutine(routineId: Int): Result<Unit> =
        runCatching { dailyRoutineDataSource.deleteDailyRoutine(routineId) }

    override suspend fun getTheme(): Result<List<Theme>> = runCatching {
        dailyRoutineDataSource.getTheme()
    }.mapCatching {
        requireNotNull(it.data).toTheme()
    }

    override suspend fun getRoutineList(themeId: List<Int>): Result<List<Routine>> = runCatching {
        dailyRoutineDataSource.getRoutineList(themeId)
    }.mapCatching { dailyRoutineList ->
        requireNotNull(dailyRoutineList.data).toRoutine()
    }
}
