package com.sopetit.softie.ui.setting

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.sopetit.softie.R
import com.sopetit.softie.databinding.FragmentSettingInitBinding
import com.sopetit.softie.ui.login.LoginActivity
import com.sopetit.softie.ui.setting.SettingActivity.Companion.USER_EXIT
import com.sopetit.softie.util.OriginalBottomSheet.Companion.BOTTOM_SHEET_TAG
import com.sopetit.softie.util.binding.BindingBottomSheet
import com.sopetit.softie.util.binding.BindingFragment
import com.sopetit.softie.util.setSingleOnClickListener
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
        clickUserSecurity()
        clickDocument()
        clickFeedback()
    }

    private fun clickUserSecurity() {
        binding.clSettingInitUserSecurity.setSingleOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(getString(R.string.setting_user_security_link))
                )
            )
        }
    }

    private fun clickDocument() {
        binding.clSettingInitDocument.setSingleOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(getString(R.string.setting_service_document_link))
                )
            )
        }
    }

    private fun clickFeedback() {
        binding.clSettingInitFeedback.setSingleOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(getString(R.string.setting_feedback_link))
                )
            )
        }
    }

    private fun clickLogOut() {
        binding.btnSettingInitLogout.setSingleOnClickListener {
            BindingBottomSheet.Builder().build(
                isDrawable = true,
                imageDrawable = R.drawable.ic_bear_face_crying,
                imageUri = "",
                title = getString(R.string.user_logout_title),
                content = getString(R.string.user_logout_content),
                isContentVisible = true,
                contentColor = R.color.gray400,
                backBtnContent = getString(R.string.user_logout_back),
                doBtnContent = getString(R.string.user_logout_exit),
                doBtnColor = R.drawable.shape_red200_fill_12_rect,
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
        binding.btnSettingInitUserExit.setSingleOnClickListener {
            viewModel.setSettingFragment(USER_EXIT)
        }
    }
}
