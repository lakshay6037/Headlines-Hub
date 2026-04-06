package com.example.headlinejetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import com.example.headlinejetpackcompose.Navigation.AppNavHost
import com.example.headlinejetpackcompose.data.local.room_db.NewsDao
import com.example.headlinejetpackcompose.domain.model.Article
import com.example.headlinejetpackcompose.domain.model.Source
import com.example.headlinejetpackcompose.theme.HeadlineJetpackComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var dao : NewsDao



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {





            HeadlineJetpackComposeTheme {

                AppNavHost()

            }
        }
    }
}



