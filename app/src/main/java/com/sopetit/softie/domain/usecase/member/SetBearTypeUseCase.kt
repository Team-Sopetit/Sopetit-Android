package com.sopetit.softie.domain.usecase.member

import com.sopetit.softie.domain.repository.AuthRepository
import javax.inject.Inject

class SetBearTypeUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(bearType: String) =
        authRepository.setBearType(bearType)
}
