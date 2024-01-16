package com.sopetit.softie.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopetit.softie.domain.entity.Cotton
import com.sopetit.softie.domain.entity.Home
import com.sopetit.softie.domain.usecase.GetHomeUseCase
import com.sopetit.softie.domain.usecase.PatchCottonUseCase
import com.sopetit.softie.ui.main.home.HomeFragment.Companion.RUN_OUT
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeUseCase: GetHomeUseCase,
    private val patchCottonUseCase: PatchCottonUseCase
) : ViewModel() {
    private val _homeResponse = MutableLiveData<Home>()
    val homeResponse: LiveData<Home> get() = _homeResponse
    private val _conversations = MutableLiveData<List<String>>(_homeResponse.value?.conversations)
    val conversations: LiveData<List<String>> get() = _conversations

    fun getHome() {
        viewModelScope.launch {
            getHomeUseCase()
                .onSuccess { response ->
                    _homeResponse.value = response
                    _conversations.value = response.conversations
                }
                .onFailure { throwable ->
                    Timber.e("$throwable")
                }
        }
    }

    fun checkCotton(cottonType: Cotton) {
        val isCottonRemain: (Int) -> Boolean = { cottonCount -> cottonCount > RUN_OUT }
        var remainCottonCount = getRemainCottonCount(cottonType)

        if (isCottonRemain(remainCottonCount)) {
            remainCottonCount = patchCotton(remainCottonCount, cottonType)
            setCottonData(cottonType, remainCottonCount)
        }
    }

    private fun getRemainCottonCount(cotton: Cotton): Int {
        val cottonCount = when (cotton) {
            Cotton.DAILY -> homeResponse.value?.dailyCottonCount ?: RUN_OUT
            Cotton.HAPPINESS -> homeResponse.value?.happinessCottonCount ?: RUN_OUT
        }
        return cottonCount
    }

    private fun patchCotton(remainCottonCount: Int, cotton: Cotton): Int {
        var cottonCount = remainCottonCount
        viewModelScope.launch {
            patchCottonUseCase(cotton.name)
                .onSuccess { response ->
                    cottonCount = response.cottonCount
                }
                .onFailure { throwable ->
                    Timber.e("$throwable")
                }
        }
        return cottonCount
    }

    private fun setCottonData(cotton: Cotton, cottonCount: Int) {
        val changeData = when (cotton) {
            Cotton.DAILY -> _homeResponse.value?.copy(dailyCottonCount = cottonCount - 1)
            Cotton.HAPPINESS -> _homeResponse.value?.copy(happinessCottonCount = cottonCount - 1)
        }
        _homeResponse.value = changeData ?: homeResponse.value
    }
}
