package com.sopetit.softie.data.source

import com.sopetit.softie.data.entity.BaseResponse
import com.sopetit.softie.data.entity.response.HomeResponse
import com.sopetit.softie.data.service.MemberService
import javax.inject.Inject

class MemberDataSource @Inject constructor(
    private val memberService: MemberService
) {
    suspend fun getHome(): BaseResponse<HomeResponse> = memberService.getHome()
}
