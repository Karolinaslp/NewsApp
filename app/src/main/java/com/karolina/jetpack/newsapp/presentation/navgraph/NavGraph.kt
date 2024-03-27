package com.karolina.jetpack.newsapp.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.karolina.jetpack.newsapp.presentation.bookmark.BookmarkScreen
import com.karolina.jetpack.newsapp.presentation.bookmark.BookmarkViewModel
import com.karolina.jetpack.newsapp.presentation.onboarding.OnBoardingScreen
import com.karolina.jetpack.newsapp.presentation.onboarding.OnBoardingViewModel
import com.karolina.jetpack.newsapp.presentation.search.SearchScreen
import com.karolina.jetpack.newsapp.presentation.search.SearchViewModel

@Composable
fun NavGraph(
    startDestination: String
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ) {
            composable(
                route = Route.OnBoardingScreen.route
            ) {
                val viewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(onEvent = { viewModel.onEvent(it) })
            }
        }

        navigation(
            route = Route.NewsNavigation.route,
            startDestination = Route.NewsNavigatorScreen.route
        ) {
            composable(route = Route.NewsNavigatorScreen.route) {
                val viewModel: BookmarkViewModel = hiltViewModel()
                BookmarkScreen(state = viewModel.state.value, navigateToDetails = {})
            }
        }
    }
}