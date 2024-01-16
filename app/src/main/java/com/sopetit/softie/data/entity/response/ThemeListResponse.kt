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
        @SerialName("iconImageUrl")
        val iconImageUrl: String,
        @SerialName("backgroundImageUrl")
        val backgroundImageUrl: String
    )

    fun toTheme() = themes.map {
        Theme(
            themeId = it.themeId,
            name = it.name,
            iconImageUrl = it.iconImageUrl,
            backgroundImageUrl = it.backgroundImageUrl
        )
    }
}
