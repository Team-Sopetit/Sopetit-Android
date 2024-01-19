package com.sopetit.softie.ui.main

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.sopetit.softie.R
import com.sopetit.softie.databinding.ActivityMainBinding
import com.sopetit.softie.ui.dailyroutine.DailyRoutineFragment
import com.sopetit.softie.ui.happyroutine.HappyMyRoutineViewModel
import com.sopetit.softie.ui.happyroutine.progress.HappyMyRoutineFragment
import com.sopetit.softie.ui.main.home.HomeFragment
import com.sopetit.softie.util.binding.BindingActivity
import com.sopetit.softie.util.setStatusBarColorFromResource
import com.sopetit.softie.util.snackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val viewModel by viewModels<HappyMyRoutineViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStatusBarColorFromResource(R.color.background)

        initFragment()
        initBottomNavigation()
        setBottomNavigationClickListener()
        changeToHappyProgressFragment()
    }

    private fun changeToHappyProgressFragment() {
        val fragmentToLoad = intent.getStringExtra("happy_progress_fragment")
        if (fragmentToLoad == "happy_progress") {
            loadHappyProgressFragment()
        }
    }

    private fun setBottomNavigationClickListener() {
        binding.bnvMain.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_navigation_daily_routine -> changeFragment(DailyRoutineFragment())
                R.id.bottom_navigation_home -> changeFragment(HomeFragment())
                R.id.bottom_navigation_happiness_routine -> changeFragment(HappyMyRoutineFragment())
                else -> true
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

    private fun changeFragment(fragment: Fragment): Boolean {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_main, fragment)
            .commit()
        return true
    }

    private fun initBottomNavigation() {
        binding.bnvMain.selectedItemId = R.id.bottom_navigation_home
        setBottomNavigationTint()
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

    private fun loadHappyProgressFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        snackBar(
            binding.btnMainAnchor,
            getString(R.string.happy_routine_add_snack_bar)
        )
        setStatusBarColorFromResource(R.color.background)
        binding.bnvMain.selectedItemId = R.id.bottom_navigation_happiness_routine
        fragmentTransaction.replace(R.id.fcv_main, HappyMyRoutineFragment())
        fragmentTransaction.commit()
    }
}
