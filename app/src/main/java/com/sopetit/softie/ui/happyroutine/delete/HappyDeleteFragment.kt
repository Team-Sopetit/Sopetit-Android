package com.sopetit.softie.ui.happyroutine.delete

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.sopetit.softie.R
import com.sopetit.softie.databinding.FragmentHappyDeleteBinding
import com.sopetit.softie.domain.entity.HappyProgress
import com.sopetit.softie.ui.happyroutine.HappyRoutineFragment
import com.sopetit.softie.ui.happyroutine.progress.HappyProgressViewModel
import com.sopetit.softie.util.OriginalBottomSheet
import com.sopetit.softie.util.binding.BindingBottomSheet
import com.sopetit.softie.util.binding.BindingFragment
import com.sopetit.softie.util.snackBar

class HappyDeleteFragment :
    BindingFragment<FragmentHappyDeleteBinding>(R.layout.fragment_happy_delete) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel: HappyProgressViewModel by activityViewModels()
        val happyDelete = viewModel.mockHappyProgress
        val routineId = 1

        setCardBinding(happyDelete)
        setCardEnter()
        setCancelEnter()
        setClearEnter(routineId)
    }

    private fun setCardBinding(happyDelete: HappyProgress) {
        with(binding) {
            tvHappyDeleteSubtitle.text = happyDelete.title
            ivHappyDeleteCardFront.setImageResource(happyDelete.imageUrl)
            tvHappyDeleteCardFrontTitle.text = happyDelete.content
            tvHappyDeleteCardBackTitle.text = happyDelete.detailTitle
            tvHappyDeleteCardBackContent.text = happyDelete.detailContent
            tvHappyDeleteCardBackTime.text = happyDelete.detailTime
            tvHappyDeleteCardBackPlace.text = happyDelete.detailPlace
        }
    }

    private fun setCardEnter() {
        with(binding) {
            clHappyDeleteCardFront.setOnClickListener {
                setCardFlip(clHappyDeleteCardFront, clHappyDeleteCardBack)
            }
            clHappyDeleteCardBack.setOnClickListener {
                setCardFlip(clHappyDeleteCardBack, clHappyDeleteCardFront)
            }
        }
    }

    private fun setCancelEnter() {
        binding.tvHappyDeleteDestroy.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
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

    private fun setClearEnter(routineId: Int) {
        binding.btnHappyDeleteClear.setOnClickListener {
            BindingBottomSheet.Builder().build(
                isDrawable = true,
                imageDrawable = R.drawable.ic_bear_face_crying,
                imageUri = "",
                title = getString(R.string.happy_delete_bottom_sheet_title),
                content = "",
                isContentVisible = true,
                contentColor = R.color.gray400,
                backBtnContent = getString(R.string.happy_delete_bottom_sheet_back_btn),
                doBtnContent = getString(R.string.happy_delete_bottom_sheet_do_btn),
                doBtnColor = R.drawable.shape_red_fill_12_rect,
                backBtnAction = {},
                doBtnAction = {
                    snackBar(
                        binding.root.rootView,
                        getString(R.string.happy_routine_delete_snack_bar)
                    )
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.fcv_main, HappyRoutineFragment())
                        .commit()
                }
            ).show(parentFragmentManager, OriginalBottomSheet.BOTTOM_SHEET_TAG)
        }
    }
}
