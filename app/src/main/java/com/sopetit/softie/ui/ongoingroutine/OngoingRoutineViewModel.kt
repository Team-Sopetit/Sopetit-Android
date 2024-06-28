package com.sopetit.softie.ui.ongoingroutine

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class OngoingRoutineViewModel @Inject constructor(

) : ViewModel() {
    private val _date = MutableLiveData<String>("")
    val date : LiveData<String> get() = _date
    init {
        getDate()
        // TODO get dailyRoutine, challengeRoutine
    }

    private fun getDate(){
        val currentDate = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("yyyy년 M월 d일", Locale.getDefault())
        _date.value = dateFormat.format(currentDate)
    }
}
