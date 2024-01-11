package com.sopetit.softie.ui.dailyroutine

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.sopetit.softie.R
import com.sopetit.softie.databinding.FragmentDailyEmptyBinding
import com.sopetit.softie.ui.dailyroutine.dailyedit.DailyEditActivity
import com.sopetit.softie.util.binding.BindingFragment

class DailyRoutineFragment :
    BindingFragment<FragmentDailyEmptyBinding>(R.layout.fragment_daily_routine) {

    private val dailyRoutineViewModel by viewModels<DailyRoutineViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this
        binding.dailyEmptyViewModel = dailyRoutineViewModel

        moveEdit()
    }

    private fun moveEdit() {
        binding.tvEdit.setOnClickListener {
            val intent = Intent(requireActivity(), DailyEditActivity::class.java)
            startActivity(intent)
        }
    }
}
