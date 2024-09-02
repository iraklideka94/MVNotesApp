package com.example.mvnotesapp.domain.model

data class UserModel(
    var userId: String? = null,
    var logIn: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val about: String? = null
)
