package com.example.mvnotesapp.presentation.auth.signup

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mvnotesapp.domain.model.UserModel
import com.example.mvnotesapp.presentation.items.ErrorItem
import com.example.mvnotesapp.presentation.items.LoadingItem
import com.example.mvnotesapp.presentation.navigation.Screens

@Composable
fun SignUpScreen(
    navController: NavController
) {

    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var about by remember { mutableStateOf("") }

    val viewModel = hiltViewModel<SignUpScreenViewModel>()
    val state by viewModel.state.collectAsState()

    when {
        state.isLoading -> {
            LoadingItem()
        }

        state.isSuccess -> {
            viewModel.sendEvent(SignUpScreenEvent.Default)
            navController.navigate(Screens.AuthScreenType.rout)
        }

        state.error != null -> {
            ErrorItem(errorMessage = (state.error)) {
                viewModel.sendEvent(SignUpScreenEvent.Default)
            }
        }

        else -> {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Registration", modifier = Modifier.padding(vertical = 8.dp))

                TextField(
                    value = login,
                    onValueChange = { login = it },
                    label = { Text("Login") },
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                TextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                TextField(
                    value = firstName,
                    onValueChange = { firstName = it },
                    label = { Text("First Name ") },
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                TextField(
                    value = lastName,
                    onValueChange = { lastName = it },
                    label = { Text("Last Name") },
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                TextField(
                    value = about,
                    onValueChange = { about = it },
                    label = { Text("About Me") },
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                Button(
                    onClick = {
                        val userLogin = login.lowercase().trim()
                        val userPassword = password.lowercase().trim()
                        val userFirstName = firstName.lowercase().trim()
                        val userLastName = lastName.lowercase().trim()
                        val userAbout = about.lowercase().trim()

                        if (userLogin.isNotEmpty() && userPassword.isNotEmpty() &&
                            userFirstName.isNotEmpty() && userLastName.isNotEmpty()
                        ) {
                            val user = UserModel(
                                userId = null,
                                logIn = userLogin,
                                password = userPassword,
                                firstName = userFirstName,
                                lastName = userLastName,
                                about = userAbout
                            )

                            viewModel.sendEvent(SignUpScreenEvent.RegistrationEvent(user))
                        }

                    },
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    Text(text = "Sign Up")
                }

                Text(
                    text = "Log In",
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .clickable {
                            navController.navigate(Screens.AuthScreenType.rout)
                        }
                )
            }
        }
    }

}