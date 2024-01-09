package com.sopetit.softie.domain.entity

import androidx.annotation.DrawableRes

data class DailyCard(
    @DrawableRes val card: Int,
    val routine: String
)
