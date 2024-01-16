package com.sopetit.softie.data.entity.response

import com.sopetit.softie.domain.entity.AddRoutine
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddDailyRoutineResponse(
    @SerialName("routineId")
    val routineId: Int
) {
    fun toAddDailyRoutine(): AddRoutine = AddRoutine(
        routineId = this.routineId
    )
}
