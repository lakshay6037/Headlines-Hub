package com.example.headlinejetpackcompose.domain.useCases.dataClasses

import com.example.headlinejetpackcompose.domain.useCases.DeleteArticle
import com.example.headlinejetpackcompose.domain.useCases.GetAllArticles
import com.example.headlinejetpackcompose.domain.useCases.GetNews
import com.example.headlinejetpackcompose.domain.useCases.InsertArticle
import com.example.headlinejetpackcompose.domain.useCases.SearchNews
import com.example.headlinejetpackcompose.domain.useCases.SelectArticle

data class NewsUseCases(
    val getNews: GetNews,

    val searchNews: SearchNews,

    val getArticles: GetAllArticles,

    val deleteArticle: DeleteArticle,

    val insertArticle: InsertArticle,

    val selectArticle: SelectArticle
)
