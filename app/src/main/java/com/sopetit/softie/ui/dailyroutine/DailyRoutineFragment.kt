package com.sopetit.softie.ui.dailyroutine

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.sopetit.softie.R
import com.sopetit.softie.databinding.FragmentDailyRoutineBinding
import com.sopetit.softie.util.binding.BindingFragment

class DailyRoutineFragment :
    BindingFragment<FragmentDailyRoutineBinding>(R.layout.fragment_daily_routine) {

    private val viewModel by viewModels<DailyRoutineViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel

        initSetDailyRoutineContent()
        initSetDeleteView()
    }

    private fun initSetDailyRoutineContent() {
        with(binding) {
            routineItemView(tvDailyRoutineAddNameFirst, tvDailyRoutineIngFirst, 0)
            routineItemView(tvDailyRoutineAddNameSecond, tvDailyRoutineIngSecond, 1)
            routineItemView(tvDailyRoutineAddNameThird, tvDailyRoutineIngThird, 2)
        }
    }

    private fun routineItemView(routineTitle: TextView, achieveMsg: TextView, index: Int) {
        viewModel.mockDailyRoutineList.observe(viewLifecycleOwner) { dailyRoutineList ->
            val achieveCountMsg =
                getString(R.string.daily_routine_ing).format(dailyRoutineList[index].achieveCount)
            achieveMsg.text = achieveCountMsg
            routineTitle.text = dailyRoutineList[index].content
        }
    }

    private fun initSetDeleteView() {
        viewModel.isDeleteView.observe(viewLifecycleOwner) { isDeleteView ->
            if (!isDeleteView) {
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
}
