package com.example.headlinejetpackcompose.domain.useCases

import com.example.headlinejetpackcompose.data.local.room_db.NewsDao
import com.example.headlinejetpackcompose.domain.model.Article
import com.example.headlinejetpackcompose.domain.repository.NewsRepository

class InsertArticle(
    private val newsRepository: NewsRepository

) {

    suspend operator fun invoke(article: Article) {
        newsRepository.insertArticle(article)
    }
}

