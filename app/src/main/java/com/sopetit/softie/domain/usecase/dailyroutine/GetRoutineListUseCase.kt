package com.sopetit.softie.domain.usecase.dailyroutine

import com.sopetit.softie.domain.repository.DailyRoutineRepository
import javax.inject.Inject

class GetRoutineListUseCase @Inject constructor(
    private val routineRepository: DailyRoutineRepository
) {

    suspend operator fun invoke(themeId: List<Int>) = routineRepository.getRoutineList(themeId)
}
