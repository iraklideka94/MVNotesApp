package com.example.mvnotesapp.presentation.screen.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow

abstract class BaseViewModel<S: UiState, in E: UiEvent>: ViewModel() {

    abstract val state: Flow<S>

}