package com.sopetit.softie.domain.repository

import com.sopetit.softie.data.entity.request.PostMemberHappyRoutineRequest
import com.sopetit.softie.domain.entity.HappyProgress

interface MemberHappinessRoutineRepository {
    suspend fun getHappyProgress(): Result<HappyProgress>
    suspend fun postMemberHappinessRoutine(request: PostMemberHappyRoutineRequest): Result<Int>

}
