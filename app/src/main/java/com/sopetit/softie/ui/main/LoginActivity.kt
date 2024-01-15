package com.sopetit.softie.ui.main

import android.content.Intent
import android.os.Bundle
import com.sopetit.softie.R
import com.sopetit.softie.databinding.ActivityLoginBinding
import com.sopetit.softie.ui.storytelling.StoryTellingActivity
import com.sopetit.softie.util.binding.BindingActivity
import com.sopetit.softie.util.setStatusBarColorFromResource

class LoginActivity : BindingActivity<ActivityLoginBinding>(R.layout.activity_login) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStatusBarColorFromResource(R.color.background)

        initSetLogInBtn()
    }

    private fun initSetLogInBtn() {
        val intent = Intent(this, StoryTellingActivity::class.java)
        binding.btnLoginKakao.setOnClickListener {
            startActivity(intent)
            finish()
        }
    }
}
