package ru.vtb.auth.domain.usecase

import ru.vtb.auth.domain.entity.Token
import ru.vtb.auth.domain.entity.User
import ru.vtb.auth.domain.repository.AuthRepository
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(private val repository: AuthRepository) {

    suspend operator fun invoke(token: Token) = repository.getUser(token)
}