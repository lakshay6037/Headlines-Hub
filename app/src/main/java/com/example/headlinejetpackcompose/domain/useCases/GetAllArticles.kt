package com.example.headlinejetpackcompose.domain.useCases

import com.example.headlinejetpackcompose.data.local.room_db.NewsDao
import com.example.headlinejetpackcompose.domain.model.Article
import com.example.headlinejetpackcompose.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetAllArticles(
private val newsRepository: NewsRepository

) {

    operator fun invoke() : Flow<List<Article>> {
        return newsRepository.getArticles()
    }

}