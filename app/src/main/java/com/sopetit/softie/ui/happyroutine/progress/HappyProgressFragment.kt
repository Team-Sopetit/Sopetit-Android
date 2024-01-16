package com.sopetit.softie.ui.happyroutine.progress

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.sopetit.softie.R
import com.sopetit.softie.databinding.FragmentHappyProgressBinding
import com.sopetit.softie.domain.entity.HappyProgress
import com.sopetit.softie.ui.happyroutine.HappyRoutineFragment
import com.sopetit.softie.ui.happyroutine.delete.HappyDeleteFragment
import com.sopetit.softie.util.OriginalBottomSheet
import com.sopetit.softie.util.binding.BindingBottomSheet
import com.sopetit.softie.util.binding.BindingFragment
import com.sopetit.softie.util.snackBar

class HappyProgressFragment :
    BindingFragment<FragmentHappyProgressBinding>(R.layout.fragment_happy_progress) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel: HappyProgressViewModel by activityViewModels()
        val happyProgress = viewModel.mockHappyProgress

        setCardBinding(happyProgress)
        setCardEnter()
        setEditEnter()
        setClearEnter()
    }

    private fun setCardBinding(happyProgress: HappyProgress) {
        with(binding) {
            tvHappyProgressSubtitle.text = happyProgress.title
            ivHappyProgressCardFront.setImageResource(happyProgress.imageUrl)
            tvHappyProgressCardFrontTitle.text = happyProgress.content
            tvHappyProgressCardBackTitle.text = happyProgress.detailTitle
            tvHappyProgressCardBackContent.text = happyProgress.detailContent
            tvHappyProgressCardBackTime.text = happyProgress.detailTime
            tvHappyProgressCardBackPlace.text = happyProgress.detailPlace
        }
    }

    private fun setCardEnter() {
        with(binding) {
            clHappyProgressCardFront.setOnClickListener {
                setCardFlip(clHappyProgressCardFront, clHappyProgressCardBack)
            }
            clHappyProgressCardBack.setOnClickListener {
                setCardFlip(clHappyProgressCardBack, clHappyProgressCardFront)
            }
        }
    }

    private fun setEditEnter() {
        binding.tvHappyProgressEdit.setOnClickListener {
            val happyDeleteFragment = HappyDeleteFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fcv_main, happyDeleteFragment)
                .commit()
        }
    }

    private fun setCardFlip(viewFront: View, viewToBack: View) {
        val isVisible = viewFront.visibility == View.VISIBLE
        if (isVisible) {
            viewFront.visibility = View.INVISIBLE
            viewToBack.visibility = View.VISIBLE
        } else {
            viewFront.visibility = View.VISIBLE
            viewToBack.visibility = View.INVISIBLE
        }
    }

    private fun initHappyRoutineCompleteBottomSheet() {
        BindingBottomSheet.Builder().build(
            isDrawable = false,
            imageDrawable = 0,
            imageUri = "",
            title = getString(R.string.happy_progress_bottom_sheet_title),
            content = getString(R.string.happy_progress_bottom_sheet_content),
            isContentVisible = true,
            contentColor = R.color.gray400,
            backBtnContent = getString(R.string.happy_progress_sheet_back_btn),
            doBtnContent = getString(R.string.happy_progress_sheet_do_btn),
            doBtnColor = R.drawable.shape_main1_fill_12_rect,
            backBtnAction = {},
            doBtnAction = {
                moveToHappyRoutineEmptyCardView()
                snackBar(
                    binding.btnHappyProgressClear,
                    getString(R.string.happy_routine_delete_snack_bar)
                )
            }
        ).show(parentFragmentManager, OriginalBottomSheet.BOTTOM_SHEET_TAG)
    }

    private fun moveToHappyRoutineEmptyCardView() {
        val happyRoutineFragment = HappyRoutineFragment()
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_main, happyRoutineFragment)
            .commit()
    }

    private fun setClearEnter() {
        initHappyRoutineCompleteBottomSheet()
    }
}
