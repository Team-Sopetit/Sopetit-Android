package com.sopetit.softie.domain.entity

data class Home(
    val frameImageUrl: String,
    val name: String,
    val dollType: String,
    val dailyCottonCount: Int,
    val happinessCottonCount: Int,
    val conversations: List<String>
)
