package com.sopetit.softie.ui.setting

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.sopetit.softie.R
import com.sopetit.softie.databinding.FragmentSettingInitBinding
import com.sopetit.softie.ui.login.LoginActivity
import com.sopetit.softie.ui.setting.SettingActivity.Companion.USER_EXIT
import com.sopetit.softie.ui.setting.SettingActivity.Companion.USER_SECURITY
import com.sopetit.softie.util.OriginalBottomSheet.Companion.BOTTOM_SHEET_TAG
import com.sopetit.softie.util.binding.BindingBottomSheet
import com.sopetit.softie.util.binding.BindingFragment
import timber.log.Timber

class SettingInitFragment :
    BindingFragment<FragmentSettingInitBinding>(R.layout.fragment_setting_init) {

    private lateinit var viewModel: SettingViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[SettingViewModel::class.java]
        binding.viewModel = viewModel

        initChangeFragment()
    }

    private fun initChangeFragment() {
        clickUserSecurity()
        clickLogOut()
        clickUserExit()
    }

    private fun clickUserSecurity() {
        binding.clSettingInitUserSecurity.setOnClickListener {
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
                title = getString(R.string.user_logout_title),
                content = getString(R.string.user_logout_content),
                isContentVisible = true,
                contentColor = R.color.gray300,
                backBtnContent = getString(R.string.user_logout_back),
                doBtnContent = getString(R.string.user_logout_exit),
                doBtnColor = R.drawable.shape_red_fill_12_rect,
                backBtnAction = {},
                doBtnAction = {
                    viewModel.setLogOut()

                    val intent = Intent(requireActivity(), LoginActivity::class.java)
                    viewModel.isLogOutResponse.observe(viewLifecycleOwner) { logOutSuccess ->
                        if (logOutSuccess) {
                            Timber.d("setting -> 로그 아웃 성공")
                            startActivity(intent)
                            requireActivity().finishAffinity()
                        }
                    }
                }
            ).show(parentFragmentManager, BOTTOM_SHEET_TAG)
        }
    }

    private fun clickUserExit() {
        binding.btnSettingInitUserExit.setOnClickListener {
            viewModel.setSettingFragment(USER_EXIT)
        }
    }
}
