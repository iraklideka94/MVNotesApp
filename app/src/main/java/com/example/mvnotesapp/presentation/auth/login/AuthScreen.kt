package com.example.mvnotesapp.presentation.auth.login

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
import com.example.mvnotesapp.presentation.items.ErrorItem
import com.example.mvnotesapp.presentation.items.LoadingItem
import com.example.mvnotesapp.presentation.navigation.Screens

@Composable
fun AuthScreen(
    navController: NavController
) {

    var login by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }
    val viewModel = hiltViewModel<AuthScreenViewModel>()
    val state by viewModel.state.collectAsState()

    when {
        state.isLoading -> {
            LoadingItem()
        }

        state.isSuccess -> {
            viewModel.sendEvent(AuthScreenEvent.Default)
            navController.navigate(Screens.MainScreenType.rout)
        }

        state.error != null -> {
            ErrorItem(errorMessage = (state.error)) {
                viewModel.sendEvent(AuthScreenEvent.Default)
            }
        }

        else -> {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Authorization", modifier = Modifier.padding(vertical = 8.dp))

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

                Button(
                    onClick = {
                        viewModel.sendEvent(
                            AuthScreenEvent.AuthorizationEvent(
                                login = login.lowercase().trim(),
                                password = password.lowercase().trim()
                            )
                        )
                    },
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    Text(text = "Log In")
                }

                Text(
                    text = "Registration",
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .clickable {
                            navController.navigate(Screens.RegistrationScreenType.rout)
                        }
                )
            }
        }
    }

}