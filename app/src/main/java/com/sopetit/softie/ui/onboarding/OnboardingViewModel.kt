package com.sopetit.softie.ui.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OnboardingViewModel : ViewModel() {

    private val _bearChoiceView: MutableLiveData<Boolean> = MutableLiveData(false)
    val bearChoiceView: LiveData<Boolean>
        get() = _bearChoiceView

    private val _bearNameChoiceView: MutableLiveData<Boolean> = MutableLiveData(false)
    val bearNameChoiceView: LiveData<Boolean>
        get() = _bearNameChoiceView

    private val _themeChoiceView: MutableLiveData<Boolean> = MutableLiveData(false)
    val themeChoiceView: LiveData<Boolean>
        get() = _themeChoiceView

    private val _routineChoiceView: MutableLiveData<Boolean> = MutableLiveData(false)
    val routineChoiceView: LiveData<Boolean>
        get() = _routineChoiceView

    private val _selectedThemeArray: MutableLiveData<ArrayList<Int>> = MutableLiveData()
    val selectedThemeArray: LiveData<ArrayList<Int>>
        get() = _selectedThemeArray

    private val _layoutTranslucent: MutableLiveData<Boolean> = MutableLiveData(false)
    val layoutTranslucent: LiveData<Boolean>
        get() = _layoutTranslucent

    private val _selectedBearType: MutableLiveData<Int> = MutableLiveData(NONE)
    val selectedBearType: LiveData<Int> get() = _selectedBearType

    fun changeBearChoiceView() {
        _bearChoiceView.value = true
    }

    fun changeBearNameChoiceView() {
        _bearNameChoiceView.value = true
    }

    fun changeThemeChoiceView() {
        _themeChoiceView.value = true
    }

    fun changeRoutineChoiceView() {
        _routineChoiceView.value = true
    }

    fun setSelectedThemeArray(themeArray: ArrayList<Int>) {
        _selectedThemeArray.value = themeArray
    }

    fun setLayoutTranslucent(boolean: Boolean) {
        _layoutTranslucent.value = boolean
    }

    fun setSelectedBearType(bearType: Int) {
        _selectedBearType.value = bearType
    }

    companion object {
        val NONE = 0
        val BROWN_BEAR = 1
        val GRAY_BEAR = 2
        val PANDA_BEAR = 3
        val RED_BEAR = 4
    }
}
