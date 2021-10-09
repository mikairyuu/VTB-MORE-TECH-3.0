package ru.vtb.auth.domain.usecase

import ru.vtb.auth.domain.entity.Token
import ru.vtb.auth.domain.entity.User
import ru.vtb.auth.domain.repository.AuthRepository
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(private val repository: AuthRepository) {

    suspend operator fun invoke(user: User): Token = repository.register(user)
}