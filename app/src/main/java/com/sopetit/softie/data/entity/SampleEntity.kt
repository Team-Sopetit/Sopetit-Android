package com.sopetit.softie.data.entity

import com.sopetit.softie.domain.entity.Sample
import kotlinx.serialization.Serializable

@Serializable
data class SampleEntity(
    val name: String,
) {
    fun toSample(): Sample =
        Sample(
            name = this.name,
        )
}
