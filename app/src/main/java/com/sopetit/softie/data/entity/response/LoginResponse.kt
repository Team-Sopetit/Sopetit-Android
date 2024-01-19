package com.sopetit.softie.data.entity.response

import com.sopetit.softie.domain.entity.Token
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val accessToken: String,
    val refreshToken: String,
    val isMemberDollExist: Boolean
) {
    fun toToken(): Token = Token(
        accessToken = this.accessToken,
        refreshToken = this.refreshToken,
        isMemberDollExist = this.isMemberDollExist
    )
}
