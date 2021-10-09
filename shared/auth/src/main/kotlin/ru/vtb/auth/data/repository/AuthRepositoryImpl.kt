package ru.vtb.auth.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.vtb.auth.data.api.AuthApi
import ru.vtb.auth.domain.entity.Token
import ru.vtb.auth.domain.entity.User
import ru.vtb.auth.domain.entity.UserSummary
import ru.vtb.auth.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val api: AuthApi) : AuthRepository {

    override suspend fun register(user: User): Token = withContext(Dispatchers.IO) {
        api.register(user)
    }

    override suspend fun login(user: UserSummary) = withContext(Dispatchers.IO) {
        api.login(user)
    }

    override suspend fun getStatistic(user: User): User = withContext(Dispatchers.IO) {
        api.getStatistic(user)
    }
}