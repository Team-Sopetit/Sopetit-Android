package com.sopetit.softie.domain.usecase.local

import com.sopetit.softie.domain.repository.AuthRepository
import javax.inject.Inject

class SetIsTutorialUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(isTutorial: Boolean) =
        authRepository.setTutorial(isTutorial)
}
