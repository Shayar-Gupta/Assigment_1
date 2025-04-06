package com.example.assigment1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.assigment1.ui.theme.Assigment1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Assigment1Theme {
                App()
            }
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    val context = LocalContext.current

    NavHost(navController = navController, startDestination = "onboarding") {
        composable("onboarding") {
            FirstScreen(
                navController = navController,
                context = context
            )
        }

        composable(
            route = "resultScreen"
        ) { SecondScreen() }
    }

}
