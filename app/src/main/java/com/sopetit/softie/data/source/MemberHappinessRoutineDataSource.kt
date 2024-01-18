package com.sopetit.softie.data.source

import com.sopetit.softie.data.entity.BaseResponse
import com.sopetit.softie.data.entity.request.PostMemberHappyRoutineRequest
import com.sopetit.softie.data.entity.response.HappyProgressResponse
import com.sopetit.softie.data.entity.response.MemberHappyRoutinePostResponse
import com.sopetit.softie.data.service.MemberHappinessRoutineService
import javax.inject.Inject

class MemberHappinessRoutineDataSource @Inject constructor(
    private val memberHappinessRoutineService: MemberHappinessRoutineService
) {
    suspend fun getHappyProgress(): BaseResponse<HappyProgressResponse> =
        memberHappinessRoutineService.getHappyProgress()

    suspend fun postMemberHappyRoutine(request: PostMemberHappyRoutineRequest): BaseResponse<MemberHappyRoutinePostResponse> =
        memberHappinessRoutineService.postMemberHappyRoutine(request)

    suspend fun patchMemberHappyRoutine(routineId: Int): BaseResponse<Unit> =
        memberHappinessRoutineService.patchAchieveHappyRoutine(routineId)
}
