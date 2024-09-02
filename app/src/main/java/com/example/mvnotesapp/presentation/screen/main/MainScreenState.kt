package com.example.mvnotesapp.presentation.screen.main

import androidx.compose.runtime.Immutable
import com.example.mvnotesapp.domain.model.NoteModel
import com.example.mvnotesapp.presentation.screen.base.UiState

@Immutable
data class MainScreenState(
    val isLoading: Boolean,
    val data: List<NoteModel>,
    val error: String? = null
) : UiState {
    companion object {
        fun initial() = MainScreenState(isLoading = true, data = emptyList(), error = null)
    }
}