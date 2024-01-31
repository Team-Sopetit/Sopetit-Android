package com.sopetit.softie.domain.usecase.member

import com.sopetit.softie.domain.repository.MemberRepository
import javax.inject.Inject

class GetHomeUseCase @Inject constructor(
    private val memberRepository: MemberRepository
) {
    suspend operator fun invoke() = memberRepository.getHome()
}
