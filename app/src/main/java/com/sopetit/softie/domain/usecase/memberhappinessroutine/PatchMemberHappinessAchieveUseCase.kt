package com.sopetit.softie.domain.usecase.memberhappinessroutine

import com.sopetit.softie.domain.repository.MemberHappinessRoutineRepository
import javax.inject.Inject

class PatchMemberHappinessAchieveUseCase @Inject constructor(
    private val achieveHappyRepository: MemberHappinessRoutineRepository
) {
    suspend operator fun invoke(routineId: Int) =
        achieveHappyRepository.patchMemberHappinessAchieve(routineId)
}
