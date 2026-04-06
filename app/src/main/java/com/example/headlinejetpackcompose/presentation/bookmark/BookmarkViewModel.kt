package com.example.headlinejetpackcompose.presentation.bookmark

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.headlinejetpackcompose.domain.useCases.dataClasses.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
): ViewModel() {

    var state by mutableStateOf(BookmarkState(emptyList()))
        private set


    init{
        getArticles()
    }

    private fun getArticles(){
        newsUseCases.getArticles().onEach {
            state = state.copy(articles = it)
    }.launchIn(viewModelScope)


}
}
