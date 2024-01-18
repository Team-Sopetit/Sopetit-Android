package com.sopetit.softie.ui.happyroutine.complete

import androidx.lifecycle.ViewModel
import com.sopetit.softie.domain.usecase.GetBearTypeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HappyRoutineCompleteViewModel @Inject constructor(
    private val getBearTypeUseCase: GetBearTypeUseCase
) : ViewModel() {

    fun getBearType(): String {
        return getBearTypeUseCase()
    }
}
