package com.example.headlinejetpackcompose.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.headlinejetpackcompose.domain.model.Article
import com.example.headlinejetpackcompose.presentation.Dimens.ExtraSmallPadding2
import com.example.headlinejetpackcompose.presentation.Dimens.MediumPadding1
import java.time.Instant
import java.time.Duration

@Composable
fun ArticleList(
    modifier: Modifier = Modifier,
    articles :LazyPagingItems<Article>,
    onClick: (Article) -> Unit
) {

    val handlePagingResult: Boolean = handlePagingResult(articles = articles)



    if (handlePagingResult) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(MediumPadding1),
            contentPadding = PaddingValues(ExtraSmallPadding2)
        ) {
            items(articles.itemCount) {
                articles[it]?.let {
                    ArticleCard(article = it, onClick = { onClick(it) })
                }

            }

        }


    }





}








@Composable
fun handlePagingResult(articles: LazyPagingItems<Article>): Boolean {


    val loadState = articles.loadState


    val error = when {

        loadState.refresh is LoadState.Error ->
            loadState.refresh as LoadState.Error


        loadState.prepend is LoadState.Error ->
            loadState.prepend as LoadState.Error


        loadState.append is LoadState.Error ->
            loadState.append as LoadState.Error


        else -> null
    }

    // Decide what UI to show based on load state
    return when {

        // Initial data is still loading
        loadState.refresh is LoadState.Loading -> {
            // Show shimmer loading UI
            ShimmerEffect()

            // Prevent LazyColumn from rendering
            false
        }

        // Any paging error occurred
        error != null -> {
            // Show empty / error screen
            EmptyScreen()

            // Prevent LazyColumn from rendering
            false
        }

        // Data loaded successfully with no errors
        else -> {
            // Allow LazyColumn to render
            true
        }
    }
}



@Composable
private fun ShimmerEffect() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        repeat(10) {
            ArticleCardShimmer(
                modifier = Modifier.padding(MediumPadding1)
            )
        }
    }
}



