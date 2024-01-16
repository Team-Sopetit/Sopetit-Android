package com.sopetit.softie.data.service

import com.sopetit.softie.data.entity.BaseResponse
import com.sopetit.softie.data.entity.response.CottonCountResponse
import com.sopetit.softie.data.entity.response.HomeResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MemberService {
    @GET("api/v1/members")
    suspend fun getHome(): BaseResponse<HomeResponse>

    @GET("api/v1/members/{cottonType}")
    suspend fun patchCotton(@Path("cottonType") cottonType: String): BaseResponse<CottonCountResponse>
}
