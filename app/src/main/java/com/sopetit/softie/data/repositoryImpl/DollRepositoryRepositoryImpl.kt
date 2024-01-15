package com.sopetit.softie.data.repositoryImpl

import com.sopetit.softie.data.source.DollDataSource
import com.sopetit.softie.domain.repository.DollRepository
import javax.inject.Inject

class DollRepositoryRepositoryImpl @Inject constructor(
    private val dollDataSource: DollDataSource
) : DollRepository {

    override suspend fun getDollType(type: String): Result<String> = runCatching {
        dollDataSource.getDoll(type)
    }.mapCatching {
        requireNotNull(it.data?.faceImageUrl)
    }
}
