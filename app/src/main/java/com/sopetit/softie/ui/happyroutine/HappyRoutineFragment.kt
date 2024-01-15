package com.sopetit.softie.ui.happyroutine

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.sopetit.softie.R
import com.sopetit.softie.databinding.FragmentHappyRoutineBinding
import com.sopetit.softie.ui.happyroutine.addlist.HappyAddListActivity
import com.sopetit.softie.util.binding.BindingFragment

class HappyRoutineFragment :
    BindingFragment<FragmentHappyRoutineBinding>(R.layout.fragment_happy_routine) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setCardEnter()
    }

    private fun setCardEnter() {
        binding.ivHappyRoutineEmptyCard.setOnClickListener {
            val intent = Intent(requireContext(), HappyAddListActivity::class.java)
            startActivity(intent)
        }
    }
}
