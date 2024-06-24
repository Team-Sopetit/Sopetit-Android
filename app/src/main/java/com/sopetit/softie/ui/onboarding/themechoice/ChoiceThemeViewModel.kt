package com.sopetit.softie.ui.onboarding.themechoice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopetit.softie.domain.entity.Theme
import com.sopetit.softie.domain.usecase.dailyroutine.GetThemeListUseCase
import com.sopetit.softie.domain.usecase.doll.GetDollUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChoiceThemeViewModel @Inject constructor(
    private val getDollUseCase: GetDollUseCase,
    private val getThemeListUseCase: GetThemeListUseCase
) : ViewModel() {

    //    private val _themeList = MutableLiveData<List<Theme>>()
    private val _themeList: MutableLiveData<List<Theme>> =
        MutableLiveData(
            listOf(
                Theme(1, "", "좋은 관계 만들기", ""),
                Theme(2, "", "마음 챙기기", ""),
                Theme(3, "", "통통한 통장 만들기", ""),
                Theme(4, "", "산뜻한 일상 만들기", ""),
                Theme(5, "", "한 걸음 성장하기", ""),
                Theme(6, "", "건강한 몸 만들기", ""),
                Theme(7, "", "나와 친해지기", ""),
            )
        )
    val themeList: LiveData<List<Theme>>
        get() = _themeList

    private val _themeBtnEnabled: MutableLiveData<Boolean> = MutableLiveData()
    val themeBtnEnabled: LiveData<Boolean>
        get() = _themeBtnEnabled

    // TODO 서버 연결 시 재작업 예정
//    fun getThemeList() {
//        viewModelScope.launch {
//            getThemeListUseCase.invoke()
//                .onSuccess { response ->
//                    _themeList.value = response
//                    Timber.d("서버 통신 성공 -> $response")
//                }.onFailure {
//                    Timber.e("서버 통신 실패 -> ${it.message}")
//                }
//        }
//    }

    fun setThemeBtnEnabled(boolean: Boolean) {
        _themeBtnEnabled.value = boolean
    }
}
