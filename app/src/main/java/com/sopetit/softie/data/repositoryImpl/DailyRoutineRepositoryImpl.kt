package com.sopetit.softie.data.repositoryImpl

import com.sopetit.softie.data.source.DailyRoutineDataSource
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
}
