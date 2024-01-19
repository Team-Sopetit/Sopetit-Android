package com.sopetit.softie.ui.setting

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.sopetit.softie.R
import com.sopetit.softie.databinding.ActivitySettingBinding
import com.sopetit.softie.util.binding.BindingActivity
import com.sopetit.softie.util.setSingleOnClickListener
import com.sopetit.softie.util.setStatusBarColorFromResource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingActivity : BindingActivity<ActivitySettingBinding>(R.layout.activity_setting) {

    private val viewModel by viewModels<SettingViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        setStatusBarColorFromResource(R.color.white)

        initMakeInitFragment()
        initChangeFragment()
    }

    private fun initMakeInitFragment() {
        viewModel.setSettingFragment(SETTING_INIT)
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fcv_setting)
        if (currentFragment == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fcv_setting, SettingInitFragment())
                .commit()
        }
    }

    private fun initChangeFragment() {
        setClickBackBtnInDetailView(SettingUserExitFragment())
        setCLickSettingMenu()
    }

    private fun setCLickSettingMenu() {
        viewModel.settingFragment.observe(this) { clickSetting ->
            when (clickSetting) {
                USER_SECURITY -> changeFragment(SettingUserSecurityFragment())
                USER_EXIT -> changeFragment(SettingUserExitFragment())
                // TODO 다른 세부 설정 화면 추가
            }
        }
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_setting, fragment)
            .addToBackStack(FRAGMENT_STACK_TAG).commitAllowingStateLoss()
    }

    private fun setClickBackBtnInDetailView(fragment: Fragment) {
        binding.btnSettingBack.setSingleOnClickListener {
            if (viewModel.settingFragment.value == SETTING_INIT) finish()
            else backInitView(fragment)
        }
    }

    fun backInitView(fragment: Fragment) {
        with(supportFragmentManager) {
            beginTransaction().remove(fragment).commit()
            popBackStack()
        }
        viewModel.setSettingFragment(SETTING_INIT)
    }

    companion object {
        const val SETTING_INIT = "설정"
        const val USER_SECURITY = "개인정보 처리방침"
        const val DOCUMENT = "서비스 이용 약관"
        const val GUIDE = "서비스 이용 가이드"
        const val FEEDBACK = "피드백"
        const val USER_EXIT = "회원 탈퇴"
        const val FRAGMENT_STACK_TAG = "BACK_STACK_TAG"
    }
}
