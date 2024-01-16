package com.sopetit.softie.data.entity.response

import com.sopetit.softie.domain.entity.Routine
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DailyRoutineListResponse(
    @SerialName("routines")
    val routines: List<RoutineItem>
) {
    @Serializable
    data class RoutineItem(
        @SerialName("routineId")
        val routineId: Int,
        @SerialName("content")
        val content: String
    )

    fun toRoutine() = routines.map {
        Routine(
            routineId = it.routineId,
            content = it.content
        )
    }
}
