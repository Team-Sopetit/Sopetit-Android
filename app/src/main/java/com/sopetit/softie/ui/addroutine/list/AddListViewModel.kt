package com.sopetit.softie.ui.addroutine.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopetit.softie.domain.entity.MakerCard
import com.sopetit.softie.domain.entity.RoutineTheme
import com.sopetit.softie.domain.usecase.addroutine.GetMakerCardUseCase
import com.sopetit.softie.domain.usecase.addroutine.GetRoutineThemeListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AddListViewModel @Inject constructor(
    private val getMakerCardUseCase: GetMakerCardUseCase,
    private val getRoutineThemeListUseCase: GetRoutineThemeListUseCase
) : ViewModel() {
    private val _addMakerCardResponse = MutableLiveData<MakerCard>()
    val addMakerCardResponse: LiveData<MakerCard> get() = _addMakerCardResponse

    private val _addRoutineThemeListResponse = MutableLiveData<RoutineTheme>()
    val addRoutineThemeListResponse: LiveData<RoutineTheme> get() = _addRoutineThemeListResponse

    private val _myMakerId: MutableLiveData<Int> = MutableLiveData()

    fun setMakerId(makerId: Int) {
        _myMakerId.value = makerId
    }

    fun getMakerCard() {
        viewModelScope.launch {
            getMakerCardUseCase()
                .onSuccess { response ->
                    _addMakerCardResponse.value = response
                }
                .onFailure { throwable ->
                    Timber.e("$throwable")
                }
        }
    }

//    fun getRoutineTheme() {
//        viewModelScope.launch {
//            getRoutineThemeListUseCase
//                .onSuccess { response ->
//                    _addRoutineThemeListResponse.value = response
//                }
//                .onFailure { throwable ->
//                    Timber.e("$throwable")
//                }
//        }
//    }
}
