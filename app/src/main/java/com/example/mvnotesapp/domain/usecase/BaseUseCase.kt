package com.example.mvnotesapp.domain.usecase

abstract class BaseUseCase<T> {
    abstract suspend fun invoke(): T
}