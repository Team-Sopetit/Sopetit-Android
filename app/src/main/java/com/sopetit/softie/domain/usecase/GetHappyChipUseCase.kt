package com.sopetit.softie.domain.usecase

import com.sopetit.softie.domain.repository.HappinessRoutineRepository
import javax.inject.Inject

class GetHappyChipUseCase @Inject constructor(
    private val happinessRoutineRepository: HappinessRoutineRepository
) {
    suspend operator fun invoke() = happinessRoutineRepository.getHappyChip()
}
