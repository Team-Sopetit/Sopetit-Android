package com.sopetit.softie.domain.repository

import com.sopetit.softie.data.entity.request.PostMemberHappyRoutineRequest

interface MemberHappinessRoutineRepository {
    suspend fun postMemberHappinessRoutine(request: PostMemberHappyRoutineRequest): Result<Int>
}
