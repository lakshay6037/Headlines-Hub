package com.example.headlinejetpackcompose.presentation.splashScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.headlinejetpackcompose.Navigation.Routes
import com.example.headlinejetpackcompose.domain.manager.LocalUserManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SplashViewModel @Inject constructor(
    private val LocalUserManager: LocalUserManager
) : ViewModel() {


    var startDestination by mutableStateOf(Routes.SplashScreen)
        private set


    init {
        viewModelScope.launch {
            LocalUserManager.readAppEntry().collect { entry ->
                startDestination = if (entry) {
                    Routes.NewsNavigator
                } else {
                    Routes.OnboardingScreen

                }

            }

        }
    }
}



