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

    val nickname: MutableLiveData<String> = MutableLiveData("")

    var filterSpecialCharacter = InputFilter { source, _, _, _, _, _ ->
        if (source.isNullOrBlank() || NICKNAME_REGEX.matcher(source).matches()) {
            _isSpecialCharacterEntered.value = false
            source
        } else {
            _isSpecialCharacterEntered.value = true
            source.filter { it.isLetterOrDigit() }
        }
    }

    fun checkIsNicknameValid() {
        val nicknameLength = nickname.value?.length ?: 0
        _isNickNameValid.value = nicknameLength in MINIMUM_LENGTH..MAXIMUM_LENGTH
    }

    companion object {
        private const val MINIMUM_LENGTH = 1
        private const val MAXIMUM_LENGTH = 10
        private const val NICKNAME_PATTERN =
            "^[ㄱ-ㅣ가-힣a-zA-Z0-9\\u318D\\u119E\\u11A2\\u2022\\u2025a\\u00B7\\uFE55]+$"
        val NICKNAME_REGEX: Pattern = Pattern.compile(NICKNAME_PATTERN)
    }
}
