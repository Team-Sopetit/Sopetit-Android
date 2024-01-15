package com.sopetit.softie.ui.happyroutine

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.sopetit.softie.R
import com.sopetit.softie.databinding.FragmentHappyRoutineBinding
import com.sopetit.softie.ui.happyroutine.addlist.HappyAddListActivity
import com.sopetit.softie.util.binding.BindingFragment

class HappyRoutineFragment :
    BindingFragment<FragmentHappyRoutineBinding>(R.layout.fragment_happy_routine) {

    private val viewModel by viewModels<HappyRoutineViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dollType = "BROWN"

        setDollBinding(dollType)
        setCardEnter()
    }

    private fun setDollBinding(dollType: String?) {
        if (dollType != null) {
            val faceImageUrl = viewModel.getFaceImageUrl(dollType)
            binding.ivHappyRoutineCharacter.setImageResource(faceImageUrl)
        } else {
        }
    }

    private fun setCardEnter() {
        binding.ivHappyRoutineEmptyCard.setOnClickListener {
            val intent = Intent(requireContext(), HappyAddListActivity::class.java)
            startActivity(intent)
        }
    }
}

