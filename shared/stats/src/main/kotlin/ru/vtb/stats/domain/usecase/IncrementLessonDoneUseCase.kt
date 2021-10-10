package ru.vtb.stats.domain.usecase

import ru.vtb.stats.domain.entity.Token
import ru.vtb.stats.domain.entity.UserStatsSummary
import ru.vtb.stats.domain.repository.StatsRepository
import javax.inject.Inject

class IncrementLessonDoneUseCase @Inject constructor(private val repository: StatsRepository) {
        suspend operator fun invoke(token: Token){
            val stats = repository.getStats(token)
            stats.lessonsDone++
            repository.setStats(stats, token)
    }
}