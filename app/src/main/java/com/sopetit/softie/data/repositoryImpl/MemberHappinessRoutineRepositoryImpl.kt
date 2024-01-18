package com.sopetit.softie.data.repositoryImpl

import com.sopetit.softie.data.entity.request.PostMemberHappyRoutineRequest
import com.sopetit.softie.data.source.MemberHappinessRoutineDataSource
import com.sopetit.softie.domain.entity.HappyProgress
import com.sopetit.softie.domain.repository.MemberHappinessRoutineRepository
import javax.inject.Inject

class MemberHappinessRoutineRepositoryImpl @Inject constructor(
    private val memberHappinessRoutineDataSource: MemberHappinessRoutineDataSource
) : MemberHappinessRoutineRepository {
    override suspend fun getHappyProgress(): Result<HappyProgress> =
        runCatching { memberHappinessRoutineDataSource.getHappyProgress() }.map { response ->
            requireNotNull(response.data).toHappyProgress()
        }

    override suspend fun postMemberHappinessRoutine(request: PostMemberHappyRoutineRequest): Result<Int> =
        runCatching { memberHappinessRoutineDataSource.postMemberHappyRoutine(request) }.map { response ->
            requireNotNull(response.data).routineId
        }

    override suspend fun patchMemberHappinessAchieve(routineId: Int): Result<Unit> =
        runCatching { memberHappinessRoutineDataSource.patchMemberHappyRoutine(routineId) }
}
