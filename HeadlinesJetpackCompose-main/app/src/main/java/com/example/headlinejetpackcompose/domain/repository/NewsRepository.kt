package com.example.headlinejetpackcompose.domain.repository

import androidx.paging.PagingData
import com.example.headlinejetpackcompose.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getNews(sources :List<String>) : Flow<PagingData<Article>>


    fun searchNews(query : String, sources :List<String>) : Flow<PagingData<Article>>


    suspend fun insertArticle(article: Article)

    suspend fun deleteArticleByUrl(url :String)

    fun getArticles() : Flow<List<Article>>


    suspend fun getAnArticle( url :String) : Article?




}

