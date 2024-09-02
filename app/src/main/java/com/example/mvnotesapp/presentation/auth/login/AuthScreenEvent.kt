package com.example.mvnotesapp.presentation.auth.login

import com.example.mvnotesapp.presentation.screen.base.UiEvent
import javax.annotation.concurrent.Immutable

@Immutable
sealed class AuthScreenEvent: UiEvent {

    object Default : AuthScreenEvent()

    object LoadingData : AuthScreenEvent()

    data class AuthorizationEvent(val login: String, val password: String) : AuthScreenEvent()

    data class ShowError(val errorMessage: String?) : AuthScreenEvent()
}