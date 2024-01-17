package com.sopetit.softie.ui.happyroutine.progress

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopetit.softie.domain.entity.HappyProgress
import com.sopetit.softie.domain.usecase.GetHappyProgressUseCase
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class HappyProgressViewModel @Inject constructor(
    private val getHappyProgressUseCase: GetHappyProgressUseCase
) : ViewModel() {
    private val _happyProgressResponse = MutableLiveData<HappyProgress>()
    val happyProgressResponse: LiveData<HappyProgress> get() = _happyProgressResponse

    fun getHappyProgress() {
        viewModelScope.launch {
            getHappyProgressUseCase()
                .onSuccess { response ->
                    _happyProgressResponse.value = response
                }
                .onFailure { throwable ->
                    Timber.e("$throwable")
                }
        }
    }
}
