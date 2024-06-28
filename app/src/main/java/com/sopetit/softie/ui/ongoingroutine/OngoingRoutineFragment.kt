package com.sopetit.softie.ui.ongoingroutine

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.sopetit.softie.R
import com.sopetit.softie.databinding.FragmentOngoingRoutineBinding
import com.sopetit.softie.util.binding.BindingFragment
import com.sopetit.softie.util.setStatusBarColor

class OngoingRoutineFragment:BindingFragment<FragmentOngoingRoutineBinding>(R.layout.fragment_ongoing_routine) {
    private val viewModel by viewModels<OngoingRoutineViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        setStatusBarColor(R.color.home_background)
    }
}
