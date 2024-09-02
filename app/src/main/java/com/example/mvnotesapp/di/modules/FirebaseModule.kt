package com.example.mvnotesapp.di.modules

import com.example.mvnotesapp.data.firebase.AuthRepositoryImpl
import com.example.mvnotesapp.domain.repository.AuthRepository
import com.example.mvnotesapp.domain.usecase.AuthUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Singleton
    @Provides
    fun providesFirebaseAuth() = FirebaseAuth.getInstance()

    @Singleton
    @Provides
    fun providesFirebaseFireStore() = FirebaseFirestore.getInstance()

    @Singleton
    @Provides
    fun providesAuthRepository(
        firebaseAuth: FirebaseAuth,
        firebaseFireStore: FirebaseFirestore
    ): AuthRepository = AuthRepositoryImpl(firebaseAuth = firebaseAuth, firebaseFireStore = firebaseFireStore)

    @Singleton
    @Provides
    fun providesAuthUseCase(authRepository: AuthRepository): AuthUseCase =
        AuthUseCase(authRepository)
}