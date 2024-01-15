package com.sopetit.softie.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class NoResponse(
    val success: Boolean,
    val message: String
)
