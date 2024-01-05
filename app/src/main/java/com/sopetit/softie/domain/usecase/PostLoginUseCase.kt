package com.sopetit.softie.domain.usecase

import com.sopetit.softie.domain.repository.SampleRepository
import javax.inject.Inject

class PostLoginUseCase @Inject constructor(
    private val sampleRepository: SampleRepository
) {
    suspend operator fun invoke(name: String) = sampleRepository.postLogin(name)
}
