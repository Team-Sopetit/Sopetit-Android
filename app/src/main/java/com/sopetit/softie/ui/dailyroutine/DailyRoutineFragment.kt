package com.sopetit.softie.ui.dailyroutine

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.sopetit.softie.R
import com.sopetit.softie.databinding.FragmentDailyRoutineBinding
import com.sopetit.softie.domain.entity.Bear
import com.sopetit.softie.ui.dailyroutine.dailyroutineedit.DailyRoutineEditActivity
import com.sopetit.softie.util.binding.BindingFragment
import com.sopetit.softie.util.setStatusBarColor

class DailyRoutineFragment :
    BindingFragment<FragmentDailyRoutineBinding>(R.layout.fragment_daily_routine) {

    private val dailyRoutineViewModel by viewModels<DailyRoutineViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setStatusBarColor(R.color.background)

        binding.lifecycleOwner = this
        binding.dailyRoutineViewModel = dailyRoutineViewModel

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

    private fun moveEdit() {
        binding.tvDailyRoutineEdit.setOnClickListener {
            val intent = Intent(requireActivity(), DailyRoutineEditActivity::class.java)
            startActivity(intent)
        }
    }
}
