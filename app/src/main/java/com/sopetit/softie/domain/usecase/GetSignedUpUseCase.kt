package com.sopetit.softie.domain.usecase

import com.sopetit.softie.domain.repository.AuthRepository
import javax.inject.Inject

class GetSignedUpUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke() = authRepository.getSignedUp()
}
