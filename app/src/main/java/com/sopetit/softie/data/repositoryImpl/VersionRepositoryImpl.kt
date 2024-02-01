package com.sopetit.softie.data.repositoryImpl

import com.sopetit.softie.data.source.VersionDataSource
import com.sopetit.softie.domain.entity.UpdateVersion
import com.sopetit.softie.domain.repository.VersionRepository
import javax.inject.Inject

class VersionRepositoryImpl @Inject constructor(
    private val versionDataSource: VersionDataSource
) : VersionRepository {
    override suspend fun getUpdateVersion(): Result<UpdateVersion> =
        runCatching { versionDataSource.getUpdateVersion() }.map { response ->
            requireNotNull(response.data).toUpdateVersion()
        }
}
