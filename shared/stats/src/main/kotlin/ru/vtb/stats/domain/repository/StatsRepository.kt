package ru.vtb.stats.domain.repository

import ru.vtb.stats.domain.entity.Token
import ru.vtb.stats.domain.entity.UserStatsSummary

interface StatsRepository {

    suspend fun getStats(token: Token): UserStatsSummary

    suspend fun setStats(stats: UserStatsSummary, token: Token)
}