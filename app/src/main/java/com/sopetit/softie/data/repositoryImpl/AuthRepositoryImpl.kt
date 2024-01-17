package com.sopetit.softie.data.repositoryImpl

import com.sopetit.softie.data.source.AuthDataSource
import com.sopetit.softie.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource
) : AuthRepository {

    override suspend fun deleteAuth(): Result<Unit> = runCatching { authDataSource.deleteAuth() }
}
