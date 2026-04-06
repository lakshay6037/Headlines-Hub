package com.example.headlinejetpackcompose.domain.useCases

import com.example.headlinejetpackcompose.domain.model.Article
import com.example.headlinejetpackcompose.domain.repository.NewsRepository

class SelectArticle(
    val newsRepository: NewsRepository
) {

    operator suspend fun invoke(url :String) : Article? {
        return newsRepository.getAnArticle(url)
    }
}


