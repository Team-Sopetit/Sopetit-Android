package com.sopetit.softie.ui.setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SettingViewModel : ViewModel() {

    private val _settingFragment: MutableLiveData<String> = MutableLiveData()
    val settingFragment: LiveData<String>
        get() = _settingFragment

    private val _isClickBackBtn: MutableLiveData<Boolean> = MutableLiveData(false)
    val isClickBackBtn: LiveData<Boolean>
        get() = _isClickBackBtn

    fun setSettingFragment(clickFragment: String) {
        _settingFragment.value = clickFragment
    }

    fun setIsClickBackBtn(isClick: Boolean) {
        _isClickBackBtn.value = isClick
    }
}
