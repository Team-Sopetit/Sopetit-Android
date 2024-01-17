package com.sopetit.softie.ui.setting

import android.content.Intent
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.sopetit.softie.R
import com.sopetit.softie.databinding.FragmentSettingUserExitBinding
import com.sopetit.softie.ui.main.LoginActivity
import com.sopetit.softie.util.binding.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class SettingUserExitFragment :
    BindingFragment<FragmentSettingUserExitBinding>(R.layout.fragment_setting_user_exit) {

    private lateinit var viewModel: SettingViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(SettingViewModel::class.java)
        binding.viewModel = viewModel

        initSetBear()
        initSetSpeechText()
        initSetClickBackBtn()
        initSetClickExitBtn()
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
            viewModel.setDeleteAuth()

            deleteAuth()
        }
    }

    private fun deleteAuth() {
        val intent = Intent(requireActivity(), LoginActivity::class.java)
        viewModel.isDeleteAuthResponse.observe(viewLifecycleOwner) { deleteSuccess ->
            if (deleteSuccess) {
                Timber.d("setting -> 멤버 탈퇴 성공")
                startActivity(intent)
            }
        }
    }

    companion object {
        const val SETTING_SPAN_START = 8
        const val SETTING_SPAN_END = 10
    }
}
