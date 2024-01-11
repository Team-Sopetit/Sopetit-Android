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
        binding.btnSettingLogout.setOnClickListener {
//            BindingBottomSheet.Builder().build(
//                image = "https://github.com/Team-Sopetit/Sopetit-Android/assets/128988935/765fdb9d-a29a-4c07-a9f5-852b8c6b3a7f",
//                title = "제목",
//                content = "내용",
//                isContentVisible = true,
//                contentColor = R.color.gray300,
//                backBtnContent = "돌아가기",
//                doBtnContent = "그냥 해",
//                doBtnColor = R.drawable.shape_red_fill_12_rect,
//                backBtnAction = {},
//                doBtnAction = {}
//            ).show(parentFragmentManager, "bottom sheet")

            BottomSheetFragment().show(parentFragmentManager, "bottom")

            // BottomSheet가 뜰 때 다시 적용
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            }
//            requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            requireActivity().window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    private fun clickUserExit() {
        binding.btnSettingUserExit.setOnClickListener {
            viewModel.setSettingFragment(USER_EXIT)
        }
    }
}
