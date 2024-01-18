package com.sopetit.softie.ui.happyroutine.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopetit.softie.domain.entity.HappyChip
import com.sopetit.softie.domain.entity.HappyContent
import com.sopetit.softie.domain.usecase.happyroutine.GetHappyChipUseCase
import com.sopetit.softie.domain.usecase.happyroutine.GetHappyContentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HappyAddListViewModel @Inject constructor(
    private val getHappyChipUseCase: GetHappyChipUseCase,
    private val getHappyContentUseCase: GetHappyContentUseCase,
) : ViewModel() {
    private val _happyChipResponse = MutableLiveData<List<HappyChip>>()
    val happyChipResponse: LiveData<List<HappyChip>> get() = _happyChipResponse

    private val _happyContentResponse = MutableLiveData<List<HappyContent>>()
    val happyContentResponse: LiveData<List<HappyContent>> get() = _happyContentResponse

    private val allChip = HappyChip(themeId = 0, name = "전체")

    init {
        _happyChipResponse.value = listOf(allChip)
    }

    fun getHappyChip() {
        viewModelScope.launch {
            getHappyChipUseCase()
                .onSuccess { response ->
                    val chipList = _happyChipResponse.value?.toMutableList() ?: mutableListOf()
                    chipList.addAll(response)
                    _happyChipResponse.value = chipList
                }
                .onFailure { throwable ->
                    Timber.e("$throwable")
                }
        }
    }

    fun getHappyContent(themeId: Int) {
        viewModelScope.launch {
            getHappyContentUseCase(themeId)
                .onSuccess { response ->
                    _happyContentResponse.value = response
                }
                .onFailure { throwable ->
                    Timber.e("$throwable")
                }
        }
    }
}
