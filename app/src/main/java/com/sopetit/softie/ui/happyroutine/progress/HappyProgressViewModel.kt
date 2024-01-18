package com.sopetit.softie.ui.happyroutine.progress

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopetit.softie.domain.entity.HappyProgress
import com.sopetit.softie.domain.usecase.DeleteMemberHappyRoutineUseCase
import com.sopetit.softie.domain.usecase.GetHappyProgressUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HappyProgressViewModel @Inject constructor(
    private val getHappyProgressUseCase: GetHappyProgressUseCase,
    private val deleteMemberHappyRoutineUseCase: DeleteMemberHappyRoutineUseCase
) : ViewModel() {
    private val _happyProgressResponse = MutableLiveData<HappyProgress>()
    val happyProgressResponse: LiveData<HappyProgress> get() = _happyProgressResponse

    private val _isDeleteHappyCardResponse: MutableLiveData<Boolean> = MutableLiveData()
    val isDeleteHappyCardResponse: LiveData<Boolean>
        get() = _isDeleteHappyCardResponse

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
