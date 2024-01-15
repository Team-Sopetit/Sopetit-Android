package com.sopetit.softie.data.repositoryImpl

import com.sopetit.softie.data.source.MemberDataSource
import com.sopetit.softie.domain.entity.Home
import com.sopetit.softie.domain.repository.MemberRepository
import javax.inject.Inject

class MemberRepositoryImpl @Inject constructor(
    private val memberDataSource: MemberDataSource
) : MemberRepository {
    override suspend fun getHome(): Result<Home> =
        runCatching { memberDataSource.getHome() }.map { response ->
            requireNotNull(response.data).toHome()
        }
}
