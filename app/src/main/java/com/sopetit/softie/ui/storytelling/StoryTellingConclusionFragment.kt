package com.sopetit.softie.ui.storytelling

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.sopetit.softie.R
import com.sopetit.softie.databinding.FragmentStoryTellingConclusionBinding
import com.sopetit.softie.ui.onboarding.OnboardingActivity
import com.sopetit.softie.util.binding.BindingFragment

class StoryTellingConclusionFragment :
    BindingFragment<FragmentStoryTellingConclusionBinding>(R.layout.fragment_story_telling_conclusion) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListener()
    }

    private fun initClickListener() {
        binding.tvStoryTellingConclusion.setOnClickListener {
            val intent = Intent(requireActivity(), OnboardingActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
    }
}
