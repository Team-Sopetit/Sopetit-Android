package com.sopetit.softie.data.repositoryImpl

import com.sopetit.softie.data.source.AuthDataSource
import com.sopetit.softie.data.source.LocalDataSource
import com.sopetit.softie.domain.entity.Token
import com.sopetit.softie.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
    private val localTokenDataSource: LocalDataSource
) : AuthRepository {
    override suspend fun postLogin(socialPlatform: String): Result<Token> =
        kotlin.runCatching { authDataSource.postLogin(socialPlatform) }.map { response ->
            requireNotNull(response.data).toToken()
        }

    override fun initToken(accessToken: String, refreshToken: String) {
        localTokenDataSource.accessToken = accessToken
        localTokenDataSource.refreshToken = refreshToken
    }
}
