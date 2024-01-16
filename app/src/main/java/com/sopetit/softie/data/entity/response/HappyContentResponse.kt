package com.sopetit.softie.data.entity.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HappyContentResponse(
    @SerialName("routines")
    val routines: List<HappyContent>
) {
    @Serializable
    data class HappyContent(
        @SerialName("routineId")
        val routineId: Int,
        @SerialName("name")
        val name: String,
        @SerialName("nameColor")
        val nameColor: String,
        @SerialName("title")
        val title: String,
        @SerialName("iconImageUrl")
        val iconImageUrl: String
    )

    fun toHappyContent() = routines.map { happycontent ->
        com.sopetit.softie.domain.entity.HappyContent(
            routineId = happycontent.routineId,
            name = happycontent.name,
            nameColor = happycontent.nameColor,
            title = happycontent.title,
            iconImageUrl = happycontent.iconImageUrl
        )
    }
}
