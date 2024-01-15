package com.sopetit.softie.ui.happyroutine.progress

import androidx.lifecycle.ViewModel
import com.sopetit.softie.R
import com.sopetit.softie.domain.entity.HappyProgress

class HappyProgressViewModel : ViewModel() {

    val mockHappyProgress: HappyProgress = HappyProgress(
        routineId = 1,
        imageUrl = R.drawable.ic_happycard_up_purple,
        title = "성숙한 사람을 만나기 위한",
        content = "이상형의 특성 10가지 적어보고,\n절대 포기 못할 2가지와\n나와 비슷한 3가지 찾아보기 앞면",
        detailTitle = "이상형의 특성 10가지 적어보고,\n절대 포기 못할 2가지와\n나와 비슷한 3가지 찾아보기 뒷면",
        detailContent = "평소에 바빠서 연락하지 못한 사람이 있다면 안부인사 개인톡을 보내 봐. 꼭 만나서 밥을 먹거나 하지 않아도 연락 한 통이 나와 그 사람을 연결하는 방법이 될 수 있어. 난 너를 믿",
        detailTime = "5~10분",
        detailPlace = "회사 옥상, 점심식사 후 돌아가는 길"
    )
}
