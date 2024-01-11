package com.sopetit.softie.ui.dailyroutine

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.sopetit.softie.R
import com.sopetit.softie.databinding.FragmentDailyRoutineBinding
import com.sopetit.softie.ui.dailyroutine.dailyroutineedit.DailyRoutineEditActivity
import com.sopetit.softie.util.binding.BindingFragment

class DailyRoutineFragment :
    BindingFragment<FragmentDailyRoutineBinding>(R.layout.fragment_daily_routine) {

    private val dailyRoutineViewModel by viewModels<DailyRoutineViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this
        binding.dailyEmptyViewModel = dailyRoutineViewModel

        moveEdit()
    }

    private fun moveEdit() {
        binding.tvDailyRoutineEdit.setOnClickListener {
            val intent = Intent(requireActivity(), DailyRoutineEditActivity::class.java)
            startActivity(intent)
        }
    }
}
