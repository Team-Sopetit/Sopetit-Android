package com.sopetit.softie.ui.setting

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.sopetit.softie.R
import com.sopetit.softie.databinding.FragmentSettingUserExitBinding
import com.sopetit.softie.util.binding.BindingFragment

class SettingUserExitFragment :
    BindingFragment<FragmentSettingUserExitBinding>(R.layout.fragment_setting_user_exit) {

    private lateinit var viewModel: SettingViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(SettingViewModel::class.java)
        binding.viewModel = viewModel

        initSetBackBtn()
    }

    private fun initSetBackBtn() {
        viewModel.isClickBackBtn.observe(viewLifecycleOwner) { isClick ->
            if (isClick) {
                viewModel.setSettingFragment(SettingActivity.SETTING_INIT)
            }
        }
    }
}
