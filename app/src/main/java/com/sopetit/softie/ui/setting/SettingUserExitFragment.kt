package com.sopetit.softie.ui.setting

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.core.content.ContextCompat
import com.sopetit.softie.R
import com.sopetit.softie.databinding.FragmentSettingUserExitBinding
import com.sopetit.softie.util.binding.BindingFragment

class SettingUserExitFragment :
    BindingFragment<FragmentSettingUserExitBinding>(R.layout.fragment_setting_user_exit) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSetSpeechText()
        initSetClickBackBtn()
    }

    private fun initSetSpeechText() {
        binding.tvUserExitSpeech.text =
            SpannableStringBuilder(getString(R.string.user_exit_title)).apply {
                setSpan(
                    ForegroundColorSpan(
                        ContextCompat.getColor(
                            requireActivity(),
                            R.color.red
                        )
                    ),
                    SETTING_SPAN_START,
                    SETTING_SPAN_END,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
    }

    private fun initSetBear() {
        // TODO bear type 받아서 bear 이미지 띄우기
    }

    private fun initSetClickBackBtn() {
        binding.btnUserExitBack.setOnClickListener {
            (activity as SettingActivity).backInitView(this)
        }
    }

    private fun initSetClickExitBtn() {
        binding.btnUserExitExit.setOnClickListener {
            // TODO 회원탈퇴 로직 추가
        }
    }

    companion object {
        const val SETTING_SPAN_START = 8
        const val SETTING_SPAN_END = 10
    }
}
