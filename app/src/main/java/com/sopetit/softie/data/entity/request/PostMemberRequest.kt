package com.sopetit.softie.data.entity.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostMemberRequest(
    @SerialName("dollType")
    val dollType: String,
    @SerialName("name")
    val name: String,
    @SerialName("routines")
    val routines: ArrayList<Int>
)
