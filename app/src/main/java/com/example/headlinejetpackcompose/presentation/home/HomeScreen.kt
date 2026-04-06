package com.example.headlinejetpackcompose.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.headlinejetpackcompose.Navigation.Routes
import com.example.headlinejetpackcompose.R
import com.example.headlinejetpackcompose.domain.model.Article
import com.example.headlinejetpackcompose.presentation.Dimens.DefaultPadding
import com.example.headlinejetpackcompose.presentation.Dimens.MediumPadding1
import com.example.headlinejetpackcompose.presentation.common.ArticleList
import com.example.headlinejetpackcompose.presentation.common.SelfMadeSearchBar


@Composable
fun HomeScreen(
    articles: LazyPagingItems<Article>,
    navigateToDetails: (Article) -> Unit,
    navigateToSearch: () -> Unit
) {

    // 'titles' will automatically update when paging data changes
    val titles by remember(articles) {
        derivedStateOf {
            if (articles.itemCount > 10) {
                articles.itemSnapshotList.items
                    .take(10)
                    .joinToString(separator = " \uD83D\uDFE5 ") {
                        it.title.orEmpty()
                    }
            } else ""
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(top = MediumPadding1)
    ) {

        Image(
            painter = painterResource(id = R.drawable.newapplogo),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(150.dp)
                .height(30.dp)
                .padding(horizontal = MediumPadding1)
        )

        Spacer(modifier = Modifier.height(MediumPadding1))

        SelfMadeSearchBar(
            modifier = Modifier
                .padding(horizontal = MediumPadding1)
                .fillMaxWidth(),
            text = "",
            readOnly = true,
            onValueChange = {},
            onSearch = {},
            onClick = navigateToSearch
        )

        Spacer(modifier = Modifier.height(MediumPadding1))

        Text(
            text = titles,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = MediumPadding1)
                .basicMarquee(),
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )

        Spacer(modifier = Modifier.height(MediumPadding1))

        ArticleList(
            modifier = Modifier.padding(horizontal = DefaultPadding),
            articles = articles,
            onClick = navigateToDetails
        )
    }
}




//@Preview(showBackground = true)
//@Composable
//fun ArticleCardShimmerPreview(){
//    HeadlineJetpackComposeTheme() {
//        ArticleCardShimmer(Modifier)
//    }
//
//}


//@Preview(showBackground = true)
//@Composable
//fun ArticleCardPreview(){
//    HeadlineJetpackComposeTheme() {
//    }
//
//}
