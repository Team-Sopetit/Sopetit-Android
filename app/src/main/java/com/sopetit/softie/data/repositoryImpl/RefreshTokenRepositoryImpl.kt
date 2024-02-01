package com.sopetit.softie.data.repositoryImpl

import com.sopetit.softie.data.source.LocalDataSource
import com.sopetit.softie.data.source.RefreshTokenDataSource
import com.sopetit.softie.domain.entity.AccessToken
import com.sopetit.softie.domain.repository.RefreshTokenRepository
import javax.inject.Inject

class RefreshTokenRepositoryImpl @Inject constructor(
    private val refreshTokenDataSource: RefreshTokenDataSource,
    private val localDataSource: LocalDataSource
) : RefreshTokenRepository {

    override suspend fun postRefreshToken(): Result<AccessToken> =
        kotlin.runCatching { refreshTokenDataSource.postRefreshToken() }.map { response ->
            requireNotNull(response.data).toAccessToken()
        }

    override fun setAccessToken(accessToken: String) {
        localDataSource.accessToken = accessToken
    }
}
