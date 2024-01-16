package com.sopetit.softie.data.entity.response

import com.sopetit.softie.domain.entity.DailyRoutine
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DailyRoutineResponse(
    @SerialName("DailyRoutineItem")
    val routines: List<DailyRoutineItem>
) {
    @Serializable
    data class DailyRoutineItem(
        @SerialName("routineId")
        val routineId: Int,
        @SerialName("content")
        val content: String,
        @SerialName("iconImageUrl")
        val iconImageUrl: String,
        @SerialName("achieveCount")
        val achieveCount: Int,
        @SerialName("isAchieve")
        val isAchieve: Boolean
    )

    fun toUserDailyRoutine() = routines.map {
        DailyRoutine(
            routineId = it.routineId,
            content = it.content,
            iconImageUrl = it.iconImageUrl,
            achieveCount = it.achieveCount,
            isAchieve = it.isAchieve
        )
    }
}
