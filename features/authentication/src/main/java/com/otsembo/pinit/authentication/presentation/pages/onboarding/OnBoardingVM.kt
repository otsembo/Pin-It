package com.otsembo.pinit.authentication.presentation.pages.onboarding

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class OnBoardingVM @Inject constructor() : ViewModel() {

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
