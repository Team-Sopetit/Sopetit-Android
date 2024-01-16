package com.sopetit.softie.data.service

import com.sopetit.softie.data.entity.BaseResponse
import com.sopetit.softie.data.entity.response.HomeResponse
import retrofit2.http.GET

interface MemberService {
    @GET("api/v1/members")
    suspend fun getHome(): BaseResponse<HomeResponse>
}
