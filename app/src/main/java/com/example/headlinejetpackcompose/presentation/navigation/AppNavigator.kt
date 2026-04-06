package com.example.headlinejetpackcompose.presentation.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.*
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.headlinejetpackcompose.R
import com.example.headlinejetpackcompose.Navigation.Routes
import com.example.headlinejetpackcompose.domain.model.Article
import com.example.headlinejetpackcompose.presentation.bookmark.BookmarkScreen
import com.example.headlinejetpackcompose.presentation.bookmark.BookmarkViewModel
import com.example.headlinejetpackcompose.presentation.details.DetailViewModel
import com.example.headlinejetpackcompose.presentation.details.DetailsScreen
import com.example.headlinejetpackcompose.presentation.home.HomeScreen
import com.example.headlinejetpackcompose.presentation.home.HomeViewModel
import com.example.headlinejetpackcompose.presentation.search.SearchScreen
import com.example.headlinejetpackcompose.presentation.search.SearchViewModel

@Composable
fun AppNavigator() {

    val navController = rememberNavController()

    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.ic_home, text = "Home"),
            BottomNavigationItem(icon = R.drawable.ic_search, text = "Search"),
            BottomNavigationItem(icon = R.drawable.ic_bookmark, text = "Bookmark")
        )
    }

    val backStackEntry by navController.currentBackStackEntryAsState()

    val selectedItem = when (backStackEntry?.destination?.route) {
        Routes.HomeScreen -> 0
        Routes.SearchScreen -> 1
        Routes.BookmarkScreen -> 2
        else -> 0
    }

    val isBottomBarVisible = backStackEntry?.destination?.route in listOf(
        Routes.HomeScreen,
        Routes.SearchScreen,
        Routes.BookmarkScreen
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (isBottomBarVisible) {
                NewsBottomNavigation(
                    items = bottomNavigationItems,
                    selectedItem = selectedItem,
                    onItemClick = { index ->
                        when (index) {
                            0 -> navigateToTab(navController, Routes.HomeScreen)
                            1 -> navigateToTab(navController, Routes.SearchScreen)
                            2 -> navigateToTab(navController, Routes.BookmarkScreen)
                        }
                    }
                )
            }
        }
    ) { paddingValues ->

        NavHost(
            navController = navController,
            startDestination = Routes.HomeScreen,
            modifier = Modifier.padding(paddingValues)
        ) {

            // ---------------- HOME ----------------
            composable(Routes.HomeScreen) {
                val viewModel: HomeViewModel = hiltViewModel()
                val articles = viewModel.news.collectAsLazyPagingItems()

                HomeScreen(
                    articles = articles,
                    navigateToSearch = {
                        navigateToTab(navController, Routes.SearchScreen)
                    },
                    navigateToDetails = { article ->
                        navigateToDetails(navController, article)
                    }
                )
            }

            // ---------------- SEARCH ----------------
            composable(Routes.SearchScreen) {
                val viewModel: SearchViewModel = hiltViewModel()
                val state = viewModel.state

                SearchScreen(
                    state = state,
                    event = viewModel::onEvent,
                    navigateToDetails = { article ->
                        navigateToDetails(navController, article)
                    }
                )
            }

            // ---------------- BOOKMARK ----------------
            composable(Routes.BookmarkScreen) {
                val viewModel: BookmarkViewModel = hiltViewModel()
                val state = viewModel.state

                BookmarkScreen(
                    state = state,
                    navigateToDetails = { article ->
                        navigateToDetails(navController, article)
                    }
                )
            }

            // ---------------- DETAILS ----------------
            composable(Routes.DetailsScreen) {
                val article =
                    navController.previousBackStackEntry
                        ?.savedStateHandle
                        ?.get<Article>("article")

                article?.let {
                    val viewModel: DetailViewModel = hiltViewModel()
                    DetailsScreen(
                        article = it,
                        event = viewModel::onEvent,
                        navigateUp = { navController.navigateUp() },
                        sideEffect = viewModel.sideEffect
                    )
                }
            }
        }
    }
}

/* ---------------- NAV HELPERS ---------------- */

private fun navigateToDetails(
    navController: NavController,
    article: Article
) {
    navController.currentBackStackEntry
        ?.savedStateHandle
        ?.set("article", article)

    navController.navigate(Routes.DetailsScreen)
}

private fun navigateToTab(
    navController: NavController,
    route: String
) {
    navController.navigate(route) {
        popUpTo(navController.graph.startDestinationId) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}
