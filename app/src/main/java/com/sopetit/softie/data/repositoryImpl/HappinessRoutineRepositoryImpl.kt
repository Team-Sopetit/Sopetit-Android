package com.sopetit.softie.data.repositoryImpl

import com.sopetit.softie.data.source.HappinessRoutineDataSource
import com.sopetit.softie.domain.entity.HappyCard
import com.sopetit.softie.domain.repository.HappinessRoutineRepository
import javax.inject.Inject

class HappinessRoutineRepositoryImpl @Inject constructor(
    private val happinessRoutineDataSource: HappinessRoutineDataSource
) : HappinessRoutineRepository {
    override suspend fun getHappyCard(routineId: String): Result<HappyCard> =
        runCatching {
            happinessRoutineDataSource.getHappyCard(routineId)
        }.map {
            requireNotNull(it.data).toHappyCard()
        }
}
