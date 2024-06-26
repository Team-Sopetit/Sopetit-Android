package com.sopetit.softie.domain.usecase.auth

import com.sopetit.softie.domain.repository.AuthRepository
import javax.inject.Inject

class DeleteAuthUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke() = authRepository.deleteAuth()
}
