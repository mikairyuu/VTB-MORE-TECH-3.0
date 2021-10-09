package ru.vtb.message.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.vtb.message.data.api.DayApi
import ru.vtb.message.domain.entity.Header
import ru.vtb.message.domain.entity.Message
import ru.vtb.message.domain.repository.DayRepository
import javax.inject.Inject

class DayRepositoryImpl @Inject constructor(private val api: DayApi): DayRepository {

    override suspend fun getMessages(dayId: Int): List<Message> = withContext(Dispatchers.IO) {
        api.getMessages(dayId)
    }

    override suspend fun getHeader(day: Int, header: Int): Header = withContext(Dispatchers.IO) {
        api.getHeader(day, header)
    }
}