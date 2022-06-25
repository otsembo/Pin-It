package com.otsembo.pinit.authentication.presentation.pages.login

import androidx.lifecycle.ViewModel
import com.otsembo.pinit.authentication.common.AppResource
import com.otsembo.pinit.authentication.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow

class LoginVM(private val authRepository: AuthRepository) : ViewModel() {

    var loginStatus: MutableStateFlow<AppResource<*>> = MutableStateFlow(AppResource.Idle())
        private set

    var loginMessage: MutableSharedFlow<String> = MutableSharedFlow()
        private set

    suspend fun logIn(email: String, password: String) {
        loginStatus.emit(AppResource.Loading(null))
        val login = authRepository.login(email, password)
        val loginData = if (login.first) AppResource.Success(login.first) else AppResource.Error(message = "Check credentials and try again", data = login.first)
        loginStatus.emit(loginData)
        loginMessage.emit(login.second)
    }
}
