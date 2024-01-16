package com.sopetit.softie.ui.happyroutine.addlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
                name = "관계쌓기",
                nameColor = "#FFFF5D76",
                title = "성숙한 사랑을 만나기 위한",
                iconImageUrl = "https://raw.githubusercontent.com/Team-Sopetit/sopetit-image/c13153794106845de32863a6ecd38dd05b9480fb/routine/happiness/icon/relationship/icon.svg"
            ),
            HappyContent(
                routineId = 2,
                name = "관계쌓기",
                nameColor = "#FFFF5D76",
                title = "진정성 있는 관계를 만드는",
                iconImageUrl = "https://raw.githubusercontent.com/Team-Sopetit/sopetit-image/c13153794106845de32863a6ecd38dd05b9480fb/routine/happiness/icon/relationship/icon.svg"
            ),
            HappyContent(
                routineId = 3,
                name = "한 걸음 성장",
                nameColor = "#FFF27400",
                title = "나를 알고 진짜 목표를 세우는",
                iconImageUrl = "https://raw.githubusercontent.com/Team-Sopetit/sopetit-image/c13153794106845de32863a6ecd38dd05b9480fb/routine/happiness/icon/one_more_growth/icon.svg"
            ),
            HappyContent(
                routineId = 4,
                name = "한 걸음 성장",
                nameColor = "#FFF27400",
                title = "좋아하는, 잘하는 일을 찾아 가는",
                iconImageUrl = "https://raw.githubusercontent.com/Team-Sopetit/sopetit-image/c13153794106845de32863a6ecd38dd05b9480fb/routine/happiness/icon/one_more_growth/icon.svg"
            ),
            HappyContent(
                routineId = 5,
                name = "잘 쉬어가기",
                nameColor = "#FF3DB96F",
                title = "데이터가 아직 없습니다",
                iconImageUrl = "https://raw.githubusercontent.com/Team-Sopetit/sopetit-image/c13153794106845de32863a6ecd38dd05b9480fb/routine/happiness/icon/good_rest/icon.svg"
            ),
            HappyContent(
                routineId = 6,
                name = "새로운 나",
                nameColor = "#FF7B89D1",
                title = "나를 알고 진짜 목표를 세우는",
                iconImageUrl = "https://raw.githubusercontent.com/Team-Sopetit/sopetit-image/c13153794106845de32863a6ecd38dd05b9480fb/routine/happiness/icon/new_myself/icon.svg"
            ),
            HappyContent(
                routineId = 7,
                name = "마음 챙김",
                nameColor = "#FFBC73E6",
                title = "데이터가 아직 없습니다",
                iconImageUrl = "https://raw.githubusercontent.com/Team-Sopetit/sopetit-image/c13153794106845de32863a6ecd38dd05b9480fb/routine/happiness/icon/mindfulness/icon.svg"
            )
        )

        _HappyContentListOne.value = listOf<HappyContent>(
            HappyContent(
                routineId = 1,
                name = "관계쌓기",
                nameColor = "#FFFF5D76",
                title = "성숙한 사랑을 만나기 위한",
                iconImageUrl = "https://raw.githubusercontent.com/Team-Sopetit/sopetit-image/c13153794106845de32863a6ecd38dd05b9480fb/routine/happiness/icon/relationship/icon.svg"
            ),
            HappyContent(
                routineId = 2,
                name = "관계쌓기",
                nameColor = "#FFFF5D76",
                title = "진정성 있는 관계를 만드는",
                iconImageUrl = "https://raw.githubusercontent.com/Team-Sopetit/sopetit-image/c13153794106845de32863a6ecd38dd05b9480fb/routine/happiness/icon/relationship/icon.svg"
            )
        )

        _HappyContentListTwo.value = listOf<HappyContent>(
            HappyContent(
                routineId = 3,
                name = "한 걸음 성장",
                nameColor = "#FFF27400",
                title = "나를 알고 진짜 목표를 세우는",
                iconImageUrl = "https://raw.githubusercontent.com/Team-Sopetit/sopetit-image/c13153794106845de32863a6ecd38dd05b9480fb/routine/happiness/icon/one_more_growth/icon.svg"
            ),
            HappyContent(
                routineId = 4,
                name = "한 걸음 성장",
                nameColor = "#FFF27400",
                title = "좋아하는, 잘하는 일을 찾아 가는",
                iconImageUrl = "https://raw.githubusercontent.com/Team-Sopetit/sopetit-image/c13153794106845de32863a6ecd38dd05b9480fb/routine/happiness/icon/one_more_growth/icon.svg"
            )
        )

        _HappyContentListThree.value = listOf<HappyContent>(
            HappyContent(
                routineId = 5,
                name = "잘 쉬어가기",
                nameColor = "#FF3DB96F",
                title = "데이터가 아직 없습니다",
                iconImageUrl = "https://raw.githubusercontent.com/Team-Sopetit/sopetit-image/c13153794106845de32863a6ecd38dd05b9480fb/routine/happiness/icon/good_rest/icon.svg"
            )
        )

        _HappyContentListFour.value = listOf<HappyContent>(
            HappyContent(
                routineId = 6,
                name = "새로운 나",
                nameColor = "#FF7B89D1",
                title = "나를 알고 진짜 목표를 세우는",
                iconImageUrl = "https://raw.githubusercontent.com/Team-Sopetit/sopetit-image/c13153794106845de32863a6ecd38dd05b9480fb/routine/happiness/icon/new_myself/icon.svg"
            )
        )

        _HappyContentListFive.value = listOf<HappyContent>(
            HappyContent(
                routineId = 7,
                name = "마음 챙김",
                nameColor = "#FFBC73E6",
                title = "데이터가 아직 없습니다",
                iconImageUrl = "https://raw.githubusercontent.com/Team-Sopetit/sopetit-image/c13153794106845de32863a6ecd38dd05b9480fb/routine/happiness/icon/mindfulness/icon.svg"
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
