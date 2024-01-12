package com.sopetit.softie.ui.dailyroutine.dailyroutineedit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DailyRoutineEditViewModel : ViewModel() {
    val routineAddList = listOf<Int>(1, 2)

    private val _editRoutineNumber: MutableLiveData<Int> = MutableLiveData()
    val editRoutineNumber: LiveData<Int>
        get() = _editRoutineNumber

    private val _isEditBtnEnabled: MutableLiveData<Boolean> = MutableLiveData()
    val isEditBtnEnabled: LiveData<Boolean>
        get() = _isEditBtnEnabled

    fun setEditRoutineNumber(number: Int) {
        _editRoutineNumber.value = number
    }

    fun setEditBtnEnabled(enabled: Boolean) {
        _isEditBtnEnabled.value = enabled
    }
}
