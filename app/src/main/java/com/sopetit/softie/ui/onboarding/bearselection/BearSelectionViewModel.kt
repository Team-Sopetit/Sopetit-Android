package com.sopetit.softie.ui.onboarding.bearselection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopetit.softie.ui.onboarding.OnboardingViewModel.Companion.NONE

class BearSelectionViewModel : ViewModel() {
    private val _selectedBearType: MutableLiveData<Int> = MutableLiveData(NONE)
    val selectedBearType: LiveData<Int> = _selectedBearType

    fun selectBearType(dollType: Int) {
        val isSameBearSelected = _selectedBearType.value == dollType

        if (isSameBearSelected) _selectedBearType.value = NONE
        else _selectedBearType.value = dollType
    }
}
