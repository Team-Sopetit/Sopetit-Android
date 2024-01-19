package com.sopetit.softie.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import java.io.Serializable

fun Context.hideKeyboard(view: View?) {
    if (view is EditText) {
        view.clearFocus()
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

fun snackBar(
    anchorView: View,
    message: String
) {
    CustomSnackbar.make(anchorView, message).show()
}

fun Context.toast(message: String) {
    val toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
    toast.show()
    Handler(Looper.getMainLooper()).postDelayed({
        run() { toast.cancel() }
    }, 1000)
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

fun <T : Serializable> Intent.intentSerializable(key: String, clazz: Class<T>): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        this.getSerializableExtra(key, clazz)
    } else {
        this.getSerializableExtra(key) as T?
    }
}

fun View.setSingleOnClickListener(onSingleClick: (View) -> Unit) {
    setOnClickListener(OnSingleClickListener { onSingleClick(it) })
}
