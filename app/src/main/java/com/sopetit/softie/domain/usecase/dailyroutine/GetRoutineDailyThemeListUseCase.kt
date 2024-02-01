package com.sopetit.softie.domain.usecase.dailyroutine

import com.sopetit.softie.domain.repository.DailyRoutineRepository
import javax.inject.Inject

class GetRoutineDailyThemeListUseCase @Inject constructor(
    private val dailyRoutineRepository: DailyRoutineRepository
) {
    suspend operator fun invoke(themeId: Int) =
        dailyRoutineRepository.getRoutineDailyThemeList(themeId)
}
