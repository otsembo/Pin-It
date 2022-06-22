package com.otsembo.pinit.authentication.presentation.pages.onboarding

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class OnBoardingVM : ViewModel() {

    private val _loginNavigation: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loginNavigation: StateFlow<Boolean>
        get() = _loginNavigation

    private val _registerNavigation: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val registerNavigation: StateFlow<Boolean>
        get() = _registerNavigation

    suspend fun navigateToLogin(isNavigating: Boolean) {
        _loginNavigation.emit(isNavigating)
    }

    suspend fun navigateToRegister(isNavigating: Boolean) {
        _registerNavigation.emit(isNavigating)
    }
}
