package ru.vtb.stats.domain.usecase

import ru.vtb.stats.domain.entity.Token
import ru.vtb.stats.domain.repository.StatsRepository
import javax.inject.Inject

class GetStatsUseCase @Inject constructor(private val repository: StatsRepository) {

    suspend operator fun invoke(token: Token) = repository.getStats(token)
}