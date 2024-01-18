package com.sopetit.softie.domain.entity

data class HappyProgress(
    val routineId: Int,
    val iconImageUrl: String,
    val contentImageUrl: String,
    val themeName: String,
    val themeNameColor: String,
    val title: String,
    val content: String,
    val detailContent: String,
    val place: String,
    val timeTaken: String
)
