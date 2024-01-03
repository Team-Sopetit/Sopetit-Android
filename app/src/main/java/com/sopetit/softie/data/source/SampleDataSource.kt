package com.sopetit.softie.data.source

import com.sopetit.softie.data.entity.BaseResponse
import com.sopetit.softie.data.entity.request.SampleRequest
import com.sopetit.softie.data.entity.response.SampleResponse
import com.sopetit.softie.data.service.SampleService
import javax.inject.Inject

class SampleDataSource
    @Inject
    constructor(
        private val sampleService: SampleService,
    ) {
        suspend fun postLogin(name: String): BaseResponse<SampleResponse> = sampleService.postLogin(SampleRequest(name))
    }
