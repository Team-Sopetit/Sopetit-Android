package com.sopetit.softie.domain.entity

data class Home(
    val name: String,
    val dollType: String,
    val frameImageUrl: String,
    val dailyCottonCount: Int,
    val happinessCottonCount: Int,
    val conversations: List<String>
)
