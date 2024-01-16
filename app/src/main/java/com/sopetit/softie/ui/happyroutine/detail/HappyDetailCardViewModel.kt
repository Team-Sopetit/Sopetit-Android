package com.sopetit.softie.ui.happyroutine.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopetit.softie.domain.entity.HappyCard
import com.sopetit.softie.domain.usecase.GetHappyCardUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HappyDetailCardViewModel @Inject constructor(
    private val getHappyCardUseCase: GetHappyCardUseCase
) : ViewModel() {
    private val _happyCardResponse = MutableLiveData<HappyCard>()
    val happyCardResponse: LiveData<HappyCard> get() = _happyCardResponse

    fun getHappyCard(routineId: String) {
        viewModelScope.launch {
            getHappyCardUseCase(routineId)
                .onSuccess { response ->
                    _happyCardResponse.value = response
                    Log.e("hoyeon", "성공인가??")
                }
                .onFailure { throwable ->
                    Log.e("hoyeon", "$throwable")
                    Timber.e("$throwable")
                }
        }
    }
}
