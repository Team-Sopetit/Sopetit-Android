package com.sopetit.softie.ui.dailyroutine

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopetit.softie.domain.entity.DailyRoutine
import com.sopetit.softie.domain.usecase.GetDailyRoutineUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DailyRoutineViewModel @Inject constructor(private val getDailyRoutineUseCase: GetDailyRoutineUseCase) :
    ViewModel(
    ) {

    private val _mockDailyRoutineList: MutableLiveData<List<DailyRoutine>> = MutableLiveData()

    val mockDailyRoutineList: LiveData<List<DailyRoutine>>
        get() = _mockDailyRoutineList

    private val _isRoutineAchieveFirst: MutableLiveData<Boolean> = MutableLiveData()
    val isRoutineAchieveFirst: LiveData<Boolean>
        get() = _isRoutineAchieveFirst

    private val _isRoutineAchieveSecond: MutableLiveData<Boolean> = MutableLiveData()
    val isRoutineAchieveSecond: LiveData<Boolean>
        get() = _isRoutineAchieveSecond

    private val _isRoutineAchieveThird: MutableLiveData<Boolean> = MutableLiveData()
    val isRoutineAchieveThird: LiveData<Boolean>
        get() = _isRoutineAchieveThird

    private val _isDeleteView: MutableLiveData<Boolean> = MutableLiveData(false)
    val isDeleteView: LiveData<Boolean>
        get() = _isDeleteView

    val editRoutineIdArray = ArrayList<Int>()

    private val _isEditBtnEnabled: MutableLiveData<Boolean> = MutableLiveData()
    val isEditBtnEnabled: LiveData<Boolean>
        get() = _isEditBtnEnabled

    fun getDailyRoutine() {
        viewModelScope.launch {
            getDailyRoutineUseCase()
                .onSuccess { response ->
                    _mockDailyRoutineList.value = response
//                    _isRoutineAchieveFirst.value = true
//                    _isRoutineAchieveSecond.value = true
//                    _isRoutineAchieveThird.value = true
//                    _isDeleteView.value = true
//                    _isEditBtnEnabled.value = true
                }
                .onFailure { throwable ->
                    Log.e("되나", "$throwable")
                    Timber.e("$throwable")
                }
        }
    }

    fun setRoutineAchieve(isAchieve: Boolean, index: Int) {
        when (index) {
            0 -> _isRoutineAchieveFirst.value = isAchieve
            1 -> _isRoutineAchieveSecond.value = isAchieve
            2 -> _isRoutineAchieveThird.value = isAchieve
        }
    }

    fun setDeleteView(deleteEnabled: Boolean) {
        _isDeleteView.value = deleteEnabled
    }

    fun setEditRoutineIdArray(routineId: Int) {
        if (editRoutineIdArray.contains(routineId)) {
            editRoutineIdArray.removeAt(editRoutineIdArray.indexOf(routineId))
        } else {
            editRoutineIdArray.add(routineId)
        }
    }

    fun setEditBtnEnabled(enabled: Boolean) {
        _isEditBtnEnabled.value = enabled
    }
}
