package com.sopetit.softie.ui.onboarding.dollselection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DollSelectionViewModel : ViewModel() {
    private val _selectedDollType: MutableLiveData<Int> = MutableLiveData(NONE)
    val selectedDollType: LiveData<Int> = _selectedDollType

    fun clickBear(dollType: Int) {
        val isSameBearSelected = _selectedDollType.value == dollType

        if (isSameBearSelected) _selectedDollType.value = NONE
        else _selectedDollType.value = dollType
    }

    companion object {
        val NONE = 0
        val BROWN_BEAR = 1
        val GRAY_BEAR = 2
        val PANDA_BEAR = 3
        val RED_BEAR = 4
    }
}
