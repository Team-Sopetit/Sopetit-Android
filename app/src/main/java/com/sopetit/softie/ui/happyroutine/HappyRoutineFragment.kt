package com.sopetit.softie.ui.happyroutine

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.sopetit.softie.R
import com.sopetit.softie.databinding.FragmentHappyRoutineBinding
import com.sopetit.softie.ui.happyroutine.list.HappyAddListActivity
import com.sopetit.softie.util.binding.BindingFragment
import com.sopetit.softie.util.setStatusBarColor

class HappyRoutineFragment :
    BindingFragment<FragmentHappyRoutineBinding>(R.layout.fragment_happy_routine) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setStatusBarColor(R.color.background)

        setClickEmptyCardListener()
    }

    private fun setClickEmptyCardListener() {
        binding.clHappyRoutineEmptyCard.setOnClickListener {
            val intentToHappyAddList = Intent(activity, HappyAddListActivity::class.java)
            startActivity(intentToHappyAddList)
        }
    }
}
