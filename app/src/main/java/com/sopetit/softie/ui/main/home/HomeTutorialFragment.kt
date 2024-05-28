package com.sopetit.softie.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sopetit.softie.R
import com.sopetit.softie.databinding.FragmentHomeTutorialBottomsheetBinding
import com.sopetit.softie.domain.entity.Tutorial

class HomeTutorialFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentHomeTutorialBottomsheetBinding? = null
    private val binding get() = requireNotNull(_binding)
    private lateinit var viewPager: ViewPager2
    private lateinit var homeTutorialAdapter: HomeTutorialAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeTutorialBottomsheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager = binding.vpTutorial
        homeTutorialAdapter = HomeTutorialAdapter()
        binding.vpTutorial.adapter = homeTutorialAdapter
        homeTutorialAdapter.submitList(
            listOf(
                Tutorial(0, R.drawable.tutorial_daily),
                Tutorial(1, R.drawable.tutorial_challenge),
                Tutorial(2, R.drawable.tutorial_cotton)
            )
        )
    }

}
