package ru.vtb.message.domain.usecase

import ru.vtb.message.domain.repository.DayRepository
import javax.inject.Inject

class GetHeaderFromDay @Inject constructor(private val repository: DayRepository) {

    suspend operator fun invoke(day: Int, header: Int) = repository.getHeader(day, header)
}