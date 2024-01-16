package com.sopetit.softie.data.entity.response

import com.sopetit.softie.domain.entity.CottonCount

data class CottonCountResponse(
    val cottonCount: Int
) {
    fun toCottonCount(): CottonCount = CottonCount(
        cottonCount = this.cottonCount
    )
}
