package com.example.headlinejetpackcompose.models

import com.example.headlinejetpackcompose.R

data class Page(
    val image: Int,
    val title: String,
    val description: String
)


val pages = listOf(
    Page(R.drawable.onboarding1,
        "Stay Informed",
        "Get the latest breaking news and top stories from trusted sources, all in one place."),
    Page(R.drawable.onboarding2,
        "News That Matters to You",
        "Personalized updates based on your interests — technology, sports, business, and more."),
    Page(R.drawable.onboarding3,
        "Anytime, Anywhere",
        "Read news on the go with a smooth, fast, and distraction-free experience.")
)

