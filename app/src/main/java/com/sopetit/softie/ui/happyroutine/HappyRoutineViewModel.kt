package com.sopetit.softie.ui.happyroutine

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopetit.softie.domain.usecase.doll.GetDollUseCase
import com.sopetit.softie.ui.onboarding.OnboardingViewModel.Companion.BROWN
import com.sopetit.softie.ui.onboarding.OnboardingViewModel.Companion.GRAY
import com.sopetit.softie.ui.onboarding.OnboardingViewModel.Companion.RED
import com.sopetit.softie.ui.onboarding.OnboardingViewModel.Companion.WHITE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HappyRoutineViewModel @Inject constructor(
    private val getDollUseCase: GetDollUseCase
) : ViewModel() {
    private val _bearFace: MutableLiveData<String> = MutableLiveData()
    val bearFace: LiveData<String>
        get() = _bearFace

    fun setDollFace(type: String) {
        viewModelScope.launch {
            when (type) {
                BROWN -> getDollUseCase.invoke(type)
                GRAY -> getDollUseCase.invoke(type)
                WHITE -> getDollUseCase.invoke(type)
                RED -> getDollUseCase.invoke(type)
                else -> getDollUseCase.invoke(BROWN)
            }.onSuccess { response ->
                _bearFace.value = response
                Timber.d("곰돌이 서버 통신 성공 -> $response")
            }.onFailure {
                Timber.e("곰돌이 서버 통신 실패 -> ${it.message}")
            }
        }
    }
}
