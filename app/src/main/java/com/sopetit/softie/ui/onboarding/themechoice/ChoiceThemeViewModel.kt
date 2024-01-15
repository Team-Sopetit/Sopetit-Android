package com.sopetit.softie.ui.onboarding.themechoice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopetit.softie.domain.entity.Theme
import com.sopetit.softie.domain.usecase.GetDollUseCase
import com.sopetit.softie.domain.usecase.GetThemeListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ChoiceThemeViewModel @Inject constructor(
    private val getDollUseCase: GetDollUseCase,
    private val getThemeListUseCase: GetThemeListUseCase
) : ViewModel() {

    private val _themeList = MutableLiveData<List<Theme>>()
    val themeList: LiveData<List<Theme>>
        get() = _themeList

    private val _themeBtnEnabled: MutableLiveData<Boolean> = MutableLiveData()
    val themeBtnEnabled: LiveData<Boolean>
        get() = _themeBtnEnabled

    private val _bearFace: MutableLiveData<String> = MutableLiveData()
    val bearFace: LiveData<String>
        get() = _bearFace

    fun getThemeList() {
        viewModelScope.launch {
            getThemeListUseCase.invoke()
                .onSuccess { response ->
                    _themeList.value = response
                    Timber.d("서버 통신 성공 -> ${response}")
                }.onFailure {
                    Timber.e("서버 통신 실패 -> ${it.message}")
                }
        }
    }

    fun getBearFace(faceUrl: String) {
        viewModelScope.launch {
            getDollUseCase.invoke(faceUrl)
                .onSuccess { response ->
                    _bearFace.value = response
                    Timber.d("서버 통신 성공 -> ${response}")
                }.onFailure {
                    Timber.e("서버 통신 실패 -> ${it.message}")
                }
        }
    }

    fun setThemeBtnEnabled(boolean: Boolean) {
        _themeBtnEnabled.value = boolean
    }
}
