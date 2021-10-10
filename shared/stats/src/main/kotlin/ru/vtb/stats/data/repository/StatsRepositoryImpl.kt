package ru.vtb.stats.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.vtb.stats.data.api.StatsApi
import ru.vtb.stats.domain.entity.Token
import ru.vtb.stats.domain.entity.UserStatsRequest
import ru.vtb.stats.domain.entity.UserStatsSummary
import ru.vtb.stats.domain.repository.StatsRepository
import javax.inject.Inject

class StatsRepositoryImpl @Inject constructor(private val api: StatsApi) : StatsRepository {
    private var lastFetchedStat: UserStatsSummary? = null

    override suspend fun getStats(token: Token): UserStatsSummary = withContext(Dispatchers.IO) {
        if (lastFetchedStat == null)
            lastFetchedStat = api.getStats(token)

        return@withContext lastFetchedStat!!
    }

    override suspend fun setStats(stats: UserStatsSummary, token: Token) = withContext(Dispatchers.IO) {
        val requestStats =
            UserStatsRequest(
                token = token.token,
                score = stats.score,
                casesDone = stats.casesDone,
                lessonsDone = stats.lessonsDone,
                isQualified = stats.isQualified,
                money = stats.money
            )
        api.setStats(requestStats)

    }
}