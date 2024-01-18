package com.sopetit.softie.data.entity.response

import com.sopetit.softie.domain.entity.HappyProgress
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HappyProgressResponse(
    @SerialName("routineId")
    val routineId: Int,
    @SerialName("iconImageUrl")
    val iconImageUrl: String,
    @SerialName("contentImageUrl")
    val contentImageUrl: String,
    @SerialName("themeName")
    val themeName: String,
    @SerialName("themeNameColor")
    val themeNameColor: String,
    @SerialName("title")
    val title: String,
    @SerialName("content")
    val content: String,
    @SerialName("detailContent")
    val detailContent: String,
    @SerialName("place")
    val place: String,
    @SerialName("timeTaken")
    val timeTaken: String
) {
    fun toHappyProgress() = HappyProgress(
        routineId = this.routineId,
        iconImageUrl = this.iconImageUrl,
        contentImageUrl = this.contentImageUrl,
        themeName = this.themeName,
        themeNameColor = this.themeNameColor,
        title = this.title,
        content = this.content,
        detailContent = this.detailContent,
        place = this.place,
        timeTaken = this.timeTaken
    )
}
