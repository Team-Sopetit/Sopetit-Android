package com.sopetit.softie.domain.entity

data class RoutineTheme(
    val themes: List<Themes>
) {
    data class Themes(
        val themeId: Int,
        val modifier: String,
        val name: String,
        val description: String,
    )
}
