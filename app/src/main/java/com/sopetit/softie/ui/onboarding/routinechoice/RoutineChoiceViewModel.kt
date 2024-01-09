package com.sopetit.softie.ui.onboarding.routinechoice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RoutineChoiceViewModel : ViewModel() {

    private val _routineChoiceView: MutableLiveData<Boolean> = MutableLiveData(false)
    val routineChoiceView: LiveData<Boolean>
        get() = _routineChoiceView

    fun changeRoutineChoiceView() {
        _routineChoiceView.value = true
    }
}
