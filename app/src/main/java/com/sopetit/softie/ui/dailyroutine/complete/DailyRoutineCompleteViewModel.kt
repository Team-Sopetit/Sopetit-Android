package com.sopetit.softie.ui.dailyroutine.complete

import androidx.lifecycle.ViewModel
import com.sopetit.softie.domain.usecase.local.GetBearTypeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DailyRoutineCompleteViewModel @Inject constructor(
    private val getBearTypeUseCase: GetBearTypeUseCase
) : ViewModel() {

    fun getBearType(): String {
        return getBearTypeUseCase()
    }
}
