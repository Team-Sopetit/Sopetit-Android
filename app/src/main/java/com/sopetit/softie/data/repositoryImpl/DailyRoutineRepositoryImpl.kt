package com.sopetit.softie.data.repositoryImpl

import com.sopetit.softie.data.entity.request.AddDailyRoutineRequest
import com.sopetit.softie.data.source.DailyRoutineDataSource
import com.sopetit.softie.domain.entity.AddRoutine
import com.sopetit.softie.domain.entity.DailyAchieve
import com.sopetit.softie.domain.entity.DailyCard
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

    override suspend fun deleteDailyRoutine(routineIdList: List<Int>): Result<Unit> =
        runCatching { dailyRoutineDataSource.deleteDailyRoutine(routineIdList) }

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

    override suspend fun postAddDailyRoutine(request: AddDailyRoutineRequest): Result<AddRoutine> =
        runCatching {
            dailyRoutineDataSource.postAddDailyRoutine(request)
        }.map { response ->
            requireNotNull(response.data).toAddDailyRoutine()
        }

    override suspend fun getRoutineDailyThemeList(themeId: Int): Result<DailyCard> =
        runCatching {
            dailyRoutineDataSource.getRoutineDailyThemeList(themeId)
        }.map { response ->
            requireNotNull(response.data).toDailyCard()
        }
}
