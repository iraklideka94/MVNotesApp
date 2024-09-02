package com.example.mvnotesapp.domain.usecase

import com.example.mvnotesapp.domain.model.UserModel
import com.example.mvnotesapp.domain.repository.AuthRepository
import javax.inject.Inject

class AuthUseCase @Inject constructor(
    private val repository: AuthRepository
) {

    suspend fun registerNewUser(userModel: UserModel) =
        repository.firebaseSignUp(user = userModel)

    suspend fun loginUser(email: String, password: String) =
        repository.firebaseLogIn(email, password)

}