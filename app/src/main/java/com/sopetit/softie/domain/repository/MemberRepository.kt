package com.sopetit.softie.domain.repository

import com.sopetit.softie.domain.entity.Home

interface MemberRepository {
    suspend fun getHome(): Result<Home>
}
