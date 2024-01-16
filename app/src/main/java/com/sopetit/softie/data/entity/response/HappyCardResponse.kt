package com.sopetit.softie.data.entity.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HappyCardResponse(
    @SerialName("title")
    val title: String,
    @SerialName("name")
    val name: String,
    @SerialName("nameColor")
    val nameColor: String,
    @SerialName("iconImageUrl")
    val iconImageUrl: String,
    @SerialName("contentImageUrl")
    val contentImageUrl: String,
    @SerialName("subRoutines")
    val subRoutines: List<SubRoutines>
) {
    @Serializable
    data class SubRoutines(
        @SerialName("subRoutineId")
        val subRoutineId: Int,
        @SerialName("content")
        val content: String,
        @SerialName("detailContent")
        val detailContent: String,
        @SerialName("timeTaken")
        val timeTaken: String,
        @SerialName("place")
        val place: String
    )

    fun toHappyCard() = com.sopetit.softie.domain.entity.HappyCard(
        title = title,
        name = name,
        nameColor = nameColor,
        iconImageUrl = iconImageUrl,
        contentImageUrl = contentImageUrl,
        subRoutines = subRoutines.map { subRoutine ->
            com.sopetit.softie.domain.entity.HappyCard.SubRoutines(
                subRoutineId = subRoutine.subRoutineId,
                content = subRoutine.content,
                detailContent = subRoutine.detailContent,
                timeTaken = subRoutine.timeTaken,
                place = subRoutine.place,
                contentImageUrl = contentImageUrl
            )
        }
    )
}
