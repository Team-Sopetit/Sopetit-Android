package com.sopetit.softie.domain.repository

import com.sopetit.softie.domain.entity.HappyProgress

interface MemberHappinessRoutineRepository {
    suspend fun getHappyProgress(): Result<HappyProgress>
}
