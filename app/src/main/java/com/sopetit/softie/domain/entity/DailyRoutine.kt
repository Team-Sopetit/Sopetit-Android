package com.sopetit.softie.domain.entity

data class DailyRoutine(
    val routineId: Int,
    val content: String,
    val iconImageUrl: String,
    val achieveCount: Int,
    val isAchieve: Boolean
)
