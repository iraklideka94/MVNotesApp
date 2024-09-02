package com.example.mvnotesapp.presentation.auth.login

import com.example.mvnotesapp.presentation.screen.base.UiState
import javax.annotation.concurrent.Immutable


@Immutable
data class AuthScreenState(
    val isLoading: Boolean,
    val isSuccess: Boolean,
    val error: String? = null
) : UiState {

    companion object {
        fun initial() = AuthScreenState(isLoading = false, isSuccess = false, error = null)
    }
}