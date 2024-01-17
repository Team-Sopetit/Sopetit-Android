package com.sopetit.softie.data.service

import com.sopetit.softie.data.entity.BaseResponse
import com.sopetit.softie.data.entity.response.AchieveDailyResponse
import com.sopetit.softie.data.entity.response.AddDailyRoutineResponse
import com.sopetit.softie.data.entity.response.DailyCardResponse
import com.sopetit.softie.data.entity.response.DailyRoutineListResponse
import com.sopetit.softie.data.entity.response.DailyRoutineResponse
import com.sopetit.softie.data.entity.response.ThemeListResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface DailyRoutineService {
    @GET("api/v1/routines/daily/themes")
    suspend fun getTheme(): BaseResponse<ThemeListResponse>

    @GET("api/v1/routines/daily")
    suspend fun getRoutineList(
        @Query("themes") themes: List<Int>
    ): BaseResponse<DailyRoutineListResponse>

    @GET("api/v1/routines/daily/member")
    suspend fun getDailyRoutine(): BaseResponse<DailyRoutineResponse>

    @PATCH("api/v1/routines/daily/member/routine/{routineId}")
    suspend fun patchAchieveDaily(@Path("routineId") routineId: Int): BaseResponse<AchieveDailyResponse>

    @DELETE("api/v1/routines/daily/member/routine/{routineId}")
    suspend fun deleteDailyRoutine(@Query("routineId") routineIdList: List<Int>): BaseResponse<Unit>

    @POST("api/v1/routines/daily/member")
    suspend fun postAddDailyRoutine(@Body routineId: Int): BaseResponse<AddDailyRoutineResponse>

    @GET("api/v1/routines/daily/theme/{themeId}")
    suspend fun getDailyCardList(@Path("themeId") themeId: Int): BaseResponse<DailyCardResponse>
}
