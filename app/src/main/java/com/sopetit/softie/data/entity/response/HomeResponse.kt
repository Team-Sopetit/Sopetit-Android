package com.sopetit.softie.data.entity.response

import com.sopetit.softie.domain.entity.Home
import kotlinx.serialization.Serializable

@Serializable
data class HomeResponse(
    val name: String,
    val dollType: String,
    val frameImageUrl: String,
    val dailyCottonCount: Int,
    val happinessCottonCount: Int,
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
