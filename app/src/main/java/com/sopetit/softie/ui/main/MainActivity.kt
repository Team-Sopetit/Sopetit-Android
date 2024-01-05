package com.sopetit.softie.ui.main

import android.os.Bundle
import com.sopetit.softie.R
import com.sopetit.softie.databinding.ActivityMainBinding
import com.sopetit.softie.util.binding.BindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
