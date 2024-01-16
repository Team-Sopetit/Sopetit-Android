package com.sopetit.softie.ui.onboarding.routinechoice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopetit.softie.domain.entity.Routine
import com.sopetit.softie.domain.usecase.dailyroutine.GetRoutineListUseCase
import com.sopetit.softie.domain.usecase.member.PostMemberUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RoutineChoiceViewModel @Inject constructor(
    private val getRoutineListUseCase: GetRoutineListUseCase,
    private val postMemberUseCase: PostMemberUseCase
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
}
