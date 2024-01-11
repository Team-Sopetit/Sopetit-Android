package com.sopetit.softie.ui.dailyroutine.dailyroutineadd

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopetit.softie.R
import com.sopetit.softie.domain.entity.DailyCard
import com.sopetit.softie.domain.entity.Theme

class DailyRoutineAddThemeViewModel : ViewModel() {
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
                card = R.drawable.shape_gray_fill_20_rect,
                routineId = 1,
                content = "첫번째 데이터"
            ),
            DailyCard(
                card = R.drawable.shape_gray_fill_20_rect,
                routineId = 2,
                content = "첫번째 데이터"
            ),
            DailyCard(
                card = R.drawable.shape_gray_fill_20_rect,
                routineId = 3,
                content = "첫번째 데이터"
            ),
            DailyCard(
                card = R.drawable.shape_gray_fill_20_rect,
                routineId = 4,
                content = "첫번째 데이터"
            ),
            DailyCard(
                card = R.drawable.shape_gray_fill_20_rect,
                routineId = 5,
                content = "첫번째 데이터"
            )
        )
    )

    val mockDailyFirstCardList: LiveData<List<DailyCard>>
        get() = _mockDailyFirstCardList

//    private val _mockDailySecondCardList: MutableLiveData<List<DailyCard>> = MutableLiveData(
//        mutableListOf(
//            DailyCard(
//                card = R.drawable.shape_gray_fill_20_rect,
//                routineId = 1,
//                content = "두번째 데이터"
//            ),
//            DailyCard(
//                card = R.drawable.shape_gray_fill_20_rect,
//                routineId = 2,
//                content = "두번째 데이터"
//            ),
//            DailyCard(
//                card = R.drawable.shape_gray_fill_20_rect,
//                routineId = 3,
//                content = "두번째 데이터"
//            ),
//            DailyCard(
//                card = R.drawable.shape_gray_fill_20_rect,
//                routineId = 4,
//                content = "두번째 데이터"
//            ),
//            DailyCard(
//                card = R.drawable.shape_gray_fill_20_rect,
//                routineId = 5,
//                content = "두번째 데이터"
//            )
//        )
//    )

//    val mockDailySecondCardList: LiveData<List<DailyCard>>
//        get() = _mockDailySecondCardList
//
//    private val _mockDailyThirdCardList: MutableLiveData<List<DailyCard>> = MutableLiveData(
//        mutableListOf(
//            DailyCard(
//                card = R.drawable.shape_gray_fill_20_rect,
//                routineId = 1,
//                content = "세번째 데이터"
//            ),
//            DailyCard(
//                card = R.drawable.shape_gray_fill_20_rect,
//                routineId = 2,
//                content = "세번째 데이터"
//            ),
//            DailyCard(
//                card = R.drawable.shape_gray_fill_20_rect,
//                routineId = 3,
//                content = "세번째 데이터"
//            ),
//            DailyCard(
//                card = R.drawable.shape_gray_fill_20_rect,
//                routineId = 4,
//                content = "세번째 데이터"
//            ),
//            DailyCard(
//                card = R.drawable.shape_gray_fill_20_rect,
//                routineId = 5,
//                content = "세번째 데이터"
//            )
//        )
//    )
//
//    val mockDailyThirdCardList: LiveData<List<DailyCard>>
//        get() = _mockDailyThirdCardList
//
//    private val _mockDailyForthCardList: MutableLiveData<List<DailyCard>> = MutableLiveData(
//        mutableListOf(
//            DailyCard(
//                card = R.drawable.shape_gray_fill_20_rect,
//                routineId = 1,
//                content = "네번째 데이터"
//            ),
//            DailyCard(
//                card = R.drawable.shape_gray_fill_20_rect,
//                routineId = 2,
//                content = "네번째 데이터"
//            ),
//            DailyCard(
//                card = R.drawable.shape_gray_fill_20_rect,
//                routineId = 3,
//                content = "네번째 데이터"
//            ),
//            DailyCard(
//                card = R.drawable.shape_gray_fill_20_rect,
//                routineId = 4,
//                content = "네번째 데이터"
//            ),
//            DailyCard(
//                card = R.drawable.shape_gray_fill_20_rect,
//                routineId = 5,
//                content = "네번째 데이터"
//            )
//        )
//    )
//
//    val mockDailyForthCardList: LiveData<List<DailyCard>>
//        get() = _mockDailyForthCardList
//
//    private val _mockDailyFifthCardList: MutableLiveData<List<DailyCard>> = MutableLiveData(
//        mutableListOf(
//            DailyCard(
//                card = R.drawable.shape_gray_fill_20_rect,
//                routineId = 1,
//                content = "세번째 데이터"
//            ),
//            DailyCard(
//                card = R.drawable.shape_gray_fill_20_rect,
//                routineId = 2,
//                content = "세번째 데이터"
//            ),
//            DailyCard(
//                card = R.drawable.shape_gray_fill_20_rect,
//                routineId = 3,
//                content = "세번째 데이터"
//            ),
//            DailyCard(
//                card = R.drawable.shape_gray_fill_20_rect,
//                routineId = 4,
//                content = "세번째 데이터"
//            ),
//            DailyCard(
//                card = R.drawable.shape_gray_fill_20_rect,
//                routineId = 5,
//                content = "세번째 데이터"
//            )
//        )
//    )
//
//    val mockDailyFifthCardList: LiveData<List<DailyCard>>
//        get() = _mockDailyThirdCardList
}
