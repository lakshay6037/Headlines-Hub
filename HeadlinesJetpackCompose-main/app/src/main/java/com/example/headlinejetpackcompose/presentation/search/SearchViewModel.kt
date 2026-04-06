package com.example.headlinejetpackcompose.presentation.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.headlinejetpackcompose.domain.useCases.dataClasses.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
) : ViewModel() {


    var state by mutableStateOf(SearchState(
        searchQuery = ""
    ))
        private set


    fun onEvent(event: SearchEvent){
        when(event){
            is SearchEvent.UpdateSearchQuery ->{
                state = state.copy(searchQuery = event.searchQuery)

            }
            is SearchEvent.SearchNews ->{
                searchNews()
            }
        }
    }

    private fun searchNews() {
        val articles = newsUseCases.searchNews(
            state.searchQuery,
            listOf("bbc-news", "abc-news", "al-jazeera-english")
        ).cachedIn(viewModelScope)
        state = state.copy(articles = articles)
    }
}