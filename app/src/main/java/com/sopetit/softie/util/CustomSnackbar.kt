package com.sopetit.softie.util

import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.google.android.material.snackbar.Snackbar
import com.sopetit.softie.R
import com.sopetit.softie.databinding.SnackbarCustomBinding

class CustomSnackbar(view: View, private val message: String, anchor: View) {

    private val context = view.context
    private val snackbar = Snackbar.make(view, "", Snackbar.LENGTH_SHORT).apply {
        anchorView = anchor
    }
    private val snackbarLayout = snackbar.view as Snackbar.SnackbarLayout

    private val binding: SnackbarCustomBinding =
        DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.snackbar_custom, null, false)

    init {
        initMakeView()
        initSetContent()
    }

    private fun initMakeView() {
        with(snackbarLayout) {
            removeAllViews()
            setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent))
            addView(binding.root, 0)
        }
    }

    private fun initSetContent() {
        binding.tvSnackbarCustomContent.text = message
    }

    fun show() {
        snackbar.show()
    }

    fun show(duration: Int) {
        snackbar.duration = duration
        snackbar.show()
    }

    companion object {
        fun make(view: View, message: String, anchor: View) = CustomSnackbar(view, message, anchor)
    }
}
