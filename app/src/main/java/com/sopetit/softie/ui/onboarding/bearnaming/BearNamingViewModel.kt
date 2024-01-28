package com.sopetit.softie.ui.onboarding.bearnaming

import android.text.InputFilter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.regex.Pattern

class BearNamingViewModel : ViewModel() {
    private val _isNickNameValid = MutableLiveData(false)
    val isNickNameValid: LiveData<Boolean> get() = _isNickNameValid

    private val _isSpecialCharacterEntered = MutableLiveData<Boolean>()
    private val isSpecialCharacterEntered: LiveData<Boolean> = _isSpecialCharacterEntered

    private val _isLengthExceed = MutableLiveData<Boolean>()
    val isLengthExceed: LiveData<Boolean> = _isLengthExceed

    private val _isWarning = MutableLiveData(false)
    val isWarning: LiveData<Boolean> = _isWarning

    val nickname: MutableLiveData<String> = MutableLiveData("")

    val filterLength = InputFilter.LengthFilter(MAXIMUM_LENGTH)

    val filterSpecialCharacter = InputFilter { source, _, _, _, _, _ ->
        if (source.isNullOrBlank() || NICKNAME_REGEX.matcher(source).matches()) {
            handleValidInput()
            source
        } else {
            handleInvalidInput(source)
        }
    }

    private fun handleValidInput() {
        clearWarningFlags()
        checkWarning()
    }

    private fun handleInvalidInput(source: CharSequence) =
        if ((nickname.value?.length ?: 0) <= MAXIMUM_LENGTH) {
            setSpecialCharacterWarning(true)
            setLengthExceedWarning(false)
            checkWarning()
            source.filter { it.isLetter() }
        } else source

    fun warnNicknameLength() {
        setSpecialCharacterWarning(false)
        setLengthExceedWarning(true)
        checkWarning()
    }

    private fun clearWarningFlags() {
        setSpecialCharacterWarning(false)
        setLengthExceedWarning(false)
    }

    private fun setSpecialCharacterWarning(value: Boolean) {
        _isSpecialCharacterEntered.value = value
    }

    fun setLengthExceedWarning(value: Boolean) {
        _isLengthExceed.value = value
    }

    fun checkWarning() {
        _isWarning.value =
            (isSpecialCharacterEntered.value == true) || (isLengthExceed.value == true)
    }

    companion object {
        private const val MAXIMUM_LENGTH = 10
        private const val NICKNAME_PATTERN =
            "^[ㄱ-ㅣ가-힣a-zA-Z\\u318D\\u119E\\u11A2\\u2022\\u2025\\u00B7]{1,10}\$"
        val NICKNAME_REGEX: Pattern = Pattern.compile(NICKNAME_PATTERN.replace("\\s", ""))
    }
}
