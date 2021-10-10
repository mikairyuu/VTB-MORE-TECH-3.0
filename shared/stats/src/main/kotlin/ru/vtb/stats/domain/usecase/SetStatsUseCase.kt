package ru.vtb.stats.domain.usecase

import ru.vtb.stats.domain.entity.Token
import ru.vtb.stats.domain.entity.UserStatsSummary
import ru.vtb.stats.domain.repository.StatsRepository
import javax.inject.Inject

class SetStatsUseCase @Inject constructor(private val repository: StatsRepository) {
    suspend operator fun invoke(stats: UserStatsSummary, token: Token) =
        repository.setStats(stats, token)
}