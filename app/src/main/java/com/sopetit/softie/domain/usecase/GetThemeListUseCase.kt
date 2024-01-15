package com.sopetit.softie.domain.usecase

import com.sopetit.softie.domain.repository.DailyRoutineRepository
import javax.inject.Inject

class GetThemeListUseCase @Inject constructor(
    private val dailyRoutineRepository: DailyRoutineRepository
) {

    suspend operator fun invoke() = dailyRoutineRepository.getTheme()
}
