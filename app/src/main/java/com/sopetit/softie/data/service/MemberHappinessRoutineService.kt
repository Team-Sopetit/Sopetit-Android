package com.sopetit.softie.data.service

import com.sopetit.softie.data.entity.BaseResponse
import com.sopetit.softie.data.entity.response.HappyProgressResponse
import retrofit2.http.GET

interface MemberHappinessRoutineService {
    @GET("api/v1/routines/happiness/member")
    suspend fun getHappyProgress(): BaseResponse<HappyProgressResponse>
}
