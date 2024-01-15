package com.sopetit.softie.data.entity.response

import com.sopetit.softie.domain.entity.Home
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HomeResponse(
    @SerialName("name")
    val name: String,
    @SerialName("dollType")
    val dollType: String,
    @SerialName("frameImageUrl")
    val frameImageUrl: String,
    @SerialName("dailyCottonCount")
    val dailyCottonCount: Int,
    @SerialName("happinessCottonCount")
    val happinessCottonCount: Int,
    @SerialName("conversations")
    val conversations: List<String>
) {
    fun toHome(): Home = Home(
        name = this.name,
        dollType = this.dollType,
        frameImageUrl = this.frameImageUrl,
        dailyCottonCount = this.dailyCottonCount,
        happinessCottonCount = this.happinessCottonCount,
        conversations = this.conversations
    )
}
