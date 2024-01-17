package com.sopetit.softie.ui.happyroutine

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import coil.load
import com.sopetit.softie.R
import com.sopetit.softie.databinding.FragmentHappyRoutineBinding
import com.sopetit.softie.ui.happyroutine.list.HappyAddListActivity
import com.sopetit.softie.util.binding.BindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HappyRoutineFragment :
    BindingFragment<FragmentHappyRoutineBinding>(R.layout.fragment_happy_routine) {

    private val viewModel by viewModels<HappyRoutineViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSetBearFace()
        setCardEnter()
    }

    private fun initSetBearFace() {
        viewModel.setDollFace()
        viewModel.bearFace.observe(viewLifecycleOwner) { bearFace ->
            binding.ivHappyRoutineCharacter.load(bearFace)
        }
    }

    private fun setCardEnter() {
        binding.ivHappyRoutineEmptyCard.setOnClickListener {
            val intent = Intent(requireContext(), HappyAddListActivity::class.java)
            startActivity(intent)
        }
    }
}
