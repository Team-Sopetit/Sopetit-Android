package com.sopetit.softie.data.source

import com.sopetit.softie.data.entity.BaseResponse
import com.sopetit.softie.data.entity.response.HappyChipResponse
import com.sopetit.softie.data.service.HappinessRoutineService
import javax.inject.Inject

class HappinessRoutineDataSource @Inject constructor(
    private val happinessRoutineService: HappinessRoutineService
) {
    suspend fun getHappyChip(): BaseResponse<HappyChipResponse> =
        happinessRoutineService.getHappyChip()
}
