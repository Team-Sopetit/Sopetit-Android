package com.sopetit.softie.ui.happyroutine.delete

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.sopetit.softie.R
import com.sopetit.softie.databinding.FragmentHappyDeleteBinding
import com.sopetit.softie.domain.entity.HappyUser
import com.sopetit.softie.ui.happyroutine.HappyRoutineFragment
import com.sopetit.softie.util.OriginalBottomSheet
import com.sopetit.softie.util.binding.BindingBottomSheet
import com.sopetit.softie.util.binding.BindingFragment

class HappyDeleteFragment :
    BindingFragment<FragmentHappyDeleteBinding>(R.layout.fragment_happy_delete) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel: HappyDeleteViewModel by activityViewModels()
        val happyDelete = viewModel.mockHappyDelete
        val routineId = 1

        setCardBinding(happyDelete)
        setCardEnter()
        setCancelEnter()
        setClearEnter(routineId)
    }

    private fun setCardBinding(happyDelete: HappyUser) {
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
            initSetDailyRoutineAchieve(binding.btnHappyDeleteClear, routineId)
        }
    }

    private fun initSetDailyRoutineAchieve(btn: View, routineId: Int) {
        btn.setOnClickListener {
            BindingBottomSheet.Builder().build(
                isDrawable = true,
                imageDrawable = R.drawable.ic_bear_face_crying,
                imageUri = "",
                title = "정말 삭제할까요?",
                content = "",
                isContentVisible = true,
                contentColor = R.color.gray400,
                backBtnContent = "취소",
                doBtnContent = "삭제할래",
                doBtnColor = R.drawable.shape_red_fill_12_rect,
                backBtnAction = {},
                doBtnAction = {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.fcv_main, HappyRoutineFragment())
                        .commit()
                }
            ).show(parentFragmentManager, OriginalBottomSheet.BOTTOM_SHEET_TAG)
        }
    }
}
