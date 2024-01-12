package com.sopetit.softie.ui.happyroutine.adddetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopetit.softie.R
import com.sopetit.softie.domain.entity.HappyCard

class HappyDetailCardViewModel : ViewModel() {

    private val _mockHappyCardList: MutableLiveData<List<HappyCard>> = MutableLiveData(
        mutableListOf(
            HappyCard(
                name = "관계 쌓기",
                nameColor = "#CEB59E",
                title = "성숙한 사랑을 만나기 위한 행복 루틴",
                iconImageUrl = R.drawable.ic_happy_bling_blue,
                routines = listOf(
                    HappyCard.Routines(
                        routineId = 1,
                        cardImageUrl = R.drawable.ic_happycard_up_blue,
                        content = "서점에 가서 랜덤 책 골라서 공중제비 돌기",
                        detailTitle = "이상형의 특성 10가지 적어보고,\n절대 포기 못할 2가지와\n나와 비슷한 3가지 찾아보기",
                        detailContent = "평소에 바빠서 연락하지 못한 사람이 있다면 \n안부인사 개인톡을 보내 봐. 꼭 만나서 밥을 \n먹거나 하지 않아도 연락 한 통이 나와 그 사\n람을 연결하는 방법이 될 수 있어",
                        detailTime = "5~10분",
                        detailPlace = "회사 옥상, 점심식사 후 돌아가는 길"
                    ), HappyCard.Routines(
                        routineId = 2,
                        cardImageUrl = R.drawable.ic_happycard_up_red,
                        content = "길가다가 박수치기",
                        detailTitle = "홍대에서 가장 맛있는\n음식점을 찾아보아요\n나와 비슷한 3가지 찾아보기",
                        detailContent = "우리나라 좋은나라\n안부인사 개인톡을 보내 봐. 꼭 만나서 밥을 \n먹거나 하지 않아도 연락 한 통이 나와 그 사\n람을 연결하는 방법이 될 수 있어",
                        detailTime = "6~30분",
                        detailPlace = "홍대 옥상, 저녁식사 후 돌아가는 길"
                    ), HappyCard.Routines(
                        routineId = 3,
                        cardImageUrl = R.drawable.ic_happycard_up_orange,
                        content = "클럽에서 짜장면 시키기",
                        detailTitle = "스포츠몬스터로 가서,\n절대 포기 못할 2가지와\n나와 비슷한 3가지 찾아보기",
                        detailContent = "살다보면 다 그런거래요 \n안부인사 개인톡을 보내 봐. 꼭 만나서 밥을 \n먹거나 하지 않아도 연락 한 통이 나와 그 사\n람을 연결하는 방법이 될 수 있어",
                        detailTime = "10~40분",
                        detailPlace = "일산 킨텍스"
                    )
                )
            ),
            HappyCard(
                name = "두 번째 카드",
                nameColor = "#FFF27400",
                title = "두 번째 카드 제목",
                iconImageUrl = R.drawable.ic_happy_bling_purple,
                routines = listOf(
                    HappyCard.Routines(
                        routineId = 2,
                        cardImageUrl = R.drawable.ic_happycard_up_purple,
                        content = "두 번째 카드의 콘텐츠",
                        detailTitle = "두 번째 카드의 디테일 제목",
                        detailContent = "두 번째 카드의 디테일 내용",
                        detailTime = "두 번째 카드의 디테일 시간",
                        detailPlace = "두 번째 카드의 디테일 장소"
                    )
                )
            ),
            HappyCard(
                name = "세 번째 카드",
                nameColor = "#FFFFFF",
                title = "세 번째 카드 제목",
                iconImageUrl = R.drawable.ic_happy_bling_orange,
                routines = listOf(
                    HappyCard.Routines(
                        routineId = 2,
                        cardImageUrl = R.drawable.ic_happycard_up_orange,
                        content = "세 번째 카드의 콘텐츠",
                        detailTitle = "세 번째 카드의 디테일 제목",
                        detailContent = "세 번째 카드의 디테일 내용",
                        detailTime = "세 번째 카드의 디테일 시간",
                        detailPlace = "세 번째 카드의 디테일 장소"
                    )
                )
            ),
        )
    )

    val mockHappyCardList: LiveData<List<HappyCard>>
        get() = _mockHappyCardList

}
