package com.sopetit.softie.util

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.sopetit.softie.R
import com.sopetit.softie.databinding.FragmentBottomsheetBinding
import com.sopetit.softie.util.binding.BindingAdapter.setCoilImage
import com.sopetit.softie.util.binding.BindingBottomSheet

class OriginalBottomSheet : BindingBottomSheet() {

    private val binding: FragmentBottomsheetBinding
        get() = requireNotNull(_binding as FragmentBottomsheetBinding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBottomsheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setImage() {
        if (isDrawable == true) {
            imageDrawable?.let { binding.ivBottomSheet.setImageResource(it) }
        } else {
            binding.ivBottomSheet.setCoilImage(imageUri)
        }
    }

    override fun setTitle() {
        binding.tvBottomSheetTitle.text = title
    }

    override fun setContent() {
        binding.tvBottomSheetContent.text = content
    }

    override fun setContentVisible() {
        if (isContentVisible == true) {
            binding.tvBottomSheetContent.visibility = View.VISIBLE
        } else {
            binding.tvBottomSheetContent.visibility = View.INVISIBLE
        }
    }

    override fun setContentColor() {
        binding.tvBottomSheetContent.setTextColor(
            ContextCompat.getColor(requireContext(), contentColor ?: R.color.gray400)
        )
    }

    override fun setBackBtnContent() {
        binding.btnBottomSheetBack.text = backBtnContent
    }

    override fun setDoBtnContent() {
        binding.btnBottomSheetDo.text = doBtnContent
    }

    override fun setDoBtnColor() {
        doBtnColor?.let { binding.btnBottomSheetDo.setBackgroundResource(it) }
    }

    override fun setBackBtnClick(action: () -> Unit) {
        binding.btnBottomSheetBack.setSingleOnClickListener { action() }
    }

    override fun setDoBtnClick(action: () -> Unit) {
        binding.btnBottomSheetDo.setSingleOnClickListener { action() }
    }

    companion object {
        const val BOTTOM_SHEET_TAG = "BOTTOM_SHEET_TAG"
    }
}
