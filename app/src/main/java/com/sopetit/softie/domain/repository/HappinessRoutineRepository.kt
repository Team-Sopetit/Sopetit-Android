package com.sopetit.softie.domain.repository

import com.sopetit.softie.domain.entity.HappyChip
import com.sopetit.softie.domain.entity.HappyContent

interface HappinessRoutineRepository {
    suspend fun getHappyChip(): Result<List<HappyChip>>
    suspend fun getHappyContent(themeId: Int): Result<List<HappyContent>>
}
