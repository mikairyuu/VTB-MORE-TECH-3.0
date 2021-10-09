package ru.vtb.moretech.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(): ViewModel(){

    var name =  MutableStateFlow("")
    val email =  MutableStateFlow("")
    val password =  MutableStateFlow("")

}