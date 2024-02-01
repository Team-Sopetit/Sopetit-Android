package com.sopetit.softie.data.entity.response

import com.sopetit.softie.domain.entity.UpdateVersion
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VersionResponse(
    @SerialName("iosVersion")
    val iosVersion: UpdateVersionEntity,
    @SerialName("androidVersion")
    val androidVersion: UpdateVersionEntity,
    @SerialName("notificationTitle")
    val notificationTitle: String,
    @SerialName("notificationContent")
    val notificationContent: String
) {
    @Serializable
    data class UpdateVersionEntity(
        @SerialName("appVersion")
        val appVersion: String,
        @SerialName("forceUpdateVersion")
        val forceUpdateVersion: String
    )

    fun toUpdateVersion(): UpdateVersion = UpdateVersion(
        storeAppVersion = this.androidVersion.appVersion,
        forceAppVersion = this.androidVersion.forceUpdateVersion,
        notificationTitle = this.notificationTitle,
        notificationContent = this.notificationContent
    )
}
