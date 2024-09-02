package com.example.mvnotesapp.presentation.auth.signup

import com.example.mvnotesapp.domain.model.UserModel
import com.example.mvnotesapp.presentation.screen.base.UiEvent
import javax.annotation.concurrent.Immutable

@Immutable
sealed class SignUpScreenEvent: UiEvent {

    object Default : SignUpScreenEvent()

    object LoadingData : SignUpScreenEvent()

    data class RegistrationEvent(val user: UserModel) : SignUpScreenEvent()

    data class ShowError(val errorMessage: String?) : SignUpScreenEvent()
}