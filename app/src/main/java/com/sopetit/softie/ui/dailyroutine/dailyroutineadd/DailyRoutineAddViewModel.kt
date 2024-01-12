package com.sopetit.softie.ui.dailyroutine.dailyroutineadd

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopetit.softie.domain.entity.DailyCard
import com.sopetit.softie.domain.entity.Theme

class DailyRoutineAddViewModel : ViewModel() {
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

    private val _mockDailyFirstCardList: MutableLiveData<List<DailyCard>> = MutableLiveData(
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

    val mockDailyFirstCardList: LiveData<List<DailyCard>>
        get() = _mockDailyFirstCardList

    private val _mockDailySecondCardList: MutableLiveData<List<DailyCard>> = MutableLiveData(
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

    val mockDailySecondCardList: LiveData<List<DailyCard>>
        get() = _mockDailySecondCardList

    private val _mockDailyThirdCardList: MutableLiveData<List<DailyCard>> = MutableLiveData(
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

    val mockDailyThirdCardList: LiveData<List<DailyCard>>
        get() = _mockDailyThirdCardList

    private val _mockDailyFourthCardList: MutableLiveData<List<DailyCard>> = MutableLiveData(
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

    val mockDailyFourthCardList: LiveData<List<DailyCard>>
        get() = _mockDailyFourthCardList

    private val _mockDailyFifthCardList: MutableLiveData<List<DailyCard>> = MutableLiveData(
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

    val mockDailyFifthCardList: LiveData<List<DailyCard>>
        get() = _mockDailyFifthCardList

    private val _mockDailySixthCardList: MutableLiveData<List<DailyCard>> = MutableLiveData(
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

    val mockDailySixthCardList: LiveData<List<DailyCard>>
        get() = _mockDailySixthCardList

    private val _mockDailySeventhCardList: MutableLiveData<List<DailyCard>> = MutableLiveData(
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

    val mockDailySeventhCardList: LiveData<List<DailyCard>>
        get() = _mockDailySeventhCardList

    private val _mockDailyEighthCardList: MutableLiveData<List<DailyCard>> = MutableLiveData(
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

    val mockDailyEighthCardList: LiveData<List<DailyCard>>
        get() = _mockDailyEighthCardList

    private val _mockDailyNinthCardList: MutableLiveData<List<DailyCard>> = MutableLiveData(
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

    val mockDailyNinthCardList: LiveData<List<DailyCard>>
        get() = _mockDailyNinthCardList

    private val _mockDailyTenthCardList: MutableLiveData<List<DailyCard>> = MutableLiveData(
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

    val mockDailyTenthCardList: LiveData<List<DailyCard>>
        get() = _mockDailyTenthCardList

    private val _mockDailyEleventhCardList: MutableLiveData<List<DailyCard>> = MutableLiveData(
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

    val mockDailyEleventhCardList: LiveData<List<DailyCard>>
        get() = _mockDailyEleventhCardList

    private val _mockDailyTwelfthCardList: MutableLiveData<List<DailyCard>> = MutableLiveData(
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

    val mockDailyTwelfthCardList: LiveData<List<DailyCard>>
        get() = _mockDailyTwelfthCardList
}
