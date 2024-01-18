package com.sopetit.softie.domain.usecase

import com.sopetit.softie.domain.repository.MemberHappinessRoutineRepository
import javax.inject.Inject

class GetHappyProgressUseCase @Inject constructor(
    private val memberHappinessRoutineRepository: MemberHappinessRoutineRepository
) {
    suspend operator fun invoke() =
        memberHappinessRoutineRepository.getHappyProgress()
}
