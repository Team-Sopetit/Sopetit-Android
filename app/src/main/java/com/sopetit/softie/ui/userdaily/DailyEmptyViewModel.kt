package com.sopetit.softie.ui.userdaily

import androidx.lifecycle.ViewModel

class DailyEmptyViewModel : ViewModel() {
    val routineAddList = listOf<Int>(1, 2)

    companion object {
        const val NONE = 0
        const val FIRST_ROUTINE = 1
        const val SECOND_ROUTINE = 2
        const val THIRD_ROUTINE = 3
        const val FOURTH_ROUTINE = 4
        const val MAX_ROUTINE = 3
    }
}
