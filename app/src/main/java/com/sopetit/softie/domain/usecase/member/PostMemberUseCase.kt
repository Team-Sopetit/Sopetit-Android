package com.sopetit.softie.domain.usecase.member

import com.sopetit.softie.data.entity.request.PostMemberRequest
import com.sopetit.softie.domain.repository.MemberRepository
import javax.inject.Inject

class PostMemberUseCase @Inject constructor(
    private val memberRepository: MemberRepository
) {

    suspend operator fun invoke(request: PostMemberRequest) = memberRepository.postMember(request)
}
