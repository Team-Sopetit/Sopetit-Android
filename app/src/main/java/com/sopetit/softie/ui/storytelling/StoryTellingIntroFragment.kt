package com.sopetit.softie.ui.storytelling

import android.os.Bundle
import android.view.View
import com.sopetit.softie.R
import com.sopetit.softie.databinding.FragmentStoryTellingIntroBinding
import com.sopetit.softie.util.binding.BindingFragment
import com.sopetit.softie.util.setSingleOnClickListener

class StoryTellingIntroFragment :
    BindingFragment<FragmentStoryTellingIntroBinding>(R.layout.fragment_story_telling_intro) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initClickListener()
    }

    private fun initClickListener() {
        binding.root.setSingleOnClickListener {
            val storyTellingMainFragment = StoryTellingMainFragment()
            (activity as StoryTellingActivity).replaceFragment(storyTellingMainFragment)
        }
    }
}
