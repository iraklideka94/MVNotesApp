package com.example.mvnotesapp.presentation

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.navigation.compose.rememberNavController
import com.example.mvnotesapp.presentation.navigation.SetupNavHost
import com.example.mvnotesapp.presentation.ui.theme.MVNotesAppTheme
import com.google.firebase.FirebaseApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        FirebaseApp.initializeApp(applicationContext)

        setContent {
            val navController = rememberNavController()

            MVNotesAppTheme {
                SetupNavHost(navController = navController)
            }
        }
    }
}

