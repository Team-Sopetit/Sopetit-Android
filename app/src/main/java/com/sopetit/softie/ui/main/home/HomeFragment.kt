package com.sopetit.softie.ui.main.home

import android.os.Bundle
import android.view.View
import com.sopetit.softie.R
import com.sopetit.softie.databinding.FragmentHomeBinding
import com.sopetit.softie.util.binding.BindingFragment
import com.sopetit.softie.util.setStatusBarColor

class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setStatusBarColor(R.color.home_background)
    }
}
