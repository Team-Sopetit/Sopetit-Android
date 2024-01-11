package com.sopetit.softie

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class SoftieApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//        setStatusBarTransparent()
    }

    private fun setStatusBarTransparent() {
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                applyWindowFlags(activity.window)
            }

            override fun onActivityStarted(activity: Activity) { // TODO("Not yet implemented")
            }

            override fun onActivityResumed(activity: Activity) { // TODO("Not yet implemented")
            }

            override fun onActivityPaused(activity: Activity) { // TODO("Not yet implemented")
            }

            override fun onActivityStopped(activity: Activity) { // TODO("Not yet implemented")
            }

            override fun onActivitySaveInstanceState(
                activity: Activity,
                outState: Bundle
            ) { // TODO("Not yet implemented")
            }

            override fun onActivityDestroyed(activity: Activity) { // TODO("Not yet implemented")
            }
        })
    }

    private fun applyWindowFlags(window: Window) {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }
}
