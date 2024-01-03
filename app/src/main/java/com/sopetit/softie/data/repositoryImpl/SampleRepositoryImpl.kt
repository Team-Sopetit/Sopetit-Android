package com.sopetit.softie.data.repositoryImpl

import com.sopetit.softie.data.source.SampleDataSource
import com.sopetit.softie.domain.entity.Sample
import com.sopetit.softie.domain.repository.SampleRepository
import javax.inject.Inject

class SampleRepositoryImpl
    @Inject
    constructor(
        private val sampleDataSource: SampleDataSource,
    ) : SampleRepository {
        override suspend fun postLogin(name: String): Result<Sample> {
            TODO("Not yet implemented")
        }
    }
