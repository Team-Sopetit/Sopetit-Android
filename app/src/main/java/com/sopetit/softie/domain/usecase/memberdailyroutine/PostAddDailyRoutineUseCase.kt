package com.sopetit.softie.domain.usecase.memberdailyroutine

import com.sopetit.softie.data.entity.request.AddDailyRoutineRequest
import com.sopetit.softie.domain.repository.DailyRoutineRepository
import javax.inject.Inject

class PostAddDailyRoutineUseCase @Inject constructor(
    private val dailyRoutineRepository: DailyRoutineRepository
) {
    suspend operator fun invoke(request: AddDailyRoutineRequest) =
        dailyRoutineRepository.postAddDailyRoutine(request)
}
