package com.example.headlinejetpackcompose.Navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.headlinejetpackcompose.Navigation.Routes.NewsNavigator
import com.example.headlinejetpackcompose.presentation.navigation.AppNavigator
import com.example.headlinejetpackcompose.presentation.onboardingScreen.OnboardingScreen
import com.example.headlinejetpackcompose.presentation.splashScreen.SplashScreen
import com.example.headlinejetpackcompose.presentation.splashScreen.SplashViewModel

@Composable
fun AppNavHost() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.SplashScreen
    ) {

        composable(Routes.SplashScreen) {

            SplashScreen(navController)
        }

        composable(Routes.OnboardingScreen) {
            OnboardingScreen(navController)
        }


        composable(Routes.NewsNavigator) {
            AppNavigator()
        }

    }
}


