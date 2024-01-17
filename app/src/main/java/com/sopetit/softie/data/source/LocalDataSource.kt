package com.sopetit.softie.data.source

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val prefs: SharedPreferences
) {
    var accessToken: String
        set(value) = prefs.edit { putString(ACCESS_TOKEN, value) }
        get() = prefs.getString(ACCESS_TOKEN, "") ?: ""

    var refreshToken: String
        set(value) = prefs.edit { putString(REFRESH_TOKEN, value) }
        get() = prefs.getString(REFRESH_TOKEN, "") ?: ""

    var isUserSignUp: Boolean
        set(value) = prefs.edit { putBoolean(USER, value) }
        get() = prefs.getBoolean(USER, false)

    companion object {
        private const val ACCESS_TOKEN = "access_token"
        private const val REFRESH_TOKEN = "refresh_token"
        private const val USER = "user"
    }
}
