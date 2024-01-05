package com.sopetit.softie.domain.repository

import com.sopetit.softie.domain.entity.Sample

interface SampleRepository {
    suspend fun postLogin(name: String): Result<Sample>
}
