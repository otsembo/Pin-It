package com.otsembo.pinit.authentication.presentation.pages.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class OnBoardingVM : ViewModel() {

    private val _navigationTrigger = MutableSharedFlow<Int>()
    val navigationTrigger: SharedFlow<Int> = _navigationTrigger

    fun navigate(actionId: Int) {
        viewModelScope.launch {
            _navigationTrigger.emit(actionId)
        }
    }
}
