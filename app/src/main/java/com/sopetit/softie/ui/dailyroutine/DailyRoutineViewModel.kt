package com.sopetit.softie.ui.dailyroutine

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopetit.softie.domain.entity.DailyRoutine

class DailyRoutineViewModel : ViewModel() {
    // val routineAddList = listOf<Int>(1, 2)

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
            ),
            DailyRoutine(
                3,
                "이불 개기",
                "",
                10,
                true
            )
        )
    )

    val mockDailyRoutineList: LiveData<List<DailyRoutine>>
        get() = _mockDailyRoutineList

    private val _isDeleteView: MutableLiveData<Boolean> = MutableLiveData(false)
    val isDeleteView: LiveData<Boolean>
        get() = _isDeleteView

//    private val _editRoutineIdArray: MutableLiveData<ArrayList<Int>> = MutableLiveData()
//    val editRoutineIdArray: LiveData<ArrayList<Int>>
//        get() = _editRoutineIdArray

    val editRoutineIdArray = ArrayList<Int>()

    private val _isEditBtnEnabled: MutableLiveData<Boolean> = MutableLiveData()
    val isEditBtnEnabled: LiveData<Boolean>
        get() = _isEditBtnEnabled

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
