package com.sopetit.softie.domain.entity

data class Token(
    val accessToken: String = "",
    val refreshToken: String = "",
    val isMemberDollExist: Boolean = false
)
