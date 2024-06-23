package com.sopetit.softie.domain.repository

import com.sopetit.softie.domain.entity.MakerCard
import com.sopetit.softie.domain.entity.RoutineTheme

interface AddRoutineRepository {
    suspend fun getMakerCard(): Result<MakerCard>
    suspend fun getRoutineTheme(): Result<RoutineTheme>
}
