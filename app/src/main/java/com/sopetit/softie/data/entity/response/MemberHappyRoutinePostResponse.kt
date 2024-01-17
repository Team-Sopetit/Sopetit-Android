package com.sopetit.softie.data.entity.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MemberHappyRoutinePostResponse(
    @SerialName("routineId")
    val routineId: Int
)
