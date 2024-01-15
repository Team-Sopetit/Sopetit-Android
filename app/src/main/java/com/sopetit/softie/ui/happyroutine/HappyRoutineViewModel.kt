package com.sopetit.softie.ui.happyroutine

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopetit.softie.R
import com.sopetit.softie.domain.entity.HappyDoll

class HappyRoutineViewModel : ViewModel() {

    private val _mockHappyDollList: MutableLiveData<List<HappyDoll>> = MutableLiveData(
        mutableListOf(
            HappyDoll(
                faceImageUrl = R.drawable.ic_bear_face_1
            ),
            HappyDoll(
                faceImageUrl = R.drawable.ic_bear_face_2
            ),
            HappyDoll(
                faceImageUrl = R.drawable.ic_bear_face_3
            ),
            HappyDoll(
                faceImageUrl = R.drawable.ic_bear_face_4
            ),
        )
    )

    val mockHappyDollList: LiveData<List<HappyDoll>> get() = _mockHappyDollList

    fun getFaceImageUrl(type: String): Int {
        return when (type) {
            "BROWN" -> R.drawable.ic_bear_face_1
            "GRAY" -> R.drawable.ic_bear_face_2
            "PANDA" -> R.drawable.ic_bear_face_3
            "RED" -> R.drawable.ic_bear_face_4
            else -> R.drawable.ic_bear_face_1
        }
    }
}
