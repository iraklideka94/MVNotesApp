package com.example.mvnotesapp.domain.repository

import com.example.mvnotesapp.domain.model.NetworkResult
import com.example.mvnotesapp.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun firebaseSignUp(user: UserModel): Flow<NetworkResult<Boolean>>

    suspend fun firebaseLogIn(email: String, password: String): Flow<NetworkResult<Boolean>>

}