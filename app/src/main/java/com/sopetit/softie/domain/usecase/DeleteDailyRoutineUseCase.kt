package com.sopetit.softie.domain.usecase

import com.sopetit.softie.domain.repository.DailyRoutineRepository
import javax.inject.Inject

class DeleteDailyRoutineUseCase @Inject constructor(
    private val deleteDailyRoutine: DailyRoutineRepository
) {
    suspend operator fun invoke(routineId: Int) =
        deleteDailyRoutine.deleteDailyRoutine(routineId)
}
