package com.sopetit.softie.ui.userdaily

import android.content.Intent
import android.os.Bundle
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

    private var isBtnSelected = false

    private fun selectBtn() {
        with(binding) {
            btnRadioEmpty.setOnClickListener {
                isBtnSelected = !isBtnSelected
                btnRadioEmpty.isSelected = isBtnSelected
            }
            btnRadioEmptySecond.setOnClickListener {
                isBtnSelected = !isBtnSelected
                btnRadioEmptySecond.isSelected = isBtnSelected
            }
            btnRadioEmptyThird.setOnClickListener {
                isBtnSelected = !isBtnSelected
                btnRadioEmptyThird.isSelected = isBtnSelected
            }
        }
    }
}
