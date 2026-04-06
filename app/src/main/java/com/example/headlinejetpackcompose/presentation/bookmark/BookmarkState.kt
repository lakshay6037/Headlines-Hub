package com.example.headlinejetpackcompose.presentation.bookmark

import com.example.headlinejetpackcompose.domain.model.Article
import kotlinx.coroutines.flow.Flow

data class BookmarkState (
    val articles : List<Article>
)

