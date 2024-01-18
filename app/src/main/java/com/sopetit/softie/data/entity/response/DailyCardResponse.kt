package com.sopetit.softie.data.entity.response

import com.sopetit.softie.domain.entity.DailyCard
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DailyCardResponse(
    @SerialName("backgroundImageUrl")
    val backgroundImageUrl: String,
    @SerialName("routines")
    val routines: List<RoutineItemEntity>
) {
    fun toDailyCard(): DailyCard = DailyCard(
        backgroundImageUrl = this.backgroundImageUrl,
        routine = this.routines.map { routineItemEntity ->
            routineItemEntity.toRoutine()
        }
    )
}
