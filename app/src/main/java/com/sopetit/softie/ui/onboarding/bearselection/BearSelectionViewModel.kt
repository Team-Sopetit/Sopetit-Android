package com.sopetit.softie.ui.onboarding.bearselection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopetit.softie.domain.usecase.local.SetBearTypeUseCase
import com.sopetit.softie.ui.onboarding.OnboardingViewModel.Companion.NONE
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BearSelectionViewModel @Inject constructor(
    private val setBearTypeUseCase: SetBearTypeUseCase
) : ViewModel() {
    private val _selectedBearType: MutableLiveData<String> = MutableLiveData(NONE)
    val selectedBearType: LiveData<String> get() = _selectedBearType
    private val _isBearSelected: MutableLiveData<Boolean> = MutableLiveData(false)
    val isBearSelected: LiveData<Boolean> get() = _isBearSelected

    fun selectBearType(dollType: String) {
        val isSameBearSelected = _selectedBearType.value == dollType

        if (isSameBearSelected) {
            _selectedBearType.value = NONE
            _isBearSelected.value = false
        } else {
            _selectedBearType.value = dollType
            _isBearSelected.value = true
        }
    }

    fun setBearType() {
        setBearTypeUseCase(_selectedBearType.value ?: "BROWN")
    }
}
