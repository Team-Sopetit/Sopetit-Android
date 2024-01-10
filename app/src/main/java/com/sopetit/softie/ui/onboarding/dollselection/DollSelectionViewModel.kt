package com.sopetit.softie.ui.onboarding.dollselection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopetit.softie.ui.onboarding.OnboardingViewModel.Companion.NONE

class DollSelectionViewModel : ViewModel() {
    private val _selectedDollType: MutableLiveData<Int> = MutableLiveData(NONE)
    val selectedDollType: LiveData<Int> = _selectedDollType

    fun clickBear(dollType: Int) {
        val isSameBearSelected = _selectedDollType.value == dollType

        if (isSameBearSelected) _selectedDollType.value = NONE
        else _selectedDollType.value = dollType
    }
}
