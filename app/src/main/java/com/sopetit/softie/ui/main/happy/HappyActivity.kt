package com.sopetit.softie.ui.main.happy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sopetit.softie.R
import com.sopetit.softie.databinding.ActivityHappyBinding
import com.sopetit.softie.ui.main.happy.empty.HappyEmptyFragment

class HappyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHappyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHappyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fcv_happy)
        if (currentFragment == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fcv_happy, HappyEmptyFragment())
                .commit()
        }
    }
}
