package com.example.mvnotesapp.presentation.auth.signup

import com.example.mvnotesapp.domain.usecase.AuthUseCase
import com.example.mvnotesapp.presentation.screen.base.Reducer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class SignUpScreenReducer(
    initial: SignUpScreenState,
    val authUseCase: AuthUseCase,
    val viewModelScope: CoroutineScope
) : Reducer<SignUpScreenState, SignUpScreenEvent>(initialVal = initial) {

    override fun reduce(oldState: SignUpScreenState, event: SignUpScreenEvent) {
        when (event) {
            is SignUpScreenEvent.Default -> setState(
                oldState.copy(
                    isLoading = false,
                    isSuccess = false,
                    error = null
                )
            )

            is SignUpScreenEvent.RegistrationEvent -> {
                sendEvent(SignUpScreenEvent.LoadingData)
                viewModelScope.launch {
                    try {
                        authUseCase.registerNewUser(event.user)
                            .collect {
                                if (it.data != null) {
                                    setState(oldState.copy(isLoading = false, isSuccess = true))
                                } else if (it.message != null) {
                                    sendEvent(SignUpScreenEvent.ShowError(it.message))
                                } else {
                                    sendEvent(SignUpScreenEvent.LoadingData)
                                }
                            }

                    } catch (e: Exception) {
                        sendEvent(
                            SignUpScreenEvent.ShowError(
                                e.message ?: "oh,something went wrong!"
                            )
                        )
                    }

                }
            }

            is SignUpScreenEvent.LoadingData -> {
                setState(oldState.copy(isLoading = true, isSuccess = false))

            }

            is SignUpScreenEvent.ShowError -> {
                setState(
                    oldState.copy(
                        isLoading = false,
                        isSuccess = false,
                        error = event.errorMessage
                    )
                )
            }
        }
    }
}