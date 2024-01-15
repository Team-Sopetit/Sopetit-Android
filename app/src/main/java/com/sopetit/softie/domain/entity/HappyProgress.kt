package com.sopetit.softie.domain.entity

import androidx.annotation.DrawableRes

data class HappyProgress(
    val routineId: Int,
    @DrawableRes val imageUrl: Int,
    val title: String,
    val content: String,
    val detailTitle: String,
    val detailContent: String,
    val detailTime: String,
    val detailPlace: String
)
