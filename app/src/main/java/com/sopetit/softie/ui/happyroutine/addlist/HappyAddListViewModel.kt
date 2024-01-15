package com.sopetit.softie.ui.happyroutine.addlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopetit.softie.R
import com.sopetit.softie.domain.entity.HappyChip
import com.sopetit.softie.domain.entity.HappyContent
import com.sopetit.softie.domain.usecase.GetHappyChipUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HappyAddListViewModel @Inject constructor(
    private val getHappyChipUseCase: GetHappyChipUseCase
) : ViewModel() {
    private val _happyChipResponse = MutableLiveData<List<HappyChip>>()
    val happyChipResponse: LiveData<List<HappyChip>> get() = _happyChipResponse

    private val _HappyContentList = MutableLiveData<List<HappyContent>>()
    val HappyContentList: LiveData<List<HappyContent>> = _HappyContentList

    private val _HappyContentListOne = MutableLiveData<List<HappyContent>>()
    val HappyContentListOne: LiveData<List<HappyContent>> = _HappyContentListOne

    private val _HappyContentListTwo = MutableLiveData<List<HappyContent>>()
    val HappyContentListTwo: LiveData<List<HappyContent>> = _HappyContentListTwo

    private val _HappyContentListThree = MutableLiveData<List<HappyContent>>()
    val HappyContentListThree: LiveData<List<HappyContent>> = _HappyContentListThree

    private val _HappyContentListFour = MutableLiveData<List<HappyContent>>()
    val HappyContentListFour: LiveData<List<HappyContent>> = _HappyContentListFour

    private val _HappyContentListFive = MutableLiveData<List<HappyContent>>()
    val HappyContentListFive: LiveData<List<HappyContent>> = _HappyContentListFive

    init {
        _HappyContentList.value = listOf<HappyContent>(
            HappyContent(
                routineId = 1,
                title = "관계쌓기",
                content = "성숙한 사랑을 만나기 위한",
                imageUrl = R.drawable.ic_happy_red
            ),
            HappyContent(
                routineId = 2,
                title = "관계쌓기",
                content = "진정성 있는 관계를 만드는",
                imageUrl = R.drawable.ic_happy_red
            ),
            HappyContent(
                routineId = 3,
                title = "한 걸음 성장",
                content = "나를 알고 진짜 목표를 세우는",
                imageUrl = R.drawable.ic_happy_orange
            ),
            HappyContent(
                routineId = 4,
                title = "한 걸음 성장",
                content = "좋아하는, 잘하는 일을 찾아 가는",
                imageUrl = R.drawable.ic_happy_orange
            ),
            HappyContent(
                routineId = 5,
                title = "잘 쉬어가기",
                content = "데이터가 아직 없습니다",
                imageUrl = R.drawable.ic_happy_green
            ),
            HappyContent(
                routineId = 6,
                title = "새로운 나",
                content = "나를 알고 진짜 목표를 세우는",
                imageUrl = R.drawable.ic_happy_blue
            ),
            HappyContent(
                routineId = 7,
                title = "마음 챙김",
                content = "데이터가 아직 없습니다",
                imageUrl = R.drawable.ic_happy_purple
            )
        )

        _HappyContentListOne.value = listOf<HappyContent>(
            HappyContent(
                routineId = 1,
                title = "관계쌓기",
                content = "성숙한 사랑을 만나기 위한",
                imageUrl = R.drawable.ic_happy_red
            ),
            HappyContent(
                routineId = 2,
                title = "관계쌓기",
                content = "진정성 있는 관계를 만드는",
                imageUrl = R.drawable.ic_happy_red
            )
        )

        _HappyContentListTwo.value = listOf<HappyContent>(
            HappyContent(
                routineId = 3,
                title = "한 걸음 성장",
                content = "나를 알고 진짜 목표를 세우는",
                imageUrl = R.drawable.ic_happy_orange
            ),
            HappyContent(
                routineId = 4,
                title = "한 걸음 성장",
                content = "좋아하는, 잘하는 일을 찾아 가는",
                imageUrl = R.drawable.ic_happy_orange
            )
        )

        _HappyContentListThree.value = listOf<HappyContent>(
            HappyContent(
                routineId = 5,
                title = "잘 쉬어가기",
                content = "데이터가 아직 없습니다",
                imageUrl = R.drawable.ic_happy_green
            )
        )

        _HappyContentListFour.value = listOf<HappyContent>(
            HappyContent(
                routineId = 6,
                title = "새로운 나",
                content = "나를 알고 진짜 목표를 세우는",
                imageUrl = R.drawable.ic_happy_blue
            )
        )

        _HappyContentListFive.value = listOf<HappyContent>(
            HappyContent(
                routineId = 7,
                title = "마음 챙김",
                content = "데이터가 아직 없습니다",
                imageUrl = R.drawable.ic_happy_purple
            )
        )
    }

    fun getHappyChip() {
        viewModelScope.launch {
            getHappyChipUseCase()
                .onSuccess { response ->
                    _happyChipResponse.value = response
                }
                .onFailure { throwable ->
                    Timber.e("$throwable")
                }
        }
    }

}
