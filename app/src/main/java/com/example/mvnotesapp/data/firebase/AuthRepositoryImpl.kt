package com.example.mvnotesapp.data.firebase

import android.util.Log
import com.example.mvnotesapp.domain.model.NetworkResult
import com.example.mvnotesapp.domain.model.UserModel
import com.example.mvnotesapp.domain.repository.AuthRepository
import com.example.mvnotesapp.utils.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFireStore: FirebaseFirestore
) : AuthRepository {

    val TAG = "AuthRepositoryImpl "

    override suspend fun firebaseSignUp(user: UserModel): Flow<NetworkResult<Boolean>> {
        return flow {
            var isSuccess = false

            emit(NetworkResult.Loading())

            try {

                firebaseAuth.createUserWithEmailAndPassword(user.logIn, user.password)
                    .addOnCompleteListener { task ->
                        isSuccess = if (task.isSuccessful) {
                            Log.d(TAG, "firebaseSignUp: isSuccessful ")
                            val firebaseUser = firebaseAuth.currentUser
                            if (firebaseUser != null) {
                                user.userId = firebaseUser.uid
                                firebaseFireStore.collection(Constants.USERS)
                                    .document(firebaseUser.uid)
                                    .set(user)
                            }
                            firebaseUser != null
                        } else {
                            Log.d(TAG, "firebaseSignUp: Failure", task.exception)
                            false
                        }
                    }.await()

                if (isSuccess) {
                    emit(NetworkResult.Success(true))
                } else {
                    emit(NetworkResult.Error(message = "oh,something went wrong!"))
                }

            } catch (e: Exception) {
                emit(
                    NetworkResult.Error(
                        message = e.localizedMessage ?: "oh,something went wrong!"
                    )
                )
            }
        }
    }

    override suspend fun firebaseLogIn(
        email: String,
        password: String
    ): Flow<NetworkResult<Boolean>> {

        return flow {
            var isSuccess = false

            emit(NetworkResult.Loading())

            try {

                firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d(TAG, "firebaseSignIn: isSuccessful ")
                        } else {
                            Log.d(TAG, "firebaseSignIn: Failure", task.exception)
                            false
                        }
                    }.await()


                if (isSuccess) {
                    emit(NetworkResult.Success(true))
                } else {
                    emit(NetworkResult.Error(message = "oh,something went wrong!"))
                }

            } catch (e: Exception) {
                emit(
                    NetworkResult.Error(
                        message = e.localizedMessage ?: "oh,something went wrong!"
                    )
                )

            }
        }

    }
}
