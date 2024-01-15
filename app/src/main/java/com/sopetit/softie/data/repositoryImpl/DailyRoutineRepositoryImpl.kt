package com.sopetit.softie.data.repositoryImpl

import com.sopetit.softie.data.source.DailyRoutineDataSource
import com.sopetit.softie.domain.entity.Routine
import com.sopetit.softie.domain.entity.Theme
import com.sopetit.softie.domain.repository.DailyRoutineRepository
import javax.inject.Inject

class DailyRoutineRepositoryImpl @Inject constructor(
    private val dailyRoutineDataSource: DailyRoutineDataSource
) : DailyRoutineRepository {

    override suspend fun getTheme(): Result<List<Theme>> = runCatching {
        dailyRoutineDataSource.getTheme()
    }.mapCatching {
        requireNotNull(it.data).toTheme()
    }

    override suspend fun getRoutineList(themeId: Int): Result<List<Routine>> = runCatching {
        dailyRoutineDataSource.getRoutineList(themeId)
    }.mapCatching {
        requireNotNull(it.data).toRoutine()
    }
}
