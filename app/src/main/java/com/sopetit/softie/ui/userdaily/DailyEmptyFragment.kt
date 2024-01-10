package com.sopetit.softie.ui.userdaily

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.sopetit.softie.R
import com.sopetit.softie.databinding.FragmentDailyEmptyBinding
import com.sopetit.softie.util.binding.BindingFragment

class DailyEmptyFragment :
    BindingFragment<FragmentDailyEmptyBinding>(R.layout.fragment_daily_empty) {

    private val dailyEmptyViewModel by viewModels<DailyEmptyViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this
        binding.dailyEmptyViewModel = dailyEmptyViewModel

        moveEdit()
    }

    private fun moveEdit() {
        binding.tvEdit.setOnClickListener {
            val intent = Intent(requireActivity(), DailyEditActivity::class.java)
            startActivity(intent)
        }
    }
}
