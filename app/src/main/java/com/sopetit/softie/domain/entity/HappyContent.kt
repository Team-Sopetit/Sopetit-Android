package com.sopetit.softie.domain.entity

import androidx.annotation.DrawableRes

data class HappyContent(
    val routineId: Int,
    val title: String,
    val content: String,
    @DrawableRes val imageUrl: Int,
)

