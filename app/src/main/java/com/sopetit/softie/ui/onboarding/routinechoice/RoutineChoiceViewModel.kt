package com.sopetit.softie.ui.onboarding.routinechoice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopetit.softie.domain.entity.Routine

class RoutineChoiceViewModel : ViewModel() {

    private val _mockRoutineList: MutableLiveData<List<Routine>> = MutableLiveData(
        mutableListOf(
            Routine(
                1,
                "하루 한 끼 건강식으로 먹기"
            ),
            Routine(
                2,
                "앉아 있을 때 의식적으로 바른 자세 하기"
            ),
            Routine(
                3,
                "잠자기 전 오늘 다시 침대에\n누울 수 있음에 감사하기"
            ),
            Routine(
                4,
                "하루 한 번 엘레베이터 대신 계단 이용하기"
            ),
            Routine(
                5,
                "할일 전에 그 일을 잘하면\n얻을 수 있는 구체적인 것 하나 적기"
            ),
            Routine(
                6,
                "내려야 할 정거장 하나 먼저 내려서 걷기"
            ),
            Routine(
                7,
                "오늘 감정이 담긴 일기 한 줄 이상 쓰기"
            ),
            Routine(
                8,
                "마라탕 먹고 싶다"
            ),
            Routine(
                9,
                "빨리 완성하고 싶다"
            ),
            Routine(
                10,
                "집 언제 가지"
            )
        )
    )

    val mockRoutineList: LiveData<List<Routine>>
        get() = _mockRoutineList

    private val _isNoticeVisible: MutableLiveData<Boolean> = MutableLiveData(false)
    val isNoticeVisible: LiveData<Boolean>
        get() = _isNoticeVisible

    private val _isRoutineBtnEnabled: MutableLiveData<Boolean> = MutableLiveData()
    val isRoutineBtnEnabled: LiveData<Boolean>
        get() = _isRoutineBtnEnabled

    fun setNoticeVisible(visible: Boolean) {
        _isNoticeVisible.value = visible
    }

    fun setRoutineBtnEnabled(isEnabled: Boolean) {
        _isRoutineBtnEnabled.value = isEnabled
    }
}
