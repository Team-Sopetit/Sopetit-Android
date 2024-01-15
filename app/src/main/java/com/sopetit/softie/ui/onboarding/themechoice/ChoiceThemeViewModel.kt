package com.sopetit.softie.ui.onboarding.themechoice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopetit.softie.domain.entity.Theme
import com.sopetit.softie.domain.usecase.GetThemeListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ChoiceThemeViewModel @Inject constructor(
    private val getThemeListUseCase: GetThemeListUseCase
) : ViewModel() {

//    private val _mockThemeList: MutableLiveData<List<Theme>> = MutableLiveData(
//        mutableListOf(
//            Theme(
//                1,
//                "하루의 시작",
//                "",
//                ""
//            ),
//            Theme(
//                2,
//                "건강한 몸",
//                "",
//                ""
//            ),
//            Theme(
//                3,
//                "무기력 극복",
//                "",
//                ""
//            ),
//            Theme(
//                4,
//                "편안한 잠",
//                "",
//                ""
//            ),
//            Theme(
//                5,
//                "환경지킴이",
//                "",
//                ""
//            ),
//            Theme(
//                6,
//                "소소한 친절",
//                "",
//                ""
//            ),
//            Theme(
//                7,
//                "감사의 마음",
//                "",
//                ""
//            ),
//            Theme(
//                8,
//                "작은 성취감",
//                "",
//                ""
//            ),
//            Theme(
//                9,
//                "소중한 나",
//                "",
//                ""
//            ),
//            Theme(
//                10,
//                "통통한 통장",
//                "",
//                ""
//            ),
//            Theme(
//                11,
//                "몰입할 준비",
//                "",
//                ""
//            ),
//            Theme(
//                12,
//                "비워내기",
//                "",
//                ""
//            )
//
//        )
//    )
//
//    val mockThemeList: LiveData<List<Theme>>
//        get() = _mockThemeList

    private val _themeList = MutableLiveData<List<Theme>>()
    val themeList: LiveData<List<Theme>>
        get() = _themeList

    private val _themeBtnEnabled: MutableLiveData<Boolean> = MutableLiveData()
    val themeBtnEnabled: LiveData<Boolean>
        get() = _themeBtnEnabled

    fun getThemeList() {
        viewModelScope.launch {
            getThemeListUseCase.invoke()
                .onSuccess { response ->
                    _themeList.value = response
                }.onFailure {
                    Timber.e("서버 통신 실패 -> ${it.message}")
                }
        }
    }

    fun setThemeBtnEnabled(boolean: Boolean) {
        _themeBtnEnabled.value = boolean
    }
}
