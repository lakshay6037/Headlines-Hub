package com.example.headlinejetpackcompose.presentation.bookmark

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.headlinejetpackcompose.domain.model.Article
import com.example.headlinejetpackcompose.presentation.Dimens.ExtraSmallPadding2
import com.example.headlinejetpackcompose.presentation.Dimens.MediumPadding1
import com.example.headlinejetpackcompose.presentation.common.ArticleCard

@Composable
fun BookmarkScreen(
    state: BookmarkState,
    navigateToDetails: (Article) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(
                top = MediumPadding1,
                start = MediumPadding1,
                end = MediumPadding1
            )
    ) {

        Text(
            text = "Bookmarks",
            style = MaterialTheme.typography.displayMedium.copy(
                fontWeight = FontWeight.Bold
            ),
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(MediumPadding1))

        if (state.articles.isEmpty()) {
            EmptyBookmarks()
        } else {
            BookmarkArticleList(
                articles = state.articles,
                onClick = navigateToDetails
            )
        }
    }
}

/* -------------------- LIST -------------------- */

@Composable
private fun BookmarkArticleList(
    articles: List<Article>,
    onClick: (Article) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(MediumPadding1),
        contentPadding = PaddingValues(ExtraSmallPadding2)
    ) {
        items(articles.size) { index ->
            ArticleCard(
                article = articles[index],
                onClick = { onClick(articles[index]) }
            )
        }
    }
}

/* -------------------- EMPTY STATE -------------------- */

@Composable
private fun EmptyBookmarks() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = MediumPadding1),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "No bookmarks yet",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
