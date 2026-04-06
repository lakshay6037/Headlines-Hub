package com.example.headlinejetpackcompose.domain.useCases

import com.example.headlinejetpackcompose.data.local.room_db.NewsDao
import com.example.headlinejetpackcompose.domain.model.Article
import com.example.headlinejetpackcompose.domain.repository.NewsRepository
import retrofit2.http.Url

class DeleteArticle(
    private val newsRepository: NewsRepository

) {
        suspend operator fun invoke(url: String) {
            newsRepository.deleteArticleByUrl(url)
        }


    }
