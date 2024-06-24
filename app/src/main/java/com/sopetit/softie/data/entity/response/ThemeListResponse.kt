package com.sopetit.softie.data.entity.response

import com.sopetit.softie.domain.entity.Theme
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ThemeListResponse(
    @SerialName("themes")
    val themes: List<ThemeItem>
) {
    @Serializable
    data class ThemeItem(
        @SerialName("themeId")
        val themeId: Int,
        @SerialName("name")
        val name: String,
        @SerialName("modifier")
        val modifier: String,
        @SerialName("description")
        val description: String
    )

    fun toTheme() = themes.map {
        Theme(
            themeId = it.themeId,
            name = it.name,
            modifier = it.modifier,
            description = it.description
        )
    }
}
