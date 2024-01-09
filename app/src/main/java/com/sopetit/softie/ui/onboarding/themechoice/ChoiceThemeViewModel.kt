package com.sopetit.softie.ui.onboarding.themechoice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopetit.softie.domain.entity.Theme

class ChoiceThemeViewModel : ViewModel() {

    private val _mockThemeList: MutableLiveData<List<Theme>> = MutableLiveData(
        mutableListOf(
            Theme(
                1,
                "하루의 시작",
                "",
                ""
            ),
            Theme(
                2,
                "건강한 몸",
                "",
                ""
            ),
            Theme(
                3,
                "무기력 극복",
                "",
                ""
            ),
            Theme(
                4,
                "편안한 잠",
                "",
                ""
            ),
            Theme(
                5,
                "환경지킴이",
                "",
                ""
            ),
            Theme(
                6,
                "소소한 친절",
                "",
                ""
            ),
            Theme(
                7,
                "감사의 마음",
                "",
                ""
            ),
            Theme(
                8,
                "작은 성취감",
                "",
                ""
            ),
            Theme(
                9,
                "소중한 나",
                "",
                ""
            ),
            Theme(
                10,
                "통통한 통장",
                "",
                ""
            ),
            Theme(
                11,
                "몰입할 준비",
                "",
                ""
            ),
            Theme(
                12,
                "비워내기",
                "",
                ""
            )

        )
    )

    val mockThemeList: LiveData<List<Theme>>
        get() = _mockThemeList

    private val _themeBtnEnabled: MutableLiveData<Boolean> = MutableLiveData()
    val themeBtnEnabled: LiveData<Boolean>
        get() = _themeBtnEnabled

    private val _selectedThemeArray: MutableLiveData<ArrayList<Int>> = MutableLiveData()
    val selectedThemeArray: LiveData<ArrayList<Int>>
        get() = _selectedThemeArray

    fun setThemeBtnEnabled(boolean: Boolean) {
        _themeBtnEnabled.value = boolean
    }

    fun setSelectedThemeArray(themeArray: ArrayList<Int>) {
        _selectedThemeArray.value = themeArray
    }
}
