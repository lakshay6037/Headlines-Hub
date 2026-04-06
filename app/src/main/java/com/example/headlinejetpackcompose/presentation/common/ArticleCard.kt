package com.example.headlinejetpackcompose.presentation.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.headlinejetpackcompose.R
import com.example.headlinejetpackcompose.domain.model.Article
import com.example.headlinejetpackcompose.domain.model.Source
import com.example.headlinejetpackcompose.presentation.Dimens.ArticleCardSize
import com.example.headlinejetpackcompose.presentation.Dimens.ExtraSmallPadding
import com.example.headlinejetpackcompose.presentation.Dimens.ExtraSmallPadding2
import com.example.headlinejetpackcompose.presentation.Dimens.NewsTitlePadding
import com.example.headlinejetpackcompose.presentation.Dimens.SmallIconSize
import com.example.headlinejetpackcompose.theme.HeadlineJetpackComposeTheme
import java.time.Duration
import java.time.Instant

@Composable
fun ArticleCard(
    modifier: Modifier = Modifier,
    article: Article,
    onClick: (() -> Unit)? = null
) {
    val context = LocalContext.current

    Row(
        modifier = modifier
            .clickable { onClick?.invoke() }
            .padding(vertical = ExtraSmallPadding)
    ) {
        AsyncImage(
            modifier = Modifier
                .size(ArticleCardSize)
                .clip(MaterialTheme.shapes.medium),
            model = ImageRequest.Builder(context)
                .data(article.urlToImage)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(horizontal = NewsTitlePadding)
                .height(ArticleCardSize)
        ) {
            Text(
                text = article.title.orEmpty(),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = article.source.name,
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.width(ExtraSmallPadding2))

                Icon(
                    painter = painterResource(id = R.drawable.ic_time),
                    contentDescription = null,
                    modifier = Modifier.size(SmallIconSize),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.width(ExtraSmallPadding))

                Text(
                    text = getHoursAgo(article.publishedAt),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

/* -------------------- PREVIEW -------------------- */

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ArticleCardPreview() {
    HeadlineJetpackComposeTheme(dynamicColor = false) {
        ArticleCard(
            article = Article(
                id = 1,
                author = null,
                content = null,
                description = null,
                publishedAt = "2024-03-10T10:00:00Z",
                source = Source(id = "", name = "BBC"),
                title = "Her train broke down. Her phone died. And then she met her savior.",
                url = "https://bbc.com",
                urlToImage = "https://ichef.bbci.co.uk/live-experience/cps/624/cpsprodpb/11787/production/_124395517_bbcbreakingnewsgraphic.jpg"
            )
        )
    }
}

/* -------------------- TIME HELPER -------------------- */

private fun getHoursAgo(publishedAt: String?): String {
    if (publishedAt.isNullOrBlank()) return ""

    return try {
        val publishedInstant = Instant.parse(publishedAt)
        val nowInstant = Instant.now()

        val duration = Duration.between(publishedInstant, nowInstant)
        val hours = duration.toHours()
        val days = duration.toDays()

        when {
            hours < 1 -> "Just now"
            hours == 1L -> "1 hour ago"
            hours < 24 -> "$hours hours ago"
            days == 1L -> "1 day ago"
            else -> "$days days ago"
        }
    } catch (e: Exception) {
        ""
    }
}
