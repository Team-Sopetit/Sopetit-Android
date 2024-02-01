package com.sopetit.softie.data.service

import com.sopetit.softie.data.entity.BaseResponse
import com.sopetit.softie.data.entity.request.PostMemberRequest
import com.sopetit.softie.data.entity.response.CottonCountResponse
import com.sopetit.softie.data.entity.response.HomeResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface MemberService {
    @GET("api/v1/members")
    suspend fun getHome(): BaseResponse<HomeResponse>

    @PATCH("api/v1/members/cotton/{cottonType}")
    suspend fun patchCotton(@Path("cottonType") cottonType: String): BaseResponse<CottonCountResponse>

    @POST("/api/v1/members")
    suspend fun postMember(@Body request: PostMemberRequest): BaseResponse<Unit>
}
