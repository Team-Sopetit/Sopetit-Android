package com.sopetit.softie.ui.dailyroutine

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.sopetit.softie.R
import com.sopetit.softie.databinding.FragmentDailyRoutineBinding
import com.sopetit.softie.util.OriginalBottomSheet.Companion.BOTTOM_SHEET_TAG
import com.sopetit.softie.util.binding.BindingBottomSheet
import com.sopetit.softie.domain.entity.Bear
import com.sopetit.softie.ui.dailyroutine.dailyroutineedit.DailyRoutineEditActivity
import com.sopetit.softie.util.binding.BindingFragment
import com.sopetit.softie.util.setStatusBarColor
import com.sopetit.softie.util.snackBar
import com.sopetit.softie.util.toast

class DailyRoutineFragment :
    BindingFragment<FragmentDailyRoutineBinding>(R.layout.fragment_daily_routine) {

    private val viewModel by viewModels<DailyRoutineViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setStatusBarColor(R.color.background)

        binding.viewModel = viewModel

        initSetDailyRoutineContent()
        initSetDeleteView()
        initSetRoutineDelete()
    }

    private fun initSetDailyRoutineContent() {
        with(binding) {
            routineItemView(
                tvDailyRoutineAddNameFirst,
                tvDailyRoutineIngFirst,
                btnDailyRoutineYetFinFirst,
                0
            )
            routineItemView(
                tvDailyRoutineAddNameSecond,
                tvDailyRoutineIngSecond,
                btnDailyRoutineYetFinSecond,
                1
            )
            routineItemView(
                tvDailyRoutineAddNameThird,
                tvDailyRoutineIngThird,
                btnDailyRoutineYetFinThird,
                2
            )
        }
    }

    private fun routineItemView(
        routineTitle: TextView,
        achieveMsg: TextView,
        btn: View,
        index: Int
    ) {
        viewModel.mockDailyRoutineList.observe(viewLifecycleOwner) { dailyRoutineList ->
            val achieveCountMsg =
                getString(R.string.daily_routine_ing).format(dailyRoutineList[index].achieveCount)
            achieveMsg.text = achieveCountMsg
            routineTitle.text = dailyRoutineList[index].content
            viewModel.setRoutineAchieve(dailyRoutineList[index].isAchieve, index)

            initSetDailyRoutineAchieve(btn, dailyRoutineList[index].routineId)
        }
    }

    private fun initSetDailyRoutineAchieve(btn: View, routineId: Int) {
        btn.setOnClickListener {
            // TODO 서버통신 구현 후 imageUri 버전으로 수정

        moveEdit()

// /여기서부터 코드 정리 필요
        val bundle = Bundle().apply {
            putSerializable("key", Bear.RED)
        }

        binding.btnDailyRoutineYetFinFirst.setOnClickListener {
            val intentToCompleteActivity =
                Intent(requireActivity(), DailyRoutineCompleteActivity::class.java)
            intentToCompleteActivity.putExtras(bundle)
            startActivity(intentToCompleteActivity)
        }
        binding.btnDailyRoutineYetFinSecond.setOnClickListener {
            val intentToCompleteActivity =
                Intent(requireActivity(), DailyRoutineCompleteActivity::class.java)
            intentToCompleteActivity.putExtras(bundle)
            startActivity(intentToCompleteActivity)
        }
        binding.btnDailyRoutineYetFinThird.setOnClickListener {
            val intentToCompleteActivity =
                Intent(requireActivity(), DailyRoutineCompleteActivity::class.java)
            intentToCompleteActivity.putExtras(bundle)
            startActivity(intentToCompleteActivity)
        }
    }

    private fun initSetDeleteView() {
        viewModel.isDeleteView.observe(viewLifecycleOwner) { isDeleteView ->
            val isEditView = !isDeleteView
            if (isEditView) {
                binding.tvDailyRoutineEdit.setOnClickListener {
                    viewModel.setDeleteView(true)
                }
            }
        }

        initSetBackOriginalView()
    }

    private fun initSetBackOriginalView() {
        viewModel.isDeleteView.observe(viewLifecycleOwner) { isDeleteView ->
            if (isDeleteView) {
                binding.tvDailyRoutineEdit.setOnClickListener {
                    viewModel.setDeleteView(false)
                }
                clickEditRadioBtn()
            }
        }
    }

    private fun clickEditRadioBtn() {
        viewModel.mockDailyRoutineList.observe(viewLifecycleOwner) { routineList ->
            with(binding) {
                changeBtnSelectState(btnDailyRoutineRadioFirst, routineList[0].routineId)
                changeBtnSelectState(btnDailyRoutineRadioSecond, routineList[1].routineId)
                changeBtnSelectState(btnDailyRoutineRadioThird, routineList[2].routineId)
            }
        }
    }

    private fun changeBtnSelectState(button: View, itemId: Int) {
        button.setOnClickListener {
            it.isSelected = !it.isSelected
            viewModel.setEditRoutineIdArray(itemId)
            setDeleteRoutineBtnContent()
        }
    }

    private fun setDeleteRoutineBtnContent() {
        val editRoutineArraySize = viewModel.editRoutineIdArray.size
        val deleteBtnContent =
            getString(R.string.daily_routine_edit_delete).format(editRoutineArraySize)

        if (editRoutineArraySize > 0) {
            viewModel.setEditBtnEnabled(true)
        } else {
            viewModel.setEditBtnEnabled(false)
        }

        binding.btnDailyRoutineDelete.text = deleteBtnContent
    }

    private fun initSetRoutineDelete() {
        viewModel.isEditBtnEnabled.observe(viewLifecycleOwner) { isBtnEnabled ->
            if (isBtnEnabled) {
                binding.btnDailyRoutineDelete.setOnClickListener {
                    BindingBottomSheet.Builder().build(
                        isDrawable = true,
                        imageDrawable = R.drawable.ic_bear_face_crying,
                        imageUri = "",
                        title = "정말 삭제할까요?",
                        content = "루틴을 삭제해도 달성 횟수는 저장돼요",
                        isContentVisible = true,
                        contentColor = R.color.red,
                        backBtnContent = "취소",
                        doBtnContent = "삭제할래",
                        doBtnColor = R.drawable.shape_red_fill_12_rect,
                        backBtnAction = {},
                        doBtnAction = {
                            snackBar(
                                binding.root.rootView,
                                "데일리 루틴을 ${viewModel.editRoutineIdArray.size}개 삭제했어요"
                            )
                            viewModel.setDeleteView(false)
                        }
                    ).show(parentFragmentManager, BOTTOM_SHEET_TAG)
                }
            }
        }
    }
}
