package com.sopetit.softie.data.entity.response

import com.sopetit.softie.domain.entity.AccessToken
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RefreshTokenResponse(
    @SerialName("accessToken")
    val accessToken: String
) {
    fun toAccessToken(): AccessToken = AccessToken(
        accessToken = this.accessToken
    )
}
