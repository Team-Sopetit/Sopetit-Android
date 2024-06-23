package com.sopetit.softie.domain.usecase.addroutine

import com.sopetit.softie.domain.entity.RoutineTheme
import com.sopetit.softie.domain.repository.AddRoutineRepository
import javax.inject.Inject

class GetRoutineThemeListUseCase @Inject constructor(
    private val addRoutineRepository: AddRoutineRepository
) {
    suspend operator fun invoke(): Result<RoutineTheme> {
        return try {
            val result = addRoutineRepository.getRoutineTheme()
            result
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
