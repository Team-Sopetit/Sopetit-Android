package com.sopetit.softie.ui.happyroutine.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopetit.softie.data.entity.request.PostMemberHappyRoutineRequest
import com.sopetit.softie.domain.entity.HappyCard
import com.sopetit.softie.domain.usecase.happyroutine.GetHappyCardUseCase
import com.sopetit.softie.domain.usecase.happyroutine.PostMemberHappyRoutineUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HappyDetailCardViewModel @Inject constructor(
    private val getHappyCardUseCase: GetHappyCardUseCase,
    private val postMemberHappyRoutineUseCase: PostMemberHappyRoutineUseCase
) : ViewModel() {
    private val _happyCardResponse = MutableLiveData<HappyCard>()
    val happyCardResponse: LiveData<HappyCard> get() = _happyCardResponse

    private val _isPostAddRoutine: MutableLiveData<Boolean> = MutableLiveData()
    val isPostAddRoutine: LiveData<Boolean>
        get() = _isPostAddRoutine

    private val _mySubroutineId: MutableLiveData<Int> = MutableLiveData()
    val mySubroutineId: LiveData<Int>
        get() = _mySubroutineId

    fun getHappyCard(routineId: String) {
        viewModelScope.launch {
            getHappyCardUseCase(routineId)
                .onSuccess { response ->
                    _happyCardResponse.value = response
                }
                .onFailure { throwable ->
                    Timber.e("$throwable")
                }
        }
    }

    fun postAddRoutine(subRoutineId: Int) {
        viewModelScope.launch {
            postMemberHappyRoutineUseCase.invoke(
                PostMemberHappyRoutineRequest(
                    subRoutineId
                )
            ).onSuccess {
                _isPostAddRoutine.value = true
            }.onFailure { throwable ->
                _isPostAddRoutine.value = false
                Timber.e("서버 통신 실패 ${throwable.message}")
            }
        }
    }

    fun setSubRoutineId(subRoutineId: Int) {
        _mySubroutineId.value = subRoutineId
    }
}
