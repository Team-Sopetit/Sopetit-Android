package com.sopetit.softie

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
class SoftieApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}