package com.sopetit.softie.domain.entity

import androidx.annotation.DrawableRes

data class HappyCard(
    val name: String,
    val nameColor: String,
    val title: String,
    @DrawableRes val iconImageUrl: Int,
    val routines: List<Routines>
) {
    data class Routines(
        val routineId: Int,
        @DrawableRes val cardImageUrl: Int,
        val content: String,
        val detailTitle: String,
        val detailContent: String,
        val detailTime: String,
        val detailPlace: String
    )
}
