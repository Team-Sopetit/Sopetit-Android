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
import com.sopetit.softie.databinding.DialogUpdateForceBinding

class UpdateForceDialogFragment(
    updateForceDialogInterface: UpdateForceDialogInterface
) : DialogFragment() {
    private var _binding: DialogUpdateForceBinding? = null
    private val binding get() = _binding ?: error(getString(R.string.binding_error))
    private var updateForceDialogInterface: UpdateForceDialogInterface? = null

    init {
        this.updateForceDialogInterface = updateForceDialogInterface
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogUpdateForceBinding.inflate(inflater, container, false)
        val view = binding.root
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.btnUpdateForceDialogConfirm.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(getString(R.string.dialog_update_store_link))
                )
            )
            dismiss()
            this.updateForceDialogInterface?.finishApp()
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

interface UpdateForceDialogInterface {
    fun finishApp()
}
