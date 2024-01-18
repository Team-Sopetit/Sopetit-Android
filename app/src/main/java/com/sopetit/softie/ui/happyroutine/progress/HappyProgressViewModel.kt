package com.sopetit.softie.ui.happyroutine.progress

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopetit.softie.domain.entity.HappyProgress
import com.sopetit.softie.domain.usecase.GetHappyProgressUseCase
import com.sopetit.softie.domain.usecase.PatchMemberHappinessAchieveUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HappyProgressViewModel @Inject constructor(
    private val getHappyProgressUseCase: GetHappyProgressUseCase,
    private val patchMemberHappinessAchieveUseCase: PatchMemberHappinessAchieveUseCase
) : ViewModel() {
    private val _happyProgressResponse = MutableLiveData<HappyProgress>()
    val happyProgressResponse: LiveData<HappyProgress> get() = _happyProgressResponse

    fun getHappyProgress() {
        viewModelScope.launch {
            getHappyProgressUseCase()
                .onSuccess { response ->
                    _happyProgressResponse.value = response
                }
                .onFailure { throwable ->
                    Timber.e("$throwable")
                }
        }
    }

    private val _isHappyRoutineAchieve: MutableLiveData<Boolean> = MutableLiveData()
    val isHappyRoutineAchieve: LiveData<Boolean>
        get() = _isHappyRoutineAchieve

    private val _myRoutineId: MutableLiveData<Int> = MutableLiveData()
    val myRoutineId: LiveData<Int>
        get() = _myRoutineId

    fun patchAchieveHappyRoutine(routineId: Int) {
        viewModelScope.launch {
            patchMemberHappinessAchieveUseCase(routineId)
                .onSuccess { response ->
                    _isHappyRoutineAchieve.value
                }
                .onFailure { throwable ->
                    Timber.e("$throwable")
                }
        }
    }
}
