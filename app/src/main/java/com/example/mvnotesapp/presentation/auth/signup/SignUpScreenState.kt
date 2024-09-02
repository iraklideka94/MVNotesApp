package com.example.mvnotesapp.presentation.auth.signup

import com.example.mvnotesapp.presentation.screen.base.UiState
import javax.annotation.concurrent.Immutable


@Immutable
data class SignUpScreenState(
    val isLoading: Boolean,
    val isSuccess: Boolean,
    val error: String? = null
) : UiState {

    companion object {
        fun initial() = SignUpScreenState(isLoading = false, isSuccess = false, error = null)
    }
}