package ru.vtb.stats.data.api

import retrofit2.http.Body
import retrofit2.http.POST
import ru.vtb.stats.domain.entity.Token
import ru.vtb.stats.domain.entity.UserStatsRequest
import ru.vtb.stats.domain.entity.UserStatsSummary

interface StatsApi {

    @POST("Stats/get")
    suspend fun getStats(
        @Body token:Token
    ): UserStatsSummary

    @POST("Stats/set")
    suspend fun setStats(
        @Body stats:UserStatsRequest
    )
}