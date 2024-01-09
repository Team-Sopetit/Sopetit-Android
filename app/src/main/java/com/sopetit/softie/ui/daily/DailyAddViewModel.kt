package com.sopetit.softie.ui.daily

import androidx.lifecycle.ViewModel
import com.sopetit.softie.R
import com.sopetit.softie.domain.entity.DailyCard

class DailyAddViewModel : ViewModel() {
    val mockDailyList = listOf<DailyCard>(
        DailyCard(
            card = R.drawable.shape_gray_fill_20_rect,
            routine = "가짜 데이터"
        ),
        DailyCard(
            card = R.drawable.shape_gray_fill_20_rect,
            routine = "진짜 데이터"
        ),
        DailyCard(
            card = R.drawable.shape_gray_fill_20_rect,
            routine = "가짜 데이터"
        ),
        DailyCard(
            card = R.drawable.shape_gray_fill_20_rect,
            routine = "가짜 데이터"
        ),
        DailyCard(
            card = R.drawable.shape_gray_fill_20_rect,
            routine = "가짜 데이터"
        )
    )
}
