package com.sopetit.softie.ui.dailyroutine.dailyroutineedit

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.sopetit.softie.R
import com.sopetit.softie.databinding.ActivityDailyRoutineEditBinding
import com.sopetit.softie.ui.dailyroutine.DailyRoutineActivity
import com.sopetit.softie.ui.dailyroutine.DailyRoutineViewModel
import com.sopetit.softie.util.binding.BindingActivity

class DailyRoutineEditActivity :
    BindingActivity<ActivityDailyRoutineEditBinding>(R.layout.activity_daily_routine_edit) {
    private val dailyRoutineViewModel by viewModels<DailyRoutineViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.lifecycleOwner = this
        binding.dailyEmptyViewModel = dailyRoutineViewModel

        moveDaily()
        selectBtn()
    }

    private fun moveDaily() {
        binding.tvDailyRoutineEditCancel.setOnClickListener {
            val intent = Intent(this, DailyRoutineActivity::class.java)
            startActivity(intent)
        }
    }

    private fun selectBtn() {
        with(binding) {
            setButtonClickListener(btnDailyRoutineEditRadioEmpty)
            setButtonClickListener(btnDailyRoutineEditRadioEmptySecond)
            setButtonClickListener(btnDailyRoutineEditRadioEmptyThird)
        }
    }

    private fun setButtonClickListener(button: View) {
        button.setOnClickListener {
            it.isSelected = !it.isSelected
        }
    }
}
