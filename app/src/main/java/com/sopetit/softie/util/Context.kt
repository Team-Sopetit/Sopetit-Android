package com.sopetit.softie.util

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun snackBar(
    anchorView: View,
    message: String,
) {
    CustomSnackbar.make(anchorView, message).show()
}

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Fragment.setStatusBarColor(colorResId: Int) {
    activity?.let {
        val statusBarColor = ContextCompat.getColor(it, colorResId)
        it.window.statusBarColor = statusBarColor
    }
}

fun Activity.setStatusBarColorFromResource(colorResId: Int) {
    val statusBarColor = ContextCompat.getColor(this, colorResId)
    window.statusBarColor = statusBarColor
}
