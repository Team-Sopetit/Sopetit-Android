package com.sopetit.softie.ui.dailyroutine

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopetit.softie.domain.entity.DailyRoutine
import com.sopetit.softie.domain.usecase.GetDailyRoutineUseCase
import com.sopetit.softie.domain.usecase.PatchAchieveDailyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DailyRoutineViewModel @Inject constructor(
    private val getDailyRoutineUseCase: GetDailyRoutineUseCase,
    private val patchAchieveDailyUseCase: PatchAchieveDailyUseCase
) :
    ViewModel() {
    private val _dailyRoutineListResponse: MutableLiveData<List<DailyRoutine>> = MutableLiveData()
    val dailyRoutineListResponse: LiveData<List<DailyRoutine>>
        get() = _dailyRoutineListResponse

    fun getDailyRoutine() {
        viewModelScope.launch {
            getDailyRoutineUseCase()
                .onSuccess { response ->
                    _dailyRoutineListResponse.value = response
                    Timber.d("뷰모델 서버 성공")
                }
                .onFailure { throwable ->
                    Timber.d("뷰모델 서버 실패")
                    Timber.e("$throwable")
                }
        }
    }

    private val _isRoutineAchieveFirst: MutableLiveData<Boolean> = MutableLiveData()
    val isRoutineAchieveFirst: LiveData<Boolean>
        get() = _isRoutineAchieveFirst

    private val _isRoutineAchieveSecond: MutableLiveData<Boolean> = MutableLiveData()
    val isRoutineAchieveSecond: LiveData<Boolean>
        get() = _isRoutineAchieveSecond

    private val _isRoutineAchieveThird: MutableLiveData<Boolean> = MutableLiveData()
    val isRoutineAchieveThird: LiveData<Boolean>
        get() = _isRoutineAchieveThird

    fun patchAchieveDaily(routineId: Int) {
        viewModelScope.launch {
            patchAchieveDailyUseCase(routineId)
                .onSuccess { response ->
//                    routineAchieve(response)
//                    setRoutineAchieve(
//                        response.isAchieve,
//                        dailyRoutineListResponse.value.indexOf()
//                    )
                    routineAchieve(routineId, response.isAchieve)

//                    when (response.routineId)
//                        dailyRoutineListResponse.value?.get(0)?.routineId -> _isRoutineAchieveFirst.value = response.isAchieve
//                    dailyRoutineListResponse.value.get(1).routineId -> _isRoutineAchieveSeco.value = response.isAchieve
//
//                    _isRoutineAchieveFirst.value = response.isAchieve
//                    _isRoutineAchieveSecond.value = response.isAchieve
//                    _isRoutineAchieveThird.value = response.isAchieve
                    Timber.d("달성 서버 성공")
                }
                .onFailure { throwable ->
                    Timber.d("달성 서버 실패")
                    Timber.e("$throwable")
                }
        }
    }

    fun routineAchieve(routineId: Int, isAchieve: Boolean) {
        when (routineId) {
            dailyRoutineListResponse.value?.get(0)?.routineId -> setRoutineAchieve(isAchieve, 0)
            dailyRoutineListResponse.value?.get(1)?.routineId -> setRoutineAchieve(isAchieve, 1)
            dailyRoutineListResponse.value?.get(2)?.routineId -> setRoutineAchieve(isAchieve, 2)
        }
    }

    private val _isDeleteView: MutableLiveData<Boolean> = MutableLiveData(false)
    val isDeleteView: LiveData<Boolean>
        get() = _isDeleteView

    val editRoutineIdArray = ArrayList<Int>()

    private val _isEditBtnEnabled: MutableLiveData<Boolean> = MutableLiveData()
    val isEditBtnEnabled: LiveData<Boolean>
        get() = _isEditBtnEnabled

    fun setRoutineAchieve(isAchieve: Boolean, index: Int) {
        when (index) {
            0 -> _isRoutineAchieveFirst.value = isAchieve
            1 -> _isRoutineAchieveSecond.value = isAchieve
            2 -> _isRoutineAchieveThird.value = isAchieve
        }
    }

    fun setDeleteView(deleteEnabled: Boolean) {
        _isDeleteView.value = deleteEnabled
    }

    fun setEditRoutineIdArray(routineId: Int) {
        if (editRoutineIdArray.contains(routineId)) {
            editRoutineIdArray.removeAt(editRoutineIdArray.indexOf(routineId))
        } else {
            editRoutineIdArray.add(routineId)
        }
    }

    fun setEditBtnEnabled(enabled: Boolean) {
        _isEditBtnEnabled.value = enabled
    }
}
