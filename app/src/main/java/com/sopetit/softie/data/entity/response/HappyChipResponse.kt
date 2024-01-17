package com.sopetit.softie.data.entity.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HappyChipResponse(
    @SerialName("themes")
    val themes: List<HappyChip>
) {
    @Serializable
    data class HappyChip(
        @SerialName("themeId")
        val themeId: Int,
        @SerialName("name")
        val name: String,
    )

    fun toHappyChip() = themes.map { happychip ->
        com.sopetit.softie.domain.entity.HappyChip(
            themeId = happychip.themeId,
            name = happychip.name,
        )
    }
}
