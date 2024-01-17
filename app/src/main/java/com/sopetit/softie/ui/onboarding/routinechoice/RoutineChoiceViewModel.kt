package com.sopetit.softie.ui.onboarding.routinechoice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopetit.softie.data.entity.request.PostMemberRequest
import com.sopetit.softie.domain.entity.Routine
import com.sopetit.softie.domain.usecase.InitSIgnUpStateUseCase
import com.sopetit.softie.domain.usecase.dailyroutine.GetRoutineListUseCase
import com.sopetit.softie.domain.usecase.member.PostMemberUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RoutineChoiceViewModel @Inject constructor(
    private val getRoutineListUseCase: GetRoutineListUseCase,
    private val postMemberUseCase: PostMemberUseCase,
    private val initSIgnUpStateUseCase: InitSIgnUpStateUseCase
) : ViewModel() {

    private val _routineList = MutableLiveData<List<Routine>>()
    val routineList: LiveData<List<Routine>>
        get() = _routineList

    private val _isNoticeVisible: MutableLiveData<Boolean> = MutableLiveData(false)
    val isNoticeVisible: LiveData<Boolean>
        get() = _isNoticeVisible

    private val _isRoutineBtnEnabled: MutableLiveData<Boolean> = MutableLiveData()
    val isRoutineBtnEnabled: LiveData<Boolean>
        get() = _isRoutineBtnEnabled

    private val _isPostNewMember: MutableLiveData<Boolean> = MutableLiveData()
    val isPostNewMember: LiveData<Boolean>
        get() = _isPostNewMember

    fun getRoutineList(themeId: List<Int>) {
        viewModelScope.launch {
            getRoutineListUseCase.invoke(themeId)
                .onSuccess { response ->
                    _routineList.value = response
                    Timber.e("서버 통신 성공 -> $response")
                }.onFailure { throwable ->
                    Timber.e("서버 통신 실패 -> ${throwable.message}")
                }
        }
    }

    fun setNoticeVisible(visible: Boolean) {
        _isNoticeVisible.value = visible
    }

    fun setRoutineBtnEnabled(isEnabled: Boolean) {
        _isRoutineBtnEnabled.value = isEnabled
    }

    fun postNewMember(dollType: String, name: String, routines: ArrayList<Int>) {
        viewModelScope.launch {
            postMemberUseCase.invoke(
                PostMemberRequest(
                    dollType, name, routines
                )
            ).onSuccess {
                _isPostNewMember.value = true
                initSIgnUpStateUseCase(true)
            }.onFailure { throwable ->
                _isPostNewMember.value = false
                initSIgnUpStateUseCase(false)
                Timber.e("서버 통신 실패 ${throwable.message}")
            }
        }
    }
}
