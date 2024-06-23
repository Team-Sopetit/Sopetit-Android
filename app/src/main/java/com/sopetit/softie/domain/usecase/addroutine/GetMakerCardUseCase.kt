package com.sopetit.softie.domain.usecase.addroutine

import com.sopetit.softie.domain.entity.MakerCard
import com.sopetit.softie.domain.repository.AddRoutineRepository
import javax.inject.Inject

class GetMakerCardUseCase @Inject constructor(
    private val addRoutineRepository: AddRoutineRepository
) {
    suspend operator fun invoke(): Result<List<MakerCard>> {
        return addRoutineRepository.getMakerCard()
    }
}

