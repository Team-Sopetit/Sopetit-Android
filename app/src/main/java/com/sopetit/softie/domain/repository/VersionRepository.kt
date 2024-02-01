package com.sopetit.softie.domain.repository

import com.sopetit.softie.domain.entity.UpdateVersion

interface VersionRepository {

    suspend fun getUpdateVersion(): Result<UpdateVersion>
}
