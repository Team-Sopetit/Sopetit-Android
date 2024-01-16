package com.sopetit.softie.domain.usecase

import com.sopetit.softie.domain.repository.MemberRepository
import javax.inject.Inject

class PatchCottonUseCase @Inject constructor(
    private val memberRepository: MemberRepository
) {
    suspend operator fun invoke(cottonType: String) = memberRepository.patchCotton(cottonType)
}
