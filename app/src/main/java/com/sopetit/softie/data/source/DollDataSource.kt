package com.sopetit.softie.data.source

import com.sopetit.softie.data.entity.BaseResponse
import com.sopetit.softie.data.entity.response.DollResponse
import com.sopetit.softie.data.service.DollService
import javax.inject.Inject

class DollDataSource @Inject constructor(
    private val dollService: DollService
) {

    suspend fun getDoll(type: String): BaseResponse<DollResponse> = dollService.getDoll(type)
}
