package com.sopetit.softie.domain.usecase.memberhappinessroutine

import com.sopetit.softie.data.entity.request.PostMemberHappyRoutineRequest
import com.sopetit.softie.domain.repository.MemberHappinessRoutineRepository
import javax.inject.Inject

class PostMemberHappyRoutineUseCase @Inject constructor(
    private val memberHappinessRoutineRepository: MemberHappinessRoutineRepository
) {
    suspend operator fun invoke(request: PostMemberHappyRoutineRequest) =
        memberHappinessRoutineRepository.postMemberHappinessRoutine(request)
}
