package com.example.headlinejetpackcompose.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.headlinejetpackcompose.domain.model.Article

class NewsPaginSource(
    private val newsAoi : NewsApi,
    private val source : String
): PagingSource<Int, Article>() {

    //paging Source defines hoew paginated data is loaded from the network

    private var totalNewsCount = 0;
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1)
                ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {

        val page = params.key?: 1

        val newsResponse = newsAoi.getNews(
            source = source,
            page = page
        )

        totalNewsCount += newsResponse.articles.size

        val articles = newsResponse.articles
            .filter { isValidArticle(it) }
            .distinctBy { it.title }
            .distinctBy { it.urlToImage }

        return try{ LoadResult.Page(
            data = articles,
            nextKey = if (totalNewsCount == newsResponse.totalResults) null else page?.plus(1),
            prevKey = null
        )}catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(
                throwable = e
            )
        }
    }



    private fun isValidArticle(article: Article): Boolean {

        val title = article.title?.trim()
        val description = article.description?.trim()

        // Title must exist
        if (title.isNullOrEmpty()) return false

        // Remove time-only / junk titles
        val timeOnlyRegex = Regex(
            """(?i)^(updated)?\s*\d+\s*(min|mins|minute|minutes|hour|hours|hr|hrs|ago)$|^\d{1,2}:\d{2}\s*(am|pm)?$|^live$|^-$"""
        )
        if (timeOnlyRegex.matches(title)) return false

        // Remove very short titles
        if (title.length < 10) return false

        // Description must exist
        if (description.isNullOrEmpty() || description.length < 20) return false

        return true
    }

}