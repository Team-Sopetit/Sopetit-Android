package com.sopetit.softie.data.source

import com.sopetit.softie.data.entity.BaseResponse
import com.sopetit.softie.data.entity.response.HappyProgressResponse
import com.sopetit.softie.data.service.MemberHappinessRoutineService
import javax.inject.Inject

class MemberHappinessRoutineDataSource @Inject constructor(
    private val memberHappinessRoutineService: MemberHappinessRoutineService
) {
    /*suspend fun postMemberHappyRoutine(request: PostMemberHappyRoutineRequest): BaseResponse<MemberHappyRoutinePostResponse> =
        memberHappinessRoutineService.postMemberHappyRoutine(request)*/

    suspend fun getHappyProgress(): BaseResponse<HappyProgressResponse> =
        memberHappinessRoutineService.getHappyProgress()
}
