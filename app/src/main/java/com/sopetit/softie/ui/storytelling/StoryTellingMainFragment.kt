package com.sopetit.softie.ui.storytelling

import android.os.Bundle
import android.view.View
import com.sopetit.softie.R
import com.sopetit.softie.databinding.FragmentStoryTellingMainBinding
import com.sopetit.softie.util.binding.BindingFragment
import com.sopetit.softie.util.setSingleOnClickListener

class StoryTellingMainFragment :
    BindingFragment<FragmentStoryTellingMainBinding>(R.layout.fragment_story_telling_main) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListener()
    }

    private fun initClickListener() {
        binding.root.setSingleOnClickListener {
            val storyTellingConclusionFragment = StoryTellingConclusionFragment()
            (activity as StoryTellingActivity).replaceFragment(storyTellingConclusionFragment)
        }
    }
}
