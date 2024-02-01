package com.sopetit.softie.domain.entity

data class UpdateVersion(
    val storeAppVersion: String,
    val forceAppVersion: String,
    val notificationTitle: String,
    val notificationContent: String
)
