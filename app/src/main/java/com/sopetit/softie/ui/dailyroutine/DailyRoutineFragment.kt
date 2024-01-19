package com.sopetit.softie.ui.dailyroutine

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.sopetit.softie.R
import com.sopetit.softie.databinding.FragmentDailyRoutineBinding
import com.sopetit.softie.domain.entity.DailyRoutine
import com.sopetit.softie.ui.dailyroutine.complete.DailyRoutineCompleteActivity
import com.sopetit.softie.ui.dailyroutine.dailyroutineadd.DailyRoutineAddActivity
import com.sopetit.softie.util.OriginalBottomSheet.Companion.BOTTOM_SHEET_TAG
import com.sopetit.softie.util.binding.BindingAdapter.setCoilImage
import com.sopetit.softie.util.binding.BindingBottomSheet
import com.sopetit.softie.util.binding.BindingFragment
import com.sopetit.softie.util.setSingleOnClickListener
import com.sopetit.softie.util.setStatusBarColor
import com.sopetit.softie.util.snackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DailyRoutineFragment :
    BindingFragment<FragmentDailyRoutineBinding>(R.layout.fragment_daily_routine) {
    private val viewModel by viewModels<DailyRoutineViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setStatusBarColor(R.color.background)
        binding.viewModel = viewModel

        viewModel.getDailyRoutine()
        moveToAddRoutine()
        initSetDailyRoutineContent()
        initSetDeleteView()
        initSetRoutineDelete()
        addDailyRoutineMsg()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getDailyRoutine()
    }

    private fun initSetDailyRoutineContent() {
        viewModel.dailyRoutineListResponse.observe(viewLifecycleOwner) { dailyRoutineList ->
            when (dailyRoutineList.size) {
                3 -> {
                    makeRoutineItemThirdView()
                    makeRoutineItemSecondView()
                    makeRoutineItemFirstView()
                }

                2 -> {
                    makeRoutineItemFirstView()
                    makeRoutineItemSecondView()
                }

                1 -> {
                    makeRoutineItemFirstView()
                }
            }
        }
    }

    private fun makeRoutineItemFirstView() {
        with(binding) {
            routineItemView(
                ivDailyRoutineIconFirst,
                tvDailyRoutineAddNameFirst,
                tvDailyRoutineIngFirst,
                btnDailyRoutineYetFinFirst,
                0
            )
        }
    }

    private fun makeRoutineItemSecondView() {
        with(binding) {
            routineItemView(
                ivDailyRoutineIconSecond,
                tvDailyRoutineAddNameSecond,
                tvDailyRoutineIngSecond,
                btnDailyRoutineYetFinSecond,
                1
            )
        }
    }

    private fun makeRoutineItemThirdView() {
        with(binding) {
            routineItemView(
                ivDailyRoutineIconThird,
                tvDailyRoutineAddNameThird,
                tvDailyRoutineIngThird,
                btnDailyRoutineYetFinThird,
                2
            )
        }
    }

    private fun routineItemView(
        dailyIcon: ImageView,
        routineTitle: TextView,
        achieveMsg: TextView,
        btn: View,
        index: Int
    ) {
        val dailyRoutine = viewModel.dailyRoutineListResponse.value

        val achieveCountMsg =
            getString(R.string.daily_routine_ing).format(dailyRoutine?.get(index)?.achieveCount)
        achieveMsg.text = achieveCountMsg
        routineTitle.text = dailyRoutine?.get(index)?.content
        dailyIcon.setCoilImage(dailyRoutine?.get(index)?.iconImageUrl)
        viewModel.setRoutineAchieve(dailyRoutine?.get(index)?.isAchieve ?: false, index)

        initSetDailyRoutineAchieve(
            btn,
            (dailyRoutine?.get(index) ?: 0) as DailyRoutine,
            dailyRoutine?.get(index)?.routineId ?: 0
        )
    }

    private fun initSetDailyRoutineAchieve(btn: View, dailyRoutine: DailyRoutine, routineId: Int) {
        btn.setSingleOnClickListener {
            BindingBottomSheet.Builder().build(
                isDrawable = false,
                imageDrawable = 0,
                imageUri = dailyRoutine.iconImageUrl,
                title = "데일리 루틴을 완료했나요?",
                content = "한 번 완료하면 이전으로 되돌릴 수 없어요",
                isContentVisible = true,
                contentColor = R.color.gray400,
                backBtnContent = "아니, 아직이야!",
                doBtnContent = "완료했어",
                doBtnColor = R.drawable.shape_main1_fill_12_rect,
                backBtnAction = {},
                doBtnAction = {
                    startDailyRoutineCompleteActivity()
                    viewModel.patchAchieveDaily(routineId)
                    viewModel.getDailyRoutine()
                }
            ).show(parentFragmentManager, BOTTOM_SHEET_TAG)
        }
    }

    private fun initSetDeleteView() {
        viewModel.isDeleteView.observe(viewLifecycleOwner) { isDeleteView ->
            val isEditView = !isDeleteView
            if (isEditView) {
                binding.tvDailyRoutineEdit.setSingleOnClickListener {
                    viewModel.setDeleteView(true)
                }
            }
        }

        initSetBackOriginalView()
    }

    private fun initSetBackOriginalView() {
        viewModel.isDeleteView.observe(viewLifecycleOwner) { isDeleteView ->
            if (isDeleteView) {
                binding.tvDailyRoutineEdit.setSingleOnClickListener {
                    viewModel.setDeleteView(false)
                }
                clickEditRadioBtn()
            }
        }
    }

    private fun clickEditRadioBtn() {
        viewModel.dailyRoutineListResponse.observe(viewLifecycleOwner) { routineList ->
            when (routineList.size) {
                3 -> {
                    with(binding) {
                        changeBtnSelectState(btnDailyRoutineRadioFirst, routineList[0].routineId)
                        changeBtnSelectState(btnDailyRoutineRadioSecond, routineList[1].routineId)
                        changeBtnSelectState(btnDailyRoutineRadioThird, routineList[2].routineId)
                    }
                }

                2 -> {
                    with(binding) {
                        changeBtnSelectState(btnDailyRoutineRadioFirst, routineList[0].routineId)
                        changeBtnSelectState(btnDailyRoutineRadioSecond, routineList[1].routineId)
                    }
                }

                1 -> {
                    with(binding) {
                        changeBtnSelectState(btnDailyRoutineRadioFirst, routineList[0].routineId)
                    }
                }
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
        binding.btnDailyRoutineDelete.setSingleOnClickListener {
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
                    viewModel.deleteDailyRoutine()
                    viewModel.isDailyRoutineDelete.observe(viewLifecycleOwner) {
                        snackBar(
                            binding.root.rootView,
                            "데일리 루틴을 ${viewModel.editRoutineIdArray.size}개 삭제했어요"
                        )
                        viewModel.setDeleteView(false)
                        viewModel.getDailyRoutine()

                        resetRadioButtons()
                    }
                }
            ).show(parentFragmentManager, BOTTOM_SHEET_TAG)
        }
    }

    private fun resetRadioButtons() {
        with(binding) {
            btnDailyRoutineRadioFirst.isSelected = false
            btnDailyRoutineRadioSecond.isSelected = false
            btnDailyRoutineRadioThird.isSelected = false
        }
        viewModel.clearEditRoutineIdArray()
        setDeleteRoutineBtnContent()
    }


    private fun startDailyRoutineCompleteActivity() {
        val intentToCompleteActivity =
            Intent(requireActivity(), DailyRoutineCompleteActivity::class.java)
        startActivity(intentToCompleteActivity)
    }

    private val addDailyRoutineLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == AppCompatActivity.RESULT_OK) {
                snackBar(binding.root, getString(R.string.daily_routine_snack_bar))
            }
        }

    private fun addDailyRoutineMsg() {
        binding.ivDailyRoutineEmpty.setSingleOnClickListener {
            addDailyRoutineLauncher.launch(
                Intent(
                    requireActivity(),
                    DailyRoutineAddActivity::class.java
                )
            )
        }
    }

    private fun moveToAddRoutine() {
        binding.ivDailyRoutineEmpty.setSingleOnClickListener {
            val intent = Intent(requireContext(), DailyRoutineAddActivity::class.java)
            startActivity(intent)
        }
    }
}
