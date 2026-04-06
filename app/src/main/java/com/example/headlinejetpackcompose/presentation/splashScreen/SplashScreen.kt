package com.example.headlinejetpackcompose.presentation.splashScreen

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.headlinejetpackcompose.Navigation.Routes
import com.example.headlinejetpackcompose.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen( navController: NavController) {

    var scale = remember {
        Animatable(0f)
    }

    val viewModel = hiltViewModel<SplashViewModel>()

    LaunchedEffect(key1 = true){

        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 500,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        delay(3000L)

        navController.navigate(viewModel.startDestination){
            popUpTo(Routes.SplashScreen) {
                inclusive = true
            }
        }

    }

    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center){

        Image(painter = painterResource(id = R.drawable.newapplogo) ,
            contentDescription = "Splash Screen" ,
            modifier = Modifier.size(150.dp),
            contentScale = ContentScale.Crop
        )
    }
}