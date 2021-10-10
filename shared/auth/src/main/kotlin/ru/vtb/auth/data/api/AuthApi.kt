package ru.vtb.auth.data.api

import retrofit2.http.Body
import retrofit2.http.POST
import ru.vtb.auth.domain.entity.Token
import ru.vtb.auth.domain.entity.User
import ru.vtb.auth.domain.entity.UserSummary

interface AuthApi {

    @POST("Account/create")
    suspend fun register(@Body user: User): Token

    @POST("Account/login")
    suspend fun login(@Body user: UserSummary): Token

    @POST("Account/get")
    suspend fun getUser(@Body token: Token): User
}