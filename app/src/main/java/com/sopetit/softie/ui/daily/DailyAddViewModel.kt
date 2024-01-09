package com.sopetit.softie.ui.daily

import androidx.lifecycle.ViewModel
import com.sopetit.softie.R
import com.sopetit.softie.domain.entity.DailyCard

class DailyAddViewModel : ViewModel() {
    val mockDailyList = listOf<DailyCard>(
        DailyCard(
            card = R.drawable.shape_gray_fill_20_rect,
            routine = ""
        ),
        DailyCard(
            card = R.drawable.shape_gray_fill_20_rect,
            routine = ""
        ),
        DailyCard(
            card = R.drawable.shape_gray_fill_20_rect,
            routine = ""
        ),
        DailyCard(
            card = R.drawable.shape_gray_fill_20_rect,
            routine = ""
        ),
        DailyCard(
            card = R.drawable.shape_gray_fill_20_rect,
            routine = ""
        )
    )
}
