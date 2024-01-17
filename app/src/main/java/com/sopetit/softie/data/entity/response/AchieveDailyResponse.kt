package com.sopetit.softie.data.entity.response

import com.sopetit.softie.domain.entity.DailyAchieve
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AchieveDailyResponse(
    @SerialName("routineId")
    val routineId: Int,
    @SerialName("isAchieve")
    val isAchieve: Boolean,
    @SerialName("achieveCount")
    val achieveCount: Int
) {
    fun toAchieveDaily(): DailyAchieve = DailyAchieve(
        routineId = this.routineId,
        isAchieve = this.isAchieve,
        achieveCount = this.achieveCount
    )
}
