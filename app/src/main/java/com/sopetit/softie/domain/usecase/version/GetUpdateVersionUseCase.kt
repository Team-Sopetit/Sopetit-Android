package com.sopetit.softie.domain.usecase.version

import com.sopetit.softie.domain.repository.VersionRepository
import javax.inject.Inject

class GetUpdateVersionUseCase @Inject constructor(
    private val versionRepository: VersionRepository
) {
    suspend operator fun invoke() =
        versionRepository.getUpdateVersion()
}
