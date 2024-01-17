package com.sopetit.softie.util // ktlint-disable filename

import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import com.sopetit.softie.R
import kotlin.system.exitProcess

fun AppCompatActivity.initBackPressedCallback() {
    var onBackPressedTime = 0L
    val deadline = 2000L
    onBackPressedDispatcher.addCallback(this) {
        if (System.currentTimeMillis() - onBackPressedTime >= deadline) {
            onBackPressedTime = System.currentTimeMillis()
            toast(getString(R.string.finish_app_toast_msg))
        } else {
            this@initBackPressedCallback.finishAffinity()
            System.runFinalization()
            exitProcess(0)
        }
    }
}
