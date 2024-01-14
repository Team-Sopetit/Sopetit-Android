package com.sopetit.softie.domain.entity

data class DailyRoutineAdd(
    val themeId: Int,
    val dailyRoutineCardList: List<DailyCard>
)
