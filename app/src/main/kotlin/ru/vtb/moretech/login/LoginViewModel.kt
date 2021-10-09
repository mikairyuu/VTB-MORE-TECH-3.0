package ru.vtb.moretech.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.vtb.auth.domain.entity.User
import ru.vtb.auth.domain.entity.UserSummary
import ru.vtb.auth.domain.usecase.LoginUSerUseCase
import ru.vtb.auth.domain.usecase.RegisterUserUseCase
import ru.vtb.storage.PreferencesProvider
import javax.inject.Inject

inline fun <T> all(vararg values: T, condition: (T) -> Boolean): Boolean =
    values.all { condition(it) }

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase,
    private val loginUSerUseCase: LoginUSerUseCase,
    private val preferencesProvider: PreferencesProvider
): ViewModel(){

    var name =  MutableStateFlow("")
    val email =  MutableStateFlow("")
    val password =  MutableStateFlow("")

    val isNeededToNavigated = MutableStateFlow(false)

    fun onClickRegister() {

        if (all(name.value, email.value, password.value) { it.isNotBlank() }) {
            viewModelScope.launch {
                try {
                    registerUserUseCase(User(name.value, email.value, password.value)).also {
                        preferencesProvider.saveUserToken(it.token)
                        isNeededToNavigated.value = true
                    }
                } catch (e: Exception) {

                }

            }
        }
    }

    fun onClickLogin() {

        if (all(email.value, password.value) { it.isNotBlank() }) {
            viewModelScope.launch {
                try {
                    loginUSerUseCase(UserSummary(email.value, password.value)).also {
                        preferencesProvider.saveUserToken(it.token)
                        isNeededToNavigated.value = true
                    }
                } catch (e: Exception) {

                }
            }
        }
    }
}