package com.sopetit.softie.domain.repository

import com.sopetit.softie.domain.entity.Theme

interface DailyRoutineRepository {

    suspend fun getTheme(): Result<List<Theme>>
}
