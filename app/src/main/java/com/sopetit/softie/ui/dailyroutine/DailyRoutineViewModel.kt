package com.sopetit.softie.ui.dailyroutine

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopetit.softie.domain.entity.DailyRoutine

class DailyRoutineViewModel : ViewModel() {

    private val _mockDailyRoutineList: MutableLiveData<List<DailyRoutine>> = MutableLiveData(
        mutableListOf(
            DailyRoutine(
                1,
                "일찍 일어나기",
                "",
                3,
                true
            ),
            DailyRoutine(
                2,
                "작업 끝내기",
                "",
                5,
                false
            )
        )
    )

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
