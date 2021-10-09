package ru.vtb.auth.domain.usecase

import ru.vtb.auth.domain.entity.User
import ru.vtb.auth.domain.entity.UserSummary
import ru.vtb.auth.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUSerUseCase @Inject constructor(private val repository: AuthRepository) {

    suspend operator fun invoke(user: UserSummary) = repository.login(user)
}