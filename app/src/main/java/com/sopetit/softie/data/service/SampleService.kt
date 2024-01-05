package com.sopetit.softie.data.service

import com.sopetit.softie.data.entity.BaseResponse
import com.sopetit.softie.data.entity.request.SampleRequest
import com.sopetit.softie.data.entity.response.SampleResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface SampleService {
    @POST
    suspend fun postLogin(
        @Body body: SampleRequest,
    ): BaseResponse<SampleResponse>
}
