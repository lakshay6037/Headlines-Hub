package com.example.headlinejetpackcompose.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.headlinejetpackcompose.domain.model.Article
import com.example.headlinejetpackcompose.presentation.Dimens.MediumPadding1
import com.example.headlinejetpackcompose.presentation.common.ArticleList
import com.example.headlinejetpackcompose.presentation.common.SelfMadeSearchBar

@Composable
fun SearchScreen(
    state: SearchState,
    event: (SearchEvent) -> Unit,
    navigateToDetails : (Article) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(
                top = MediumPadding1,
                start = MediumPadding1,
                end = MediumPadding1
            )
            .statusBarsPadding()
    ) {

        SelfMadeSearchBar(
            text = state.searchQuery,
            readOnly = false,
            onValueChange = { query ->
                event(SearchEvent.UpdateSearchQuery(query))
            },
            onSearch = {
                event(SearchEvent.SearchNews)
            }
        )

        Spacer(modifier = Modifier.height(MediumPadding1))

        state.articles?.let {
            val articles = it.collectAsLazyPagingItems()

            ArticleList(
                articles = articles,
                onClick = {
                    navigateToDetails(it)
                }
            )
        }
    }
}
