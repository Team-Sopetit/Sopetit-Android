package com.sopetit.softie.domain.entity

data class MakerCard(
    val artistId: Int,
    val artistImageUrl: String,
    val subTitle: String,
    val title: String,
    val hashtag: List<Hashtag>
) {
    data class Hashtag(
        val hashtagId: Int,
        val content: String,
    )
}
