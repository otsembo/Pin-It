package com.otsembo.pinit.authentication.presentation.pages.register

import androidx.lifecycle.ViewModel
import com.otsembo.pinit.authentication.common.AppResource
import com.otsembo.pinit.authentication.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RegisterVM constructor(private val authRepository: AuthRepository) : ViewModel() {

    private val _registrationProcess: MutableStateFlow<AppResource<*>> = MutableStateFlow(AppResource.Idle())
    val registrationProcess: StateFlow<AppResource<*>>
        get() = _registrationProcess

    suspend fun createAccount(accountCreate: AccountCreate) {
        _registrationProcess.emit(AppResource.Loading<String>())
        val result = authRepository.createAccount(accountCreate.email, accountCreate.password, accountCreate.username)
        _registrationProcess.emit(if (result) AppResource.Success(result) else AppResource.Error(message = "Failed to create account", data = !result))
    }

    companion object {
        private const val TAG = "REGISTER-VM"
    }
}

// model to be used with create account
data class AccountCreate(
    var username: String = emptyString,
    var email: String = emptyString,
    var password: String = emptyString
) {
    companion object {
        const val emptyString = ""
    }
}
