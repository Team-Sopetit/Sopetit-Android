package com.sopetit.softie.ui.happyroutine.addlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopetit.softie.R
import com.sopetit.softie.domain.entity.HappyChip
import com.sopetit.softie.domain.entity.HappyContent

class HappyAddListViewModel : ViewModel() {
    private val _mockHappyChipList = MutableLiveData<List<HappyChip>>()
    val mockHappyChipList: LiveData<List<HappyChip>> = _mockHappyChipList

    private val _mockHappyContentList = MutableLiveData<List<HappyContent>>()
    val mockHappyContentList: LiveData<List<HappyContent>> = _mockHappyContentList

    private val _mockHappyContentListOne = MutableLiveData<List<HappyContent>>()
    val mockHappyContentListOne: LiveData<List<HappyContent>> = _mockHappyContentListOne

    private val _mockHappyContentListTwo = MutableLiveData<List<HappyContent>>()
    val mockHappyContentListTwo: LiveData<List<HappyContent>> = _mockHappyContentListTwo

    private val _mockHappyContentListThree = MutableLiveData<List<HappyContent>>()
    val mockHappyContentListThree: LiveData<List<HappyContent>> = _mockHappyContentListThree

    private val _mockHappyContentListFour = MutableLiveData<List<HappyContent>>()
    val mockHappyContentListFour: LiveData<List<HappyContent>> = _mockHappyContentListFour

    private val _mockHappyContentListFive = MutableLiveData<List<HappyContent>>()
    val mockHappyContentListFive: LiveData<List<HappyContent>> = _mockHappyContentListFive

    init {
        _mockHappyContentList.value = listOf<HappyContent>(
            HappyContent(
                routineId = 1,
                title = "관계쌓기",
                content = "성숙한 사랑을 만나기 위한",
                imageUrl = R.drawable.ic_happy_red,
            ),
            HappyContent(
                routineId = 2,
                title = "관계쌓기",
                content = "진정성 있는 관계를 만드는",
                imageUrl = R.drawable.ic_happy_red,
            ),
            HappyContent(
                routineId = 3,
                title = "한 걸음 성장",
                content = "나를 알고 진짜 목표를 세우는",
                imageUrl = R.drawable.ic_happy_orange,
            ),
            HappyContent(
                routineId = 4,
                title = "한 걸음 성장",
                content = "좋아하는, 잘하는 일을 찾아 가는",
                imageUrl = R.drawable.ic_happy_orange,
            ),
            HappyContent(
                routineId = 5,
                title = "잘 쉬어가기",
                content = "데이터가 아직 없습니다",
                imageUrl = R.drawable.ic_happy_green,
            ),
            HappyContent(
                routineId = 6,
                title = "새로운 나",
                content = "나를 알고 진짜 목표를 세우는",
                imageUrl = R.drawable.ic_happy_blue,
            ),
            HappyContent(
                routineId = 7,
                title = "마음 챙김",
                content = "데이터가 아직 없습니다",
                imageUrl = R.drawable.ic_happy_purple,
            ),
        )

        _mockHappyChipList.value = listOf<HappyChip>(
            HappyChip(
                themeId = 1,
                name = "전체",
            ),
            HappyChip(
                themeId = 2,
                name = "관계쌓기",
            ),
            HappyChip(
                themeId = 3,
                name = "한 걸음 성장",
            ),
            HappyChip(
                themeId = 4,
                name = "잘 쉬어가기",
            ),
            HappyChip(
                themeId = 5,
                name = "새로운 나",
            ),
            HappyChip(
                themeId = 6,
                name = "마음 챙김",
            )
        )

        _mockHappyContentListOne.value = listOf<HappyContent>(
            HappyContent(
                routineId = 1,
                title = "관계쌓기",
                content = "성숙한 사랑을 만나기 위한",
                imageUrl = R.drawable.ic_happy_red,
            ),
            HappyContent(
                routineId = 2,
                title = "관계쌓기",
                content = "진정성 있는 관계를 만드는",
                imageUrl = R.drawable.ic_happy_red,
            ),
        )

        _mockHappyContentListTwo.value = listOf<HappyContent>(
            HappyContent(
                routineId = 3,
                title = "한 걸음 성장",
                content = "나를 알고 진짜 목표를 세우는",
                imageUrl = R.drawable.ic_happy_orange,
            ),
            HappyContent(
                routineId = 4,
                title = "한 걸음 성장",
                content = "좋아하는, 잘하는 일을 찾아 가는",
                imageUrl = R.drawable.ic_happy_orange,
            ),
        )

        _mockHappyContentListThree.value = listOf<HappyContent>(
            HappyContent(
                routineId = 5,
                title = "잘 쉬어가기",
                content = "데이터가 아직 없습니다",
                imageUrl = R.drawable.ic_happy_green,
            ),
        )

        _mockHappyContentListFour.value = listOf<HappyContent>(
            HappyContent(
                routineId = 6,
                title = "새로운 나",
                content = "나를 알고 진짜 목표를 세우는",
                imageUrl = R.drawable.ic_happy_blue,
            ),
        )

        _mockHappyContentListFive.value = listOf<HappyContent>(
            HappyContent(
                routineId = 7,
                title = "마음 챙김",
                content = "데이터가 아직 없습니다",
                imageUrl = R.drawable.ic_happy_purple,
            ),
        )
    }
}
