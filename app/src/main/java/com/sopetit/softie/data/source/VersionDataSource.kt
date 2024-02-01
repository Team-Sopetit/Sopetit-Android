package com.sopetit.softie.data.source

import com.sopetit.softie.data.entity.BaseResponse
import com.sopetit.softie.data.entity.response.VersionResponse
import com.sopetit.softie.data.service.VersionService
import javax.inject.Inject

class VersionDataSource @Inject constructor(
    private val versionService: VersionService
) {

    suspend fun getUpdateVersion(): BaseResponse<VersionResponse> =
        versionService.getUpdateVersion()
}
