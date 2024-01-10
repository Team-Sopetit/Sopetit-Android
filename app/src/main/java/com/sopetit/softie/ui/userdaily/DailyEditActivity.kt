package com.sopetit.softie.ui.userdaily

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.sopetit.softie.R
import com.sopetit.softie.databinding.ActivityDailyEditBinding
import com.sopetit.softie.util.binding.BindingActivity

class DailyEditActivity : BindingActivity<ActivityDailyEditBinding>(R.layout.activity_daily_edit) {
    private val dailyEmptyViewModel by viewModels<DailyEmptyViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.lifecycleOwner = this
        binding.dailyEmptyViewModel = dailyEmptyViewModel

        moveDaily()
        selectBtn()
    }

    private fun moveDaily() {
        binding.tvCancel.setOnClickListener {
            val intent = Intent(this, DailyEmptyActivity::class.java)
            startActivity(intent)
        }
    }

    private fun selectBtn() {
        with(binding) {
            setButtonClickListener(btnRadioEmpty)
            setButtonClickListener(btnRadioEmptySecond)
            setButtonClickListener(btnRadioEmptyThird)
        }
    }

    private fun setButtonClickListener(button: View) {
        button.setOnClickListener {
            it.isSelected = !it.isSelected
        }
    }
}
