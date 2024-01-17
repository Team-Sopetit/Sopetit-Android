package com.sopetit.softie.domain.usecase

import com.sopetit.softie.domain.repository.DailyRoutineRepository
import javax.inject.Inject

class DeleteDailyRoutineUseCase @Inject constructor(
    private val deleteDailyRoutine: DailyRoutineRepository
) {
    suspend operator fun invoke(routineIdList: List<Int>) =
        deleteDailyRoutine.deleteDailyRoutine(routineIdList)
}
