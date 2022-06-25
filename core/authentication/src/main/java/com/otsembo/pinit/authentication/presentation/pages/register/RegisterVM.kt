package com.otsembo.pinit.authentication.presentation.pages.register

import androidx.lifecycle.ViewModel
import com.otsembo.pinit.authentication.common.AppResource
import com.otsembo.pinit.authentication.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow

class RegisterVM constructor(private val authRepository: AuthRepository) : ViewModel() {

    var registrationProcess: MutableStateFlow<AppResource<*>> = MutableStateFlow(AppResource.Idle())
        private set

    suspend fun createAccount(accountCreate: AccountCreate) {
        registrationProcess.emit(AppResource.Loading<String>())
        val result = authRepository.createAccount(accountCreate.email, accountCreate.password, accountCreate.username)
        registrationProcess.emit(if (result.first) AppResource.Success(result.first) else AppResource.Error(message = result.second, data = result.first))
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
