package com.sopetit.softie.data.entity.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostMemberHappyRoutineRequest(
    @SerialName("subRoutineId")
    val subRoutineId: Int
)
