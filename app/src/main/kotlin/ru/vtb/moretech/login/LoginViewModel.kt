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
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase,
    private val loginUSerUseCase: LoginUSerUseCase
): ViewModel(){

    var name =  MutableStateFlow("")
    val email =  MutableStateFlow("")
    val password =  MutableStateFlow("")

    fun onClickRegister() {

        viewModelScope.launch {
            registerUserUseCase(User(name.value, email.value, password.value)).also {
                Log.d("TESTING_REG", it.token)
            }
        }
    }

    fun onClickLogin() {

        viewModelScope.launch {
            loginUSerUseCase(UserSummary(email.value, password.value)).also {
                Log.d("TESTING_AUTH", it.token)
            }
        }
    }
}