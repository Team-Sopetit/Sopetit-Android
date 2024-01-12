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
            changeBtnSelectState(btnDailyRoutineEditRadioEmptyFirst)
            changeBtnSelectState(btnDailyRoutineEditRadioEmptySecond)
            changeBtnSelectState(btnDailyRoutineEditRadioEmptyThird)
        }
    }

    private fun changeBtnSelectState(button: View) {
        button.setOnClickListener {
            it.isSelected = !it.isSelected
        }
    }
}
