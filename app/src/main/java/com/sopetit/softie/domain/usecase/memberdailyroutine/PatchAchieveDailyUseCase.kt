package com.sopetit.softie.domain.usecase.memberdailyroutine

import com.sopetit.softie.domain.repository.DailyRoutineRepository
import javax.inject.Inject

class PatchAchieveDailyUseCase @Inject constructor(
    private val achieveDailyRepository: DailyRoutineRepository
) {
    suspend operator fun invoke(routineId: Int) =
        achieveDailyRepository.patchAchieveDaily(routineId)
}
