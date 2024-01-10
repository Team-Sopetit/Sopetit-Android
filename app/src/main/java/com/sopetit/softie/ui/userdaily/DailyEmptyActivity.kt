package com.sopetit.softie.ui.userdaily

import android.os.Bundle
import com.sopetit.softie.R
import com.sopetit.softie.databinding.ActivitySampleBinding
import com.sopetit.softie.util.binding.BindingActivity

class DailyEmptyActivity : BindingActivity<ActivitySampleBinding>(R.layout.activity_sample) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initFragment()
    }

    private fun initFragment() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fcv_empty_fragment)
        if (currentFragment == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fcv_empty_fragment, DailyEmptyFragment())
                .commit()
        }
    }
}
