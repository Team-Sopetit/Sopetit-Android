package com.sopetit.softie.ui.dailyroutine

import android.os.Bundle
import com.sopetit.softie.R
import com.sopetit.softie.domain.entity.Bear
import com.sopetit.softie.domain.entity.Cotton
import com.sopetit.softie.util.RoutineCompleteActivity
import com.sopetit.softie.util.intentSerializable
import com.sopetit.softie.util.setStatusBarColorFromResource

class DailyRoutineCompleteActivity() : RoutineCompleteActivity(Cotton.DAILY) {
    private lateinit var bear: Bear
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStatusBarColorFromResource(R.color.white)

        getBearType()
        setDailyRoutineImage()
    }

    private fun getBearType() {
        bear = intent.intentSerializable("key", Bear::class.java) as Bear
    }

    private fun setDailyRoutineImage() {
        val bearDrawableMap = mapOf(
            Bear.BROWN to R.drawable.ic_bear_handsup_brown,
            Bear.GRAY to R.drawable.ic_bear_handsup_gray,
            Bear.RED to R.drawable.ic_bear_handsup_red,
            Bear.PANDA to R.drawable.ic_bear_handsup_panda
        )
        val resourceId = bearDrawableMap[bear] ?: R.drawable.ic_bear_handsup_brown

        binding.ivRoutineComplete.setImageResource(resourceId)
    }
}
