package com.sopetit.softie.data.service

import com.sopetit.softie.data.entity.BaseResponse
import com.sopetit.softie.data.entity.request.PostMemberHappyRoutineRequest
import com.sopetit.softie.data.entity.response.MemberHappyRoutinePostResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface MemberHappinessRoutineService {
    @POST("api/v1/routines/happiness/member")
    suspend fun postMemberHappyRoutine(@Body request: PostMemberHappyRoutineRequest): BaseResponse<MemberHappyRoutinePostResponse>
}
