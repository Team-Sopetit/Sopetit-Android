package com.sopetit.softie.ui.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopetit.softie.domain.usecase.doll.GetDollUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val getDollUseCase: GetDollUseCase
) : ViewModel() {

    private val _bearChoiceView: MutableLiveData<Boolean> = MutableLiveData(false)
    val bearChoiceView: LiveData<Boolean>
        get() = _bearChoiceView

    private val _bearNameChoiceView: MutableLiveData<Boolean> = MutableLiveData(false)
    val bearNameChoiceView: LiveData<Boolean>
        get() = _bearNameChoiceView

    private val _themeChoiceView: MutableLiveData<Boolean> = MutableLiveData(false)
    val themeChoiceView: LiveData<Boolean>
        get() = _themeChoiceView

    private val _secondThemeChoiceView: MutableLiveData<Boolean> = MutableLiveData(false)
    val secondThemeChoiceView: LiveData<Boolean>
        get() = _secondThemeChoiceView

    private val _routineChoiceView: MutableLiveData<Boolean> = MutableLiveData(false)
    val routineChoiceView: LiveData<Boolean>
        get() = _routineChoiceView

    private val _selectedThemeArray: MutableLiveData<ArrayList<Int>> = MutableLiveData()
    val selectedThemeArray: LiveData<ArrayList<Int>>
        get() = _selectedThemeArray

    private val _selectedRoutineArray: MutableLiveData<ArrayList<Int>> = MutableLiveData()
    val selectedRoutineArray: LiveData<ArrayList<Int>>
        get() = _selectedRoutineArray

    private val _layoutTranslucent: MutableLiveData<Boolean> = MutableLiveData(false)
    val layoutTranslucent: LiveData<Boolean>
        get() = _layoutTranslucent

    private val _selectedBearType: MutableLiveData<Int> = MutableLiveData()
    val selectedBearType: LiveData<Int> get() = _selectedBearType

    private val _bearNickname: MutableLiveData<String> = MutableLiveData("")
    val bearNickname: LiveData<String> get() = _bearNickname

    private val _bearFace: MutableLiveData<String> = MutableLiveData()
    val bearFace: LiveData<String>
        get() = _bearFace

    fun changeBearChoiceView() {
        _bearChoiceView.value = true
    }

    fun changeBearNameChoiceView() {
        _bearNameChoiceView.value = true
    }

    fun changeThemeChoiceView() {
        _themeChoiceView.value = true
    }

    fun changeSecondThemeChoiceView() {
        _secondThemeChoiceView.value = true
    }

    fun changeRoutineChoiceView(isRoutineChoice: Boolean) {
        _routineChoiceView.value = isRoutineChoice
    }

    fun setSelectedThemeArray(themeArray: ArrayList<Int>) {
        _selectedThemeArray.value = themeArray
    }

    fun setSelectedRoutineArray(routineArray: ArrayList<Int>) {
        _selectedRoutineArray.value = routineArray
    }

    fun setLayoutTranslucent(boolean: Boolean) {
        _layoutTranslucent.value = boolean
    }

    fun setSelectedBearType(bearType: Int) {
        _selectedBearType.value = bearType
    }

    fun setBearNickname(nickname: String) {
        _bearNickname.value = nickname
    }

    fun setDollFace(type: Int) {
        viewModelScope.launch {
            when (type) {
                BROWN_BEAR -> getDollUseCase.invoke(BROWN)
                GRAY_BEAR -> getDollUseCase.invoke(GRAY)
                PANDA_BEAR -> getDollUseCase.invoke(WHITE)
                RED_BEAR -> getDollUseCase.invoke(RED)
                else -> getDollUseCase.invoke(BROWN)
            }.onSuccess { response ->
                _bearFace.value = response
                Timber.d("서버 통신 성공 -> $response")
            }.onFailure {
                Timber.e("서버 통신 실패 -> ${it.message}")
            }
        }
    }

    companion object {
        const val NONE = 0
        const val BROWN_BEAR = 1
        const val GRAY_BEAR = 2
        const val PANDA_BEAR = 3
        const val RED_BEAR = 4

        const val BROWN = "BROWN"
        const val GRAY = "GRAY"
        const val WHITE = "WHITE"
        const val RED = "RED"
    }
}
