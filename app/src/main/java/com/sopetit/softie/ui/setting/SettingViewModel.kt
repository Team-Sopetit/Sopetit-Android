package com.sopetit.softie.ui.setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SettingViewModel : ViewModel() {

    private val _settingFragment: MutableLiveData<String> = MutableLiveData()
    val settingFragment: LiveData<String>
        get() = _settingFragment

    fun setSettingFragment(clickFragment: String) {
        _settingFragment.value = clickFragment
    }
}
