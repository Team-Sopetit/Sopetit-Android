package com.sopetit.softie.domain.repository

import com.sopetit.softie.domain.entity.HappyCard

interface HappinessRoutineRepository {
    suspend fun getHappyCard(routineId: String): Result<HappyCard>
}
