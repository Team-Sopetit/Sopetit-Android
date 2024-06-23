package com.sopetit.softie.ui.addroutine.list

import android.app.Dialog
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.sopetit.softie.R
import com.sopetit.softie.databinding.DialogMakerHelpBinding

class MakerHelpDialogFragment : DialogFragment() {

    private var _binding: DialogMakerHelpBinding? = null
    private val binding get() = _binding
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setContentView(R.layout.dialog_maker_help)

        _binding =
            DialogMakerHelpBinding.bind(dialog.findViewById<View>(R.id.cl_maker_help_dialog_border))
        return dialog
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.attributes?.let {
            it.dimAmount = 0.72f
            dialog?.window?.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        }

        dialog?.window?.setGravity(Gravity.TOP or Gravity.END)
        val params: WindowManager.LayoutParams? = dialog?.window?.attributes

        params?.x =
            requireContext().resources.getDimensionPixelSize(R.dimen.maker_help_dialog_end_margin)
        params?.y =
            requireContext().resources.getDimensionPixelSize(R.dimen.maker_help_dialog_top_margin)
        dialog?.window?.attributes = params

        binding?.ivMakerHelpDialogExit?.setOnClickListener {
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
