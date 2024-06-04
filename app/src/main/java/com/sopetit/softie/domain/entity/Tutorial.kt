package com.sopetit.softie.domain.entity

import androidx.annotation.DrawableRes

data class Tutorial(
    val tutorialId: Int,
    @DrawableRes val content: Int
)
