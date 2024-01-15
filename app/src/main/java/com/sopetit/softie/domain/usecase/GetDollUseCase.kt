package com.sopetit.softie.domain.usecase

import com.sopetit.softie.domain.repository.DollRepository
import javax.inject.Inject

class GetDollUseCase @Inject constructor(
    private val dollRepository: DollRepository
) {

    suspend operator fun invoke(type: String) = dollRepository.getDollType(type)
}
