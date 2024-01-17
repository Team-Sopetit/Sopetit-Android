package com.sopetit.softie.ui.dailyroutine.dailyroutineadd

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopetit.softie.domain.entity.AddRoutine
import com.sopetit.softie.domain.entity.DailyCard
import com.sopetit.softie.domain.entity.DailyRoutineAdd
import com.sopetit.softie.domain.entity.Theme
import com.sopetit.softie.domain.usecase.PostAddDailyRoutineUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DailyRoutineAddViewModel @Inject constructor(private val postAddDailyRoutineUseCase: PostAddDailyRoutineUseCase) :
    ViewModel() {
    private val _mockThemeList: MutableLiveData<List<Theme>> = MutableLiveData(
        mutableListOf(
            Theme(
                1,
                "하루의 시작",
                "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                ""
            ),
            Theme(
                2,
                "건강한 몸",
                "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                ""
            ),
            Theme(
                3,
                "무기력 극복",
                "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                ""
            ),
            Theme(
                4,
                "편안한 잠",
                "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                ""
            ),
            Theme(
                5,
                "환경지킴이",
                "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                ""
            ),
            Theme(
                6,
                "소소한 친절",
                "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                ""
            ),
            Theme(
                7,
                "감사의 마음",
                "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                ""
            ),
            Theme(
                8,
                "작은 성취감",
                "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                ""
            ),
            Theme(
                9,
                "소중한 나",
                "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                ""
            ),
            Theme(
                10,
                "통통한 통장",
                "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                ""
            ),
            Theme(
                11,
                "몰입할 준비",
                "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                ""
            ),
            Theme(
                12,
                "비워내기",
                "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                ""
            )

        )
    )

    val mockThemeList: LiveData<List<Theme>>
        get() = _mockThemeList

    val startNewDayCardList: LiveData<List<DailyCard>>
        get() = _startNewDayCardList

    val healthBodyCardList: LiveData<List<DailyCard>>
        get() = _healthBodyCardList

    val overcomeHelplessnessCardList: LiveData<List<DailyCard>>
        get() = _overcomeHelplessnessCardList

    val restfulSleepCardList: LiveData<List<DailyCard>>
        get() = _restfulSleepCardList

    val environmentalGuardCardList: LiveData<List<DailyCard>>
        get() = _environmentalGuardCardList

    val smallKindnessCardList: LiveData<List<DailyCard>>
        get() = _smallKindnessCardList

    val gratitudeCardList: LiveData<List<DailyCard>>
        get() = _gratitudeCardList

    val smallAccomplishmentCardList: LiveData<List<DailyCard>>
        get() = _smallAccomplishmentCardList

    val preciousMeCardList: LiveData<List<DailyCard>>
        get() = _preciousMeCardList

    val forRichCardList: LiveData<List<DailyCard>>
        get() = _forRichCardList

    val readyImmerseCardList: LiveData<List<DailyCard>>
        get() = _readyImmerseCardList

    val emptyCardList: LiveData<List<DailyCard>>
        get() = _emptyCardList

    private val _startNewDayCardList: MutableLiveData<List<DailyCard>> = MutableLiveData(
        mutableListOf(
            DailyCard(
                backgroundImageUrl = "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                routineId = 1,
                content = "첫번째 데이터"
            ),
            DailyCard(
                backgroundImageUrl = "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                routineId = 2,
                content = "첫번째 데이터"
            ),
            DailyCard(
                backgroundImageUrl = "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                routineId = 3,
                content = "첫번째 데이터"
            ),
            DailyCard(
                backgroundImageUrl = "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                routineId = 4,
                content = "첫번째 데이터"
            ),
            DailyCard(
                backgroundImageUrl = "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                routineId = 5,
                content = "첫번째 데이터"
            )
        )
    )

    private val _healthBodyCardList: MutableLiveData<List<DailyCard>> = MutableLiveData(
        mutableListOf(
            DailyCard(
                backgroundImageUrl = "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                routineId = 6,
                content = "두번째 데이터"
            ),
            DailyCard(
                backgroundImageUrl = "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                routineId = 7,
                content = "두번째 데이터"
            ),
            DailyCard(
                backgroundImageUrl = "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                routineId = 8,
                content = "두번째 데이터"
            ),
            DailyCard(
                backgroundImageUrl = "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                routineId = 9,
                content = "두번째 데이터"
            ),
            DailyCard(
                backgroundImageUrl = "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                routineId = 10,
                content = "두번째 데이터"
            )
        )
    )

    private val _overcomeHelplessnessCardList: MutableLiveData<List<DailyCard>> = MutableLiveData(
        mutableListOf(
            DailyCard(
                backgroundImageUrl = "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                routineId = 11,
                content = "세번째 데이터"
            ),
            DailyCard(
                backgroundImageUrl = "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                routineId = 12,
                content = "세번째 데이터"
            ),
            DailyCard(
                backgroundImageUrl = "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                routineId = 13,
                content = "세번째 데이터"
            ),
            DailyCard(
                backgroundImageUrl = "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                routineId = 14,
                content = "세번째 데이터"
            ),
            DailyCard(
                backgroundImageUrl = "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                routineId = 15,
                content = "세번째 데이터"
            )
        )
    )

    private val _restfulSleepCardList: MutableLiveData<List<DailyCard>> = MutableLiveData(
        mutableListOf(
            DailyCard(
                backgroundImageUrl = "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                routineId = 1,
                content = "네번째 데이터"
            ),
            DailyCard(
                backgroundImageUrl = "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                routineId = 2,
                content = "네번째 데이터"
            ),
            DailyCard(
                backgroundImageUrl = "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                routineId = 3,
                content = "네번째 데이터"
            ),
            DailyCard(
                backgroundImageUrl = "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                routineId = 4,
                content = "네번째 데이터"
            ),
            DailyCard(
                backgroundImageUrl = "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                routineId = 5,
                content = "네번째 데이터"
            )
        )
    )

    private val _environmentalGuardCardList: MutableLiveData<List<DailyCard>> = MutableLiveData(
        mutableListOf(
            DailyCard(
                backgroundImageUrl = "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                routineId = 1,
                content = "다섯번째 데이터"
            ),
            DailyCard(
                backgroundImageUrl = "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                routineId = 2,
                content = "다섯번째 데이터"
            ),
            DailyCard(
                backgroundImageUrl = "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                routineId = 3,
                content = "다섯번째 데이터"
            ),
            DailyCard(
                backgroundImageUrl = "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                routineId = 4,
                content = "다섯번째 데이터"
            ),
            DailyCard(
                backgroundImageUrl = "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                routineId = 5,
                content = "다섯번째 데이터"
            )
        )
    )

    private val _smallKindnessCardList: MutableLiveData<List<DailyCard>> = MutableLiveData(
        mutableListOf(
            DailyCard(
                backgroundImageUrl = "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                routineId = 1,
                content = "여섯번째 데이터"
            ),
            DailyCard(
                backgroundImageUrl = "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                routineId = 2,
                content = "여섯번째 데이터"
            ),
            DailyCard(
                backgroundImageUrl = "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                routineId = 3,
                content = "여섯번째 데이터"
            ),
            DailyCard(
                backgroundImageUrl = "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                routineId = 4,
                content = "여섯번째 데이터"
            ),
            DailyCard(
                backgroundImageUrl = "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                routineId = 5,
                content = "여섯번째 데이터"
            )
        )
    )

    private val _gratitudeCardList: MutableLiveData<List<DailyCard>> = MutableLiveData(
        mutableListOf(
            DailyCard(
                backgroundImageUrl = "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                routineId = 1,
                content = "일곱번째 데이터"
            ),
            DailyCard(
                backgroundImageUrl = "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                routineId = 2,
                content = "일곱번째 데이터"
            ),
            DailyCard(
                backgroundImageUrl = "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                routineId = 3,
                content = "일곱번째 데이터"
            ),
            DailyCard(
                backgroundImageUrl = "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                routineId = 4,
                content = "일곱번째 데이터"
            ),
            DailyCard(
                backgroundImageUrl = "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                routineId = 5,
                content = "일곱번째 데이터"
            )
        )
    )

    private val _smallAccomplishmentCardList: MutableLiveData<List<DailyCard>> = MutableLiveData(
        mutableListOf(
            DailyCard(
                backgroundImageUrl = "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                routineId = 1,
                content = "여덟번째 데이터"
            ),
            DailyCard(
                backgroundImageUrl = "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                routineId = 2,
                content = "여덟번째 데이터"
            ),
            DailyCard(
                backgroundImageUrl = "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                routineId = 3,
                content = "여덟번째 데이터"
            ),
            DailyCard(
                backgroundImageUrl = "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                routineId = 4,
                content = "여덟번째 데이터"
            ),
            DailyCard(
                backgroundImageUrl = "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                routineId = 5,
                content = "여덟번째 데이터"
            )
        )
    )

    private val _preciousMeCardList: MutableLiveData<List<DailyCard>> = MutableLiveData(
        mutableListOf(
            DailyCard(
                backgroundImageUrl = "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                routineId = 1,
                content = "아홉번째 데이터"
            ),
            DailyCard(
                backgroundImageUrl = "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                routineId = 2,
                content = "아홉번째 데이터"
            ),
            DailyCard(
                backgroundImageUrl = "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                routineId = 3,
                content = "아홉번째 데이터"
            ),
            DailyCard(
                backgroundImageUrl = "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                routineId = 4,
                content = "아홉번째 데이터"
            ),
            DailyCard(
                backgroundImageUrl = "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                routineId = 5,
                content = "아홉번째 데이터"
            )
        )
    )

    private val _forRichCardList: MutableLiveData<List<DailyCard>> = MutableLiveData(
        mutableListOf(
            DailyCard(
                backgroundImageUrl = "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                routineId = 1,
                content = "열번째 데이터"
            ),
            DailyCard(
                backgroundImageUrl = "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                routineId = 2,
                content = "열번째 데이터"
            ),
            DailyCard(
                backgroundImageUrl = "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                routineId = 3,
                content = "열번째 데이터"
            ),
            DailyCard(
                backgroundImageUrl = "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                routineId = 4,
                content = "열번째 데이터"
            ),
            DailyCard(
                backgroundImageUrl = "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                routineId = 5,
                content = "열번째 데이터"
            )
        )
    )

    private val _readyImmerseCardList: MutableLiveData<List<DailyCard>> = MutableLiveData(
        mutableListOf(
            DailyCard(
                backgroundImageUrl = "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                routineId = 1,
                content = "열한번째 데이터"
            ),
            DailyCard(
                backgroundImageUrl = "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                routineId = 2,
                content = "열한번째 데이터"
            ),
            DailyCard(
                backgroundImageUrl = "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                routineId = 3,
                content = "열한번째 데이터"
            ),
            DailyCard(
                backgroundImageUrl = "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                routineId = 4,
                content = "열한번째 데이터"
            ),
            DailyCard(
                backgroundImageUrl = "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                routineId = 5,
                content = "열한번째 데이터"
            )
        )
    )

    private val _emptyCardList: MutableLiveData<List<DailyCard>> = MutableLiveData(
        mutableListOf(
            DailyCard(
                backgroundImageUrl = "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                routineId = 1,
                content = "열두번째 데이터"
            ),
            DailyCard(
                backgroundImageUrl = "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                routineId = 2,
                content = "열두번째 데이터"
            ),
            DailyCard(
                backgroundImageUrl = "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                routineId = 3,
                content = "열두번째 데이터"
            ),
            DailyCard(
                backgroundImageUrl = "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                routineId = 4,
                content = "열두번째 데이터"
            ),
            DailyCard(
                backgroundImageUrl = "https://www.urbanbrush.net/web/wp-content/uploads/edd/2022/12/urbanbrush-20221214144619159434.jpg",
                routineId = 5,
                content = "열두번째 데이터"
            )
        )
    )

    private val _themeDailyRoutineList: MutableLiveData<List<DailyRoutineAdd>> =
        MutableLiveData(
            mutableListOf(
                DailyRoutineAdd(
                    themeId = 1,
                    startNewDayCardList.value ?: emptyList()
                ),
                DailyRoutineAdd(
                    themeId = 2,
                    healthBodyCardList.value ?: emptyList()
                ),
                DailyRoutineAdd(
                    themeId = 3,
                    overcomeHelplessnessCardList.value ?: emptyList()
                ),
                DailyRoutineAdd(
                    themeId = 4,
                    restfulSleepCardList.value ?: emptyList()
                ),
                DailyRoutineAdd(
                    themeId = 5,
                    environmentalGuardCardList.value ?: emptyList()
                ),
                DailyRoutineAdd(
                    themeId = 6,
                    smallKindnessCardList.value ?: emptyList()
                ),
                DailyRoutineAdd(
                    themeId = 7,
                    gratitudeCardList.value ?: emptyList()
                ),
                DailyRoutineAdd(
                    themeId = 8,
                    smallAccomplishmentCardList.value ?: emptyList()
                ),
                DailyRoutineAdd(
                    themeId = 9,
                    preciousMeCardList.value ?: emptyList()
                ),
                DailyRoutineAdd(
                    themeId = 10,
                    forRichCardList.value ?: emptyList()
                ),
                DailyRoutineAdd(
                    themeId = 11,
                    readyImmerseCardList.value ?: emptyList()
                ),
                DailyRoutineAdd(
                    themeId = 12,
                    emptyCardList.value ?: emptyList()
                )
            )
        )


    private val themeDailyRoutineList: LiveData<List<DailyRoutineAdd>>
        get() = _themeDailyRoutineList

    fun getDailyCardListForId(themeId: Int): List<DailyRoutineAdd> {
        return themeDailyRoutineList.value?.filter { it.themeId == themeId } ?: emptyList()
    }

    private val _addDailyRoutine: MutableLiveData<AddRoutine> = MutableLiveData()
    val addDailyRoutine: LiveData<AddRoutine>
        get() = _addDailyRoutine

    fun postAddDailyRoutine(routineId: Int) {
        viewModelScope.launch {
            postAddDailyRoutineUseCase.invoke(routineId)
                .onSuccess { response ->
                    _addDailyRoutine.value = response
                    Timber.d("추가 성공")
                }
                .onFailure { throwable ->
                    Timber.e("$throwable")
                    Timber.d("추가 실패")
                }

        }
    }


}
