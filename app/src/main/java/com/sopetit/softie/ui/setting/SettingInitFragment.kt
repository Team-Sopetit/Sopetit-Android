package com.sopetit.softie.ui.setting

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.sopetit.softie.R
import com.sopetit.softie.databinding.FragmentSettingInitBinding
import com.sopetit.softie.ui.setting.SettingActivity.Companion.USER_EXIT
import com.sopetit.softie.ui.setting.SettingActivity.Companion.USER_SECURITY
import com.sopetit.softie.util.binding.BindingFragment

class SettingInitFragment :
    BindingFragment<FragmentSettingInitBinding>(R.layout.fragment_setting_init) {

    private lateinit var viewModel: SettingViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(SettingViewModel::class.java)
        binding.viewModel = viewModel

        initChangeFragment()
    }

    private fun initChangeFragment() {
        clickUserSecurity()
        clickUserExit()
    }

    private fun clickUserSecurity() {
        binding.clSettingUserSecurity.setOnClickListener {
            viewModel.setSettingFragment(USER_SECURITY)
        }
    }

    private fun clickDocument() {
        // TODO 서비스 이용 약관
    }

    private fun clickGuide() {
        // TODO 서비스 이용 가이드
    }

    private fun clickFeedback() {
        // TODO 피드백
    }

    private fun clickUserExit() {
        binding.btnSettingUserExit.setOnClickListener {
            viewModel.setSettingFragment(USER_EXIT)
        }
    }
}
