package com.sopetit.softie

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import timber.log.Timber

class SoftieApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}