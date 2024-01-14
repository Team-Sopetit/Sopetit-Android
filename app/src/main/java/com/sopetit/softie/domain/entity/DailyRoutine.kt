package com.sopetit.softie.domain.entity

data class DailyRoutine(
    val themeId: Int,
    val dailyRoutineCardList: List<DailyCard>
)
