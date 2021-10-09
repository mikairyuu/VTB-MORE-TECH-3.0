package ru.vtb.message.data.api

import retrofit2.http.GET
import retrofit2.http.Query
import ru.vtb.message.domain.entity.Header
import ru.vtb.message.domain.entity.Message

interface DayApi {

    @GET("messages")
    suspend fun getMessages(
        @Query("dayId") dayId: Int
    ): List<Message>

    @GET("header")
    suspend fun getHeader(
        @Query("day") day: Int,
        @Query("header") header: Int
    ): Header
}