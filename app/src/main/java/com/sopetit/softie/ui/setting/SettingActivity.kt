package com.sopetit.softie.ui.setting

import android.os.Bundle
import com.sopetit.softie.R
import com.sopetit.softie.databinding.ActivitySettingBinding
import com.sopetit.softie.util.binding.BindingActivity

class SettingActivity : BindingActivity<ActivitySettingBinding>(R.layout.activity_setting) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initMakeInitFragment()
    }

    private fun initMakeInitFragment() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fcv_setting)
        if (currentFragment == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fcv_setting, SettingInitFragment())
                .commit()
        }
    }
}
