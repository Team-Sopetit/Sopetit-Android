package com.sopetit.softie.data.entity.response

import com.sopetit.softie.domain.entity.CottonCount
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CottonCountResponse(
    @SerialName("cottonCount")
    val cottonCount: Int
) {
    fun toCottonCount(): CottonCount = CottonCount(
        cottonCount = this.cottonCount
    )
}
