package com.sopetit.softie.data.repositoryImpl

import com.sopetit.softie.data.entity.request.PostMemberHappyRoutineRequest
import com.sopetit.softie.data.source.MemberHappinessRoutineDataSource
import com.sopetit.softie.domain.repository.MemberHappinessRoutineRepository
import javax.inject.Inject

class MemberHappinessRoutineRepositoryImpl @Inject constructor(
    private val memberHappinessRoutineDataSource: MemberHappinessRoutineDataSource
) : MemberHappinessRoutineRepository {
    override suspend fun postMemberHappinessRoutine(request: PostMemberHappyRoutineRequest): Result<Int> =
        runCatching { memberHappinessRoutineDataSource.postMemberHappyRoutine(request) }.map {
            requireNotNull(it.data).routineId
        }
}
