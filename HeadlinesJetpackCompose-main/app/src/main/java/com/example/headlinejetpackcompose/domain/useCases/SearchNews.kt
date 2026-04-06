package com.example.headlinejetpackcompose.domain.useCases

import androidx.paging.PagingData
import com.example.headlinejetpackcompose.domain.model.Article
import com.example.headlinejetpackcompose.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SearchNews(
    private val newsRepository: NewsRepository
) {

    operator fun invoke (searchQuery :String, sources : List<String>) : Flow<PagingData<Article>>{
        return newsRepository.searchNews(searchQuery , sources)
    }
}