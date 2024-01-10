package com.sopetit.softie.ui.setting

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.sopetit.softie.R
import com.sopetit.softie.databinding.ActivitySettingBinding
import com.sopetit.softie.util.binding.BindingActivity

class SettingActivity : BindingActivity<ActivitySettingBinding>(R.layout.activity_setting) {

    private val viewModel by viewModels<SettingViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        initMakeInitFragment()
        initChangeFragment()
    }

    private fun initMakeInitFragment() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fcv_setting)
        if (currentFragment == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fcv_setting, SettingInitFragment())
                .commit()
        }
    }

    private fun initChangeFragment() {
        viewModel.settingFragment.observe(this) { clickSetting ->
            when (clickSetting) {
                USER_EXIT -> changeSettingDetail(SettingUserExitFragment())
            }
        }
    }

    private fun changeSettingDetail(fragment: Fragment) {
        changeFragment(fragment)
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_setting, fragment)
            .commit()
    }

    private fun changeTopBarTitle() {}

    companion object {
        const val USER_SECURITY = "개인정보 처리방침"
        const val DOCUMENT = "서비스 이용 약관"
        const val GUIDE = "서비스 이용 가이드"
        const val FEEDBACK = "피드백"
        const val USER_EXIT = "회원 탈퇴"
    }
}
