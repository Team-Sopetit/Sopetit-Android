package com.sopetit.softie.ui.storytelling

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.sopetit.softie.R
import com.sopetit.softie.databinding.ActivityStoryTellingBinding
import com.sopetit.softie.util.binding.BindingActivity

class StoryTellingActivity :
    BindingActivity<ActivityStoryTellingBinding>(R.layout.activity_story_telling) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initFragment()
    }

    private fun initFragment() {
        val currentFragment =
            supportFragmentManager.findFragmentById(R.id.fcv_story_telling_fragment)
        if (currentFragment == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fcv_story_telling_fragment, StoryTellingIntroFragment())
                .commit()
        }
    }

    fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fcv_story_telling_fragment, fragment).commit()
    }
}
