package com.example.headlinejetpackcompose.presentation.common

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.headlinejetpackcompose.R
import com.example.headlinejetpackcompose.presentation.Dimens.ArticleCardSize
import com.example.headlinejetpackcompose.presentation.Dimens.ExtraSmallPadding
import com.example.headlinejetpackcompose.presentation.Dimens.MediumPadding1


fun Modifier.shimmerEffect() = composed {

    val transition = rememberInfiniteTransition(label = "shimmer")

    val alpha by transition.animateFloat(
        initialValue = 0.2f,
        targetValue = 0.9f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "alpha"
    )

    background(
        color = colorResource(id = R.color.shimmer).copy(alpha = alpha)
    )
}

@Composable
fun ArticleCardShimmer(modifier: Modifier = Modifier) {
    Row(modifier = modifier) {

        Box(
            modifier = modifier
                .clip(MaterialTheme.shapes.medium)
                .size(ArticleCardSize)
                .shimmerEffect()
        )


        Column(
            Modifier.padding(ExtraSmallPadding)
                .height(ArticleCardSize),
            verticalArrangement = Arrangement.SpaceAround

        ) {

            Box(
                modifier.shimmerEffect().height(30.dp).fillMaxWidth().padding(16.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .padding(horizontal = MediumPadding1)
                        .height(15.dp)
                        .shimmerEffect()
                )

            }
        }
    }
}