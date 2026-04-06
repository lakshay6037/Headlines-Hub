package com.example.headlinejetpackcompose.presentation.details

import com.example.headlinejetpackcompose.domain.model.Article

sealed class DetailsEvent {

    data class UpsertDeleteArticle(val article : Article) : DetailsEvent()

    object RemoveSideEffect : DetailsEvent()


}


