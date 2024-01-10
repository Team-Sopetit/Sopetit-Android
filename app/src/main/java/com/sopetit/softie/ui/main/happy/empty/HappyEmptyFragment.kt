package com.sopetit.softie.ui.main.happy.empty

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sopetit.softie.databinding.FragmentHappyEmptyBinding

class HappyEmptyFragment : Fragment() {
    private var _binding: FragmentHappyEmptyBinding? = null
    private val binding: FragmentHappyEmptyBinding
        get() = requireNotNull(_binding) { "바인딩 객체가 생성되지 않았습니다" }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHappyEmptyBinding.inflate(inflater, container, false)
        return binding.root
    }
}
