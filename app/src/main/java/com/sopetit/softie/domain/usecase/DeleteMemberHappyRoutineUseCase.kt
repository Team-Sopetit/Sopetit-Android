package com.sopetit.softie.domain.usecase

import com.sopetit.softie.domain.repository.MemberHappinessRoutineRepository
import javax.inject.Inject

class DeleteMemberHappyRoutineUseCase @Inject constructor(
    private val memberHappinessRoutineRepository: MemberHappinessRoutineRepository
) {

    suspend operator fun invoke(routineId: Int) =
        memberHappinessRoutineRepository.deleteMemberHappyRoutine(routineId)
}
