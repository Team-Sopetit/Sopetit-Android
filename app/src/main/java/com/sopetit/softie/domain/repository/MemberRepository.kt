package com.sopetit.softie.domain.repository

import com.sopetit.softie.domain.entity.CottonCount
import com.sopetit.softie.domain.entity.Home

interface MemberRepository {
    suspend fun getHome(): Result<Home>
    suspend fun patchCotton(cottonType: String): Result<CottonCount>
}
