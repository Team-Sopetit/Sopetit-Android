package com.sopetit.softie.ui.happyroutine

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import coil.load
import com.sopetit.softie.R
import com.sopetit.softie.databinding.FragmentHappyRoutineBinding
import com.sopetit.softie.ui.happyroutine.list.HappyAddListActivity
import com.sopetit.softie.ui.onboarding.OnboardingViewModel.Companion.BROWN
import com.sopetit.softie.util.binding.BindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HappyRoutineFragment :
    BindingFragment<FragmentHappyRoutineBinding>(R.layout.fragment_happy_routine) {

    private val viewModel by viewModels<HappyRoutineViewModel>()
    lateinit var sharedPreferences: SharedPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences =
            this.requireActivity().getSharedPreferences("user", Context.MODE_PRIVATE)
        val bearType = sharedPreferences.getString("bearType", BROWN)

        initSetBearFace(bearType)
        setCardEnter()
    }

    private fun initSetBearFace(dollType: String?) {
        if (dollType != null) {
            viewModel.setDollFace(dollType)
            viewModel.bearFace.observe(viewLifecycleOwner) { bearFace ->
                binding.ivHappyRoutineCharacter.load(bearFace)
            }
        }
    }

    private fun setCardEnter() {
        binding.ivHappyRoutineEmptyCard.setOnClickListener {
            val intent = Intent(requireContext(), HappyAddListActivity::class.java)
            startActivity(intent)
        }
    }
}
