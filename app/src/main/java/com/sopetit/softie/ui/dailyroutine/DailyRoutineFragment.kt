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
import com.sopetit.softie.ui.main.MainActivity
import com.sopetit.softie.util.CustomSnackbar
import com.sopetit.softie.util.OriginalBottomSheet.Companion.BOTTOM_SHEET_TAG
import com.sopetit.softie.util.binding.BindingAdapter.setCoilImage
import com.sopetit.softie.util.binding.BindingBottomSheet
import com.sopetit.softie.util.binding.BindingFragment
import com.sopetit.softie.util.setSingleOnClickListener
import com.sopetit.softie.util.setStatusBarColor
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
                title = getString(R.string.daily_routine_complete_question),
                content = getString(R.string.daily_routine_complete_notice),
                isContentVisible = true,
                contentColor = R.color.gray500,
                backBtnContent = getString(R.string.daily_routine_complete_yet),
                doBtnContent = getString(R.string.daily_routine_complete_fix),
                doBtnColor = R.drawable.shape_gray650_fill_12_rect,
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
                    resetRadioButtons()
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
                title = getString(R.string.daily_routine_edit_delete_question),
                content = getString(R.string.daily_routine_edit_delete_notice),
                isContentVisible = true,
                contentColor = R.color.red200,
                backBtnContent = getString(R.string.daily_routine_edit_delete_cancel),
                doBtnContent = getString(R.string.daily_routine_edit_delete_fix),
                doBtnColor = R.drawable.shape_red200_fill_12_rect,
                backBtnAction = {},
                doBtnAction = {
                    val arraySize = viewModel.editRoutineIdArray.size
                    viewModel.deleteDailyRoutine()
                    viewModel.isDailyRoutineDelete.observe(viewLifecycleOwner) {

                        val customSnackbar = CustomSnackbar.make(
                            (binding.root.rootView),
                            getString(R.string.daily_routine_edit_delete_num, arraySize),
                            (requireActivity() as MainActivity).findViewById(R.id.bottom_navigation_home)
                        )

                        customSnackbar.show(1000)

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
                val customSnackbar = CustomSnackbar.make(
                    binding.root.rootView,
                    getString(R.string.daily_routine_add_snack_bar),
                    (requireActivity() as MainActivity).findViewById(R.id.bottom_navigation_home)
                )
                customSnackbar.show(1000)
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
