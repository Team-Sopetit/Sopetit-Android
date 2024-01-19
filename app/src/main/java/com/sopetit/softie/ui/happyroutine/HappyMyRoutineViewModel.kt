package com.sopetit.softie.ui.happyroutine

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopetit.softie.domain.entity.HappyProgress
import com.sopetit.softie.domain.usecase.DeleteMemberHappyRoutineUseCase
import com.sopetit.softie.domain.usecase.GetBearTypeUseCase
import com.sopetit.softie.domain.usecase.doll.GetDollUseCase
import com.sopetit.softie.domain.usecase.happyroutine.GetHappyProgressUseCase
import com.sopetit.softie.domain.usecase.happyroutine.PatchMemberHappinessAchieveUseCase
import com.sopetit.softie.ui.onboarding.OnboardingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HappyMyRoutineViewModel @Inject constructor(
    private val getDollUseCase: GetDollUseCase,
    private val getBearTypeUseCase: GetBearTypeUseCase,
    private val getHappyProgressUseCase: GetHappyProgressUseCase,
    private val patchMemberHappinessAchieveUseCase: PatchMemberHappinessAchieveUseCase,
    private val deleteMemberHappyRoutineUseCase: DeleteMemberHappyRoutineUseCase
) : ViewModel() {
    private val _bearFace: MutableLiveData<String> = MutableLiveData()
    val bearFace: LiveData<String>
        get() = _bearFace

    private val _happyProgressResponse = MutableLiveData<HappyProgress>()
    val happyProgressResponse: LiveData<HappyProgress> get() = _happyProgressResponse

    private val _isDeleteHappyCardResponse: MutableLiveData<Boolean> = MutableLiveData()
    private val _isHappinessRoutineProgress: MutableLiveData<Boolean> = MutableLiveData(false)
    val isHappinessRoutineProgress: LiveData<Boolean> get() = _isHappinessRoutineProgress
    fun setDollFace() {
        viewModelScope.launch {
            when (getBearTypeUseCase()) {
                OnboardingViewModel.BROWN -> getDollUseCase(OnboardingViewModel.BROWN)
                OnboardingViewModel.GRAY -> getDollUseCase(OnboardingViewModel.GRAY)
                OnboardingViewModel.WHITE -> getDollUseCase(OnboardingViewModel.WHITE)
                OnboardingViewModel.RED -> getDollUseCase(OnboardingViewModel.RED)
                else -> getDollUseCase(OnboardingViewModel.BROWN)
            }.onSuccess { response ->
                _bearFace.value = response
                Timber.d("곰돌이 서버 통신 성공 -> $response")
            }.onFailure {
                Timber.e("곰돌이 서버 통신 실패 -> ${it.message}")
            }
        }
    }

    fun getHappyProgress() {
        viewModelScope.launch {
            getHappyProgressUseCase()
                .onSuccess { response ->
                    _isHappinessRoutineProgress.value = true
                    _happyProgressResponse.value = response
                }
                .onFailure { throwable ->
                    _isHappinessRoutineProgress.value = false
                    Timber.e("$throwable")
                }
        }
    }

    fun patchAchieveHappyRoutine(routineId: Int) {
        viewModelScope.launch {
            patchMemberHappinessAchieveUseCase(routineId)
                .onSuccess { response ->
                    Timber.e("$response")
                }
                .onFailure { throwable ->
                    Timber.e("$throwable")
                }
        }
    }

    fun deleteHappyProgress(routineId: Int) {
        viewModelScope.launch {
            deleteMemberHappyRoutineUseCase(routineId)
                .onSuccess {
                    _isDeleteHappyCardResponse.value = true
                }.onFailure { throwable ->
                    _isDeleteHappyCardResponse.value = false
                    Timber.e("서버 통신 실패 -> ${throwable.message}")
                }
        }
    }
}
