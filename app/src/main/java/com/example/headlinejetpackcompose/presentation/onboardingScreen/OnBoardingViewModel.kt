package com.example.headlinejetpackcompose.presentation.onboardingScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.headlinejetpackcompose.domain.manager.LocalUserManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val localUserManager: LocalUserManager
) : ViewModel(){



    fun onEvent( event : OnboardingEvent){
        when(event){
          is OnboardingEvent.SaveAppEntry -> {
              viewModelScope.launch {
                  saveAppEntry()
              }
          }
        }
    }


    private suspend fun saveAppEntry() {
        localUserManager.saveEntry()
    }
}