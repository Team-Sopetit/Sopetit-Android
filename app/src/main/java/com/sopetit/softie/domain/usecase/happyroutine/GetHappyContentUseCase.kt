package com.sopetit.softie.domain.usecase.happyroutine

import com.sopetit.softie.domain.repository.HappinessRoutineRepository
import javax.inject.Inject

class GetHappyContentUseCase @Inject constructor(
    private val happinessRoutineRepository: HappinessRoutineRepository
) {
    suspend operator fun invoke(themeId: Int) =
        happinessRoutineRepository.getHappyContent(themeId)
}
