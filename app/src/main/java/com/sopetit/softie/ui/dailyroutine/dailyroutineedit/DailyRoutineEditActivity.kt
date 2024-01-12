package com.sopetit.softie.ui.dailyroutine.dailyroutineedit

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.sopetit.softie.R
import com.sopetit.softie.databinding.ActivityDailyRoutineEditBinding
import com.sopetit.softie.util.binding.BindingActivity

class DailyRoutineEditActivity :
    BindingActivity<ActivityDailyRoutineEditBinding>(R.layout.activity_daily_routine_edit) {
    private val viewModel by viewModels<DailyRoutineEditViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewModel = viewModel

        setCancelBtnClickListener()
        clickEditRadioBtn()
    }

    private fun setCancelBtnClickListener() {
        binding.tvDailyRoutineEditCancel.setOnClickListener {
            finish()
        }
    }

    private fun clickEditRadioBtn() {
        with(binding) {
            changeBtnSelectState(btnDailyRoutineEditRadioEmptyFirst, 1)
            changeBtnSelectState(btnDailyRoutineEditRadioEmptySecond, 2)
            changeBtnSelectState(btnDailyRoutineEditRadioEmptyThird, 3)
        }
    }

    private fun changeBtnSelectState(button: View, itemId: Int) {
        button.setOnClickListener {
            it.isSelected = !it.isSelected
        }
    }

    private fun initSetEditBtnEnabled() {
        //
    }
}
