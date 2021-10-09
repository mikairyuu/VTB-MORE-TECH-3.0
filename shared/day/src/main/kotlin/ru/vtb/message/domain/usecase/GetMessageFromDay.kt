package ru.vtb.message.domain.usecase

import ru.vtb.message.domain.repository.DayRepository
import javax.inject.Inject

class GetMessageFromDay @Inject constructor(private val repository: DayRepository) {

    suspend operator fun invoke(dayId: Int) = repository.getMessages(dayId)
}