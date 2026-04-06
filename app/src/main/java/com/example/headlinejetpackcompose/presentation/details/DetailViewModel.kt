package com.example.headlinejetpackcompose.presentation.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.headlinejetpackcompose.domain.model.Article
import com.example.headlinejetpackcompose.domain.useCases.dataClasses.NewsUseCases
import com.example.headlinejetpackcompose.util.UIComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.http.Url
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
) : ViewModel() {

    var sideEffect  by mutableStateOf<String?>(null)
    private set


    fun onEvent(event: DetailsEvent) {
        when(event) {
            is DetailsEvent.UpsertDeleteArticle -> {

                viewModelScope.launch {
                    val article = newsUseCases.selectArticle(event.article.url)

                    if (article == null) {
                        upsertArticle(event.article)
                    } else {
                        deleteArticle(event.article.url)
                    }
                }



            }

            is DetailsEvent.RemoveSideEffect -> {
                sideEffect = null
            }

        }
    }


    private fun upsertArticle(article: Article){
        viewModelScope.launch {
            newsUseCases.insertArticle(article)
            sideEffect = "Article Saved Successfully"

    }
    }


    private fun deleteArticle(url: String) {
        viewModelScope.launch {
            newsUseCases.deleteArticle(url)
            sideEffect = "Article Deleted Successfully"
        }

    }


}