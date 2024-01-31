package com.sopetit.softie.ui.onboarding.splash

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.sopetit.softie.R
import com.sopetit.softie.databinding.DialogUpdateRecommendBinding

class UpdateRecommendDialogFragment(
    updateRecommendDialogInterface: UpdateRecommendDialogInterface,
    val title: String,
    val content: String
) : DialogFragment() {
    private var _binding: DialogUpdateRecommendBinding? = null
    private val binding get() = _binding ?: error(getString(R.string.binding_error))
    private var updateRecommendDialogInterface: UpdateRecommendDialogInterface? = null

    init {
        this.updateRecommendDialogInterface = updateRecommendDialogInterface
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogUpdateRecommendBinding.inflate(inflater, container, false)
        val view = binding.root
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        setTextView()
        setBtnClickEvent()

        return view
    }

    private fun setTextView() {
        binding.tvUpdateRecommendDialogTitle.text = title
        binding.tvUpdateRecommendDialogContent.text = content
    }

    private fun setBtnClickEvent() {
        setClickDenyBtn()
        setClickConfirmBtn()
    }

    private fun setClickDenyBtn() {
        binding.btnUpdateRecommendDialogCancel.setOnClickListener {
            this.updateRecommendDialogInterface?.startApp()
            dismiss()
        }
    }

    private fun setClickConfirmBtn() {
        binding.btnUpdateRecommendDialogConfirm.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(getString(R.string.dialog_update_store_link))
                )
            )
            dismiss()
            this.updateRecommendDialogInterface?.finishApp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

interface UpdateRecommendDialogInterface {
    fun startApp()
    fun finishApp()
}
