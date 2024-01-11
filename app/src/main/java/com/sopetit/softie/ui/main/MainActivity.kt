package com.sopetit.softie.ui.main

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.sopetit.softie.R
import com.sopetit.softie.databinding.ActivityMainBinding
import com.sopetit.softie.ui.main.happy.HappyRoutineFragment
import com.sopetit.softie.ui.main.home.HomeFragment
import com.sopetit.softie.util.binding.BindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initFragment()
        setBottomNavigationTint()

        binding.bnvMain.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_navigation_progress -> {
                    // changeFragment() 진행중 화면 띄우기
                    true
                }

                R.id.bottom_navigation_home -> {
                    changeFragment(HomeFragment())
                    true
                }

                R.id.bottom_navigation_happiness_routine -> {
                    changeFragment(HappyRoutineFragment())
                    true
                }

                else -> {
                    true
                }
            }
        }
    }

    private fun initFragment() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fcv_main)
        if (currentFragment == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fcv_main, HomeFragment())
                .commit()
        }
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_main, fragment)
            .commit()
    }

    private fun setBottomNavigationTint() {
        binding.bnvMain.itemIconTintList = null

        val selectedTextColor = ContextCompat.getColor(this, R.color.main1)
        val unselectedTextColor = ContextCompat.getColor(this, R.color.gray200)

        binding.bnvMain.itemTextColor = ColorStateList(
            arrayOf(
                intArrayOf(android.R.attr.state_checked),
                intArrayOf(-android.R.attr.state_checked)
            ),
            intArrayOf(selectedTextColor, unselectedTextColor)
        )
    }
}
