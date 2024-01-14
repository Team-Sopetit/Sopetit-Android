package com.sopetit.softie.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopetit.softie.domain.entity.Cotton
import com.sopetit.softie.domain.entity.Home
import com.sopetit.softie.ui.main.home.HomeFragment.Companion.RUN_OUT

class HomeViewModel : ViewModel() {
    private val _homeResponse = MutableLiveData<Home>()
    val homeResponse: LiveData<Home> get() = _homeResponse

    fun getHome() {
        _homeResponse.value = Home(
            frameImageUrl = "https://d2v80xjmx68n4w.cloudfront.net/gigs/vGe751615194459.jpg",
            name = "애착이",
            dollType = "PANDA",
            dailyCottonCount = 5,
            happinessCottonCount = 3,
            conversations = listOf("야", "메롱", "루틴이나 해", "솔직히 나 귀엽지", "안드로이드 사랑해", "나 소프티야")
        )
    }

    fun patchSom(cotton: Cotton) {
        val isCottonRemain: (Int) -> Boolean = { cotton -> cotton > RUN_OUT }
        val cottonCount = when (cotton) {
            Cotton.DAILY -> homeResponse.value?.dailyCottonCount ?: RUN_OUT
            Cotton.HAPPINESS -> homeResponse.value?.happinessCottonCount ?: RUN_OUT
        }

        if (isCottonRemain(cottonCount)) {
            val changeData = when (cotton) {
                Cotton.DAILY -> homeResponse.value?.copy(dailyCottonCount = cottonCount - 1)
                Cotton.HAPPINESS -> homeResponse.value?.copy(happinessCottonCount = cottonCount - 1)
            }
            _homeResponse.value = changeData ?: homeResponse.value
        }
    }
}
