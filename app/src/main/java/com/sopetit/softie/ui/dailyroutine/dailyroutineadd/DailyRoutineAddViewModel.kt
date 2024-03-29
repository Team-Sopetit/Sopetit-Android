package com.sopetit.softie.ui.dailyroutine.dailyroutineadd

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopetit.softie.data.entity.request.AddDailyRoutineRequest
import com.sopetit.softie.domain.entity.DailyCard
import com.sopetit.softie.domain.entity.Theme
import com.sopetit.softie.domain.usecase.dailyroutine.GetRoutineDailyThemeListUseCase
import com.sopetit.softie.domain.usecase.dailyroutine.GetThemeListUseCase
import com.sopetit.softie.domain.usecase.memberdailyroutine.PostAddDailyRoutineUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DailyRoutineAddViewModel @Inject constructor(
    private val postAddDailyRoutineUseCase: PostAddDailyRoutineUseCase,
    private val getThemeListUseCase: GetThemeListUseCase,
    private val getRoutineDailyThemeListUseCase: GetRoutineDailyThemeListUseCase
) : ViewModel() {
    private val _themeId: MutableLiveData<Int> = MutableLiveData()
    val themeId: LiveData<Int> get() = _themeId
    private val _routineId: MutableLiveData<Int> = MutableLiveData()
    val routineId: LiveData<Int> get() = _routineId

    private val _themeList: MutableLiveData<List<Theme>> = MutableLiveData()
    val themeList: LiveData<List<Theme>>
        get() = _themeList

    private val _dailyRoutineCardThemeList: MutableLiveData<DailyCard> = MutableLiveData()
    val dailyRoutineCardThemeList: LiveData<DailyCard> get() = _dailyRoutineCardThemeList

    fun setThemeId(themeId: Int) {
        _themeId.value = themeId
    }

    fun setRoutineId(routineId: Int) {
        _routineId.value = routineId
    }

    fun postAddDailyRoutine() {
        viewModelScope.launch {
            val addDailyRoutineRequest =
                routineId.value?.let { AddDailyRoutineRequest(routineId = it) }
            if (addDailyRoutineRequest != null) {
                postAddDailyRoutineUseCase(addDailyRoutineRequest)
                    .onSuccess { response ->
                        Timber.d("$response")
                    }
                    .onFailure { throwable ->
                        Timber.e("$throwable")
                    }
            }
        }
    }

    fun getThemeList() {
        viewModelScope.launch {
            getThemeListUseCase()
                .onSuccess { response ->
                    _themeList.value = response
                }
                .onFailure { throwable ->
                    Timber.e("$throwable")
                }
        }
    }

    fun getDailyRoutine() {
        viewModelScope.launch {
            getRoutineDailyThemeListUseCase(themeId.value ?: 6)
                .onSuccess { response ->
                    _dailyRoutineCardThemeList.value = response
                }
                .onFailure { throwable ->
                    Timber.e("$throwable")
                }
        }
    }
}
