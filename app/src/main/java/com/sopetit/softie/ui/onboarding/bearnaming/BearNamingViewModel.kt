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
    val isSpecialCharacterEntered: LiveData<Boolean> = _isSpecialCharacterEntered

    private val _isLengthExceed = MutableLiveData<Boolean>()
    val isLengthExceed: LiveData<Boolean> = _isLengthExceed

    private val _isWarning = MutableLiveData<Boolean>(false)
    val isWarning: LiveData<Boolean> = _isWarning

    val nickname: MutableLiveData<String> = MutableLiveData("")

    val filterSpecialCharacter = InputFilter { source, _, _, _, _, _ ->
        if (source.isNullOrBlank() || NICKNAME_REGEX.matcher(source).matches()) {
            _isSpecialCharacterEntered.value = false
            _isLengthExceed.value = false
            checkWarning()
            source
        } else {
            if ((nickname.value?.length ?: 0) <= MAXIMUM_LENGTH) {
                _isSpecialCharacterEntered.value = true
                _isLengthExceed.value = false
                checkWarning()
                source.filter { it.isLetterOrDigit() }
            } else source
        }
    }

    val filterLength = InputFilter.LengthFilter(MAXIMUM_LENGTH)

    fun checkIsNicknameValid() {
        val nicknameLength = nickname.value?.length ?: 0
        _isNickNameValid.value = nicknameLength in MINIMUM_LENGTH..MAXIMUM_LENGTH
    }

    fun warnNicknameLength() {
        _isSpecialCharacterEntered.value = false
        _isLengthExceed.value = true
        checkWarning()
    }

    private fun checkWarning() {
        _isWarning.value =
            (_isSpecialCharacterEntered.value == true) || (_isLengthExceed.value == true)
    }

    companion object {
        private const val MINIMUM_LENGTH = 1
        private const val MAXIMUM_LENGTH = 10
        private const val NICKNAME_PATTERN =
            "^[ㄱ-ㅣ가-힣a-zA-Z\\u318D\\u119E\\u11A2\\u2022\\u2025\\u00B7\\uFE550-9]{1,10}\$"
        val NICKNAME_REGEX: Pattern = Pattern.compile(NICKNAME_PATTERN.replace("\\s", ""))
    }
}
