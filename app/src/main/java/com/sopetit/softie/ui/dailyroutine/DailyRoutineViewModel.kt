package com.sopetit.softie.ui.dailyroutine

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DailyRoutineViewModel : ViewModel() {
    val routineAddList = listOf<Int>(1, 2)

    private val _isDeleteView: MutableLiveData<Boolean> = MutableLiveData(false)
    val isDeleteView: LiveData<Boolean>
        get() = _isDeleteView

    private val _editRoutineNumber: MutableLiveData<Int> = MutableLiveData()
    val editRoutineNumber: LiveData<Int>
        get() = _editRoutineNumber

    private val _isEditBtnEnabled: MutableLiveData<Boolean> = MutableLiveData()
    val isEditBtnEnabled: LiveData<Boolean>
        get() = _isEditBtnEnabled

    fun setDeleteView(deleteEnabled: Boolean) {
        _isDeleteView.value = deleteEnabled
    }

    fun setEditRoutineNumber(number: Int) {
        _editRoutineNumber.value = number
    }

    fun setEditBtnEnabled(enabled: Boolean) {
        _isEditBtnEnabled.value = enabled
    }
}
