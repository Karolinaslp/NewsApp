package com.karolina.jetpack.newsapp.presentation.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.karolina.jetpack.newsapp.presentation.onboarding.components.OnBoardingPage

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen() {
    Column(modifier = Modifier.fillMaxSize()) {

        //Zapisywanie stanu strony, PagerState służy do manipulowania i zapisywania na której stronie jesteśmy
        val pagerState = rememberPagerState(initialPage = 0) {
            pages.size
        }

        val buttonState = remember {
            //Zapisuje i ustawia stan przycisku(na pierwszej stronie nie chcemy "Back"
            // a na ostatniej nie chcemy "Next" ale chcemy mieć "Get Started"
            //Stan przycisku jest zależny od PagerState
            derivedStateOf {
                when (pagerState.currentPage) {
                    0 -> listOf("", "Next")
                    1 -> listOf("Back", "Next")
                    2 -> listOf("Back", "get Started")
                    else -> listOf("", "")
                }
            }
        }
        HorizontalPager(state = pagerState ) { index ->
            OnBoardingPage(page = pages[index])
        }
    }
}