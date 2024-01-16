package com.sopetit.softie.domain.entity

data class HappyCard(
    val title: String,
    val name: String,
    val nameColor: String,
    val iconImageUrl: String,
    val contentImageUrl: String,
    val subRoutines: List<SubRoutines>
) {
    data class SubRoutines(
        val subRoutineId: Int,
        val content: String,
        val detailContent: String,
        val timeTaken: String,
        val place: String,
        val contentImageUrl: String
    )
}
