package com.sopetit.softie.ui.setting

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.sopetit.softie.R
import com.sopetit.softie.databinding.FragmentSettingInitBinding
import com.sopetit.softie.ui.setting.SettingActivity.Companion.USER_EXIT
import com.sopetit.softie.ui.setting.SettingActivity.Companion.USER_SECURITY
import com.sopetit.softie.util.binding.BindingBottomSheet
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
        clickLogOut()
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

    private fun clickLogOut() {
        binding.btnSettingInitLogout.setOnClickListener {
            BindingBottomSheet.Builder().build(
                isDrawable = true,
                imageDrawable = R.drawable.ic_bear_face_crying,
                imageUri = "",
                title = "제목",
                content = "내용",
                isContentVisible = true,
                contentColor = R.color.gray300,
                backBtnContent = "돌아가기",
                doBtnContent = "그냥 해",
                doBtnColor = R.drawable.shape_red_fill_12_rect,
                backBtnAction = {},
                doBtnAction = {}
            ).show(parentFragmentManager, BOTTOM_SHEET_TAG)
        }
    }

    private fun clickUserExit() {
        binding.btnSettingInitUserExit.setOnClickListener {
            viewModel.setSettingFragment(USER_EXIT)
        }
    }

    companion object {
        const val BOTTOM_SHEET_TAG = "BOTTOM SHEET TAG"
    }
}
