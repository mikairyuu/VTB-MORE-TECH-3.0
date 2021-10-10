package ru.vtb.auth.domain.repository

import ru.vtb.auth.domain.entity.Token
import ru.vtb.auth.domain.entity.User
import ru.vtb.auth.domain.entity.UserSummary

interface AuthRepository {

    suspend fun register( user: User): Token

    suspend fun login(user: UserSummary): Token

    suspend fun getUser(token: Token): User
}