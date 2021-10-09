package ru.vtb.message.domain.repository

import ru.vtb.message.domain.entity.Header
import ru.vtb.message.domain.entity.Message

interface DayRepository {

    suspend fun getMessages(dayId: Int): List<Message>

    suspend fun getHeader(day: Int, header: Int): Header
}