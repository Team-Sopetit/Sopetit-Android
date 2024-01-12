package com.sopetit.softie.ui.dailyroutine

import android.os.Bundle
import android.view.View
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

        initSetDeleteView()
    }

    private fun initSetDeleteView() {
        binding.tvDailyRoutineEdit.setOnClickListener {
            viewModel.setDeleteView(true)
        }
    }
}
