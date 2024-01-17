package com.sopetit.softie.domain.repository

import com.sopetit.softie.domain.entity.HappyProgress

interface MemberHappinessRoutineRepository {
    suspend fun getHappyProgress(): Result<HappyProgress>
import com.sopetit.softie.data.entity.request.PostMemberHappyRoutineRequest

interface MemberHappinessRoutineRepository {
    suspend fun postMemberHappinessRoutine(request: PostMemberHappyRoutineRequest): Result<Int>
}
