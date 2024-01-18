package com.sopetit.softie.data.entity.response

import com.sopetit.softie.domain.entity.Routine
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RoutineItemEntity(
    @SerialName("routineId")
    val routineId: Int,
    @SerialName("content")
    val content: String
) {
    fun toRoutine(): Routine = Routine(
        routineId = this.routineId,
        content = this.content
    )
}
