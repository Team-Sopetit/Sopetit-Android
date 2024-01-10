package com.sopetit.softie.ui.main.happy.addlist

import androidx.annotation.DrawableRes

data class HappyAddListContent(
    val routineId: Int,
    val title: String,
    val content: String,
    @DrawableRes val imageUrl: Int,
)

