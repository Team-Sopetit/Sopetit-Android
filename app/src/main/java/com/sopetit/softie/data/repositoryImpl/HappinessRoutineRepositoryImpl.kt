package com.sopetit.softie.data.repositoryImpl

import com.sopetit.softie.data.source.HappinessRoutineDataSource
import com.sopetit.softie.domain.entity.HappyCard
import com.sopetit.softie.domain.entity.HappyChip
import com.sopetit.softie.domain.entity.HappyContent
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

    override suspend fun getHappyChip(): Result<List<HappyChip>> =
        runCatching { happinessRoutineDataSource.getHappyChip() }.map { response ->
            requireNotNull(response.data).toHappyChip()
        }

    override suspend fun getHappyContent(themeId: Int): Result<List<HappyContent>> =
        runCatching { happinessRoutineDataSource.getHappyContent(themeId) }.map { response ->
            requireNotNull(response.data).toHappyContent()
        }
}
