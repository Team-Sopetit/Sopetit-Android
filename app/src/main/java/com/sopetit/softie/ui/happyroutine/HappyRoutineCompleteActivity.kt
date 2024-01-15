package com.sopetit.softie.ui.happyroutine

import android.os.Bundle
import com.sopetit.softie.R
import com.sopetit.softie.domain.entity.Bear
import com.sopetit.softie.domain.entity.Cotton
import com.sopetit.softie.util.RoutineCompleteActivity
import com.sopetit.softie.util.intentSerializable
import com.sopetit.softie.util.setStatusBarColorFromResource

class HappyRoutineCompleteActivity : RoutineCompleteActivity(Cotton.HAPPINESS) {
    lateinit var bear: Bear
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStatusBarColorFromResource(R.color.white)

        getBearType()
        setHappyRoutineImage()
    }

    private fun getBearType() {
        bear = intent.intentSerializable("key", Bear::class.java) as Bear
    }

    private fun setHappyRoutineImage() {
        val bearDrawableMap = mapOf(
            Bear.BROWN to R.drawable.ic_bear_happy_complete_brown,
            Bear.GRAY to R.drawable.ic_bear_happy_complete_gray,
            Bear.RED to R.drawable.ic_bear_happy_complete_red,
            Bear.PANDA to R.drawable.ic_bear_happy_complete_panda
        )
        val resourceId = bearDrawableMap[bear] ?: R.drawable.ic_bear_happy_complete_brown

        binding.ivRoutineComplete.setImageResource(resourceId)
    }
}
