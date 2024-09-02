package com.example.mvnotesapp.presentation.auth.login

import androidx.lifecycle.viewModelScope
import com.example.mvnotesapp.domain.usecase.AuthUseCase
import com.example.mvnotesapp.presentation.screen.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AuthScreenViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) : BaseViewModel<AuthScreenState, AuthScreenEvent>() {

    private val reducer = AuthScreenReducer(
        initial = AuthScreenState.initial(),
        authUseCase = authUseCase,
        viewModelScope = viewModelScope
    )
    override val state: StateFlow<AuthScreenState>
        get() = reducer.state

    fun sendEvent(event: AuthScreenEvent) {
        reducer.sendEvent(event)
    }
}