package com.sopetit.softie.ui.main.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopetit.softie.domain.entity.Cotton
import com.sopetit.softie.domain.entity.Home
import com.sopetit.softie.domain.usecase.GetHomeUseCase
import com.sopetit.softie.ui.main.home.HomeFragment.Companion.RUN_OUT
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeUseCase: GetHomeUseCase
) : ViewModel() {
    private val _homeResponse = MutableLiveData<Home>()
    val homeResponse: LiveData<Home> get() = _homeResponse

    fun getHome() {
        viewModelScope.launch {
            getHomeUseCase()
                .onSuccess { response ->
                    _homeResponse.value = response
                }
                .onFailure { throwable ->
                    Log.e("kang", "$throwable")
                    Timber.e("$throwable")
                }
        }
    }

    fun patchSom(cotton: Cotton) {
        val isCottonRemain: (Int) -> Boolean = { cotton -> cotton > RUN_OUT }
        val cottonCount = when (cotton) {
            Cotton.DAILY -> homeResponse.value?.dailyCottonCount ?: RUN_OUT
            Cotton.HAPPINESS -> homeResponse.value?.happinessCottonCount ?: RUN_OUT
        }

        if (isCottonRemain(cottonCount)) {
            val changeData = when (cotton) {
                Cotton.DAILY -> homeResponse.value?.copy(dailyCottonCount = cottonCount - 1)
                Cotton.HAPPINESS -> homeResponse.value?.copy(happinessCottonCount = cottonCount - 1)
            }
            _homeResponse.value = changeData ?: homeResponse.value
        }
    }
}
