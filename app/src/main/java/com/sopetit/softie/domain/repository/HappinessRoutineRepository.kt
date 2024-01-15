package com.sopetit.softie.domain.repository

import com.sopetit.softie.domain.entity.HappyChip

interface HappinessRoutineRepository {
    suspend fun getHappyChip(): Result<List<HappyChip>>
}
