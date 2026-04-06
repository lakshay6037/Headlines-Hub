package com.example.headlinejetpackcompose.domain.useCases

import androidx.paging.PagingData
import com.example.headlinejetpackcompose.domain.model.Article
import com.example.headlinejetpackcompose.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetNews(
    private val newsRepository: NewsRepository
) {

    operator fun invoke (sources : List<String>) : Flow<PagingData<Article>>{
        return newsRepository.getNews(sources)
    }


}