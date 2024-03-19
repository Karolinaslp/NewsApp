package com.karolina.jetpack.newsapp.presentation.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.karolina.jetpack.newsapp.presentation.Dimens.MediumPadding2
import com.karolina.jetpack.newsapp.presentation.Dimens.PageIndicatorWidth
import com.karolina.jetpack.newsapp.presentation.common.NewsButton
import com.karolina.jetpack.newsapp.presentation.common.NewsTextButton
import com.karolina.jetpack.newsapp.presentation.onboarding.components.OnBoardingPage
import com.karolina.jetpack.newsapp.presentation.onboarding.components.PagerIndicator
import kotlinx.coroutines.launch

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
        HorizontalPager(state = pagerState) { index ->
            OnBoardingPage(page = pages[index])
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MediumPadding2)
                //oblicza odległąć od dolnej belki i dodaje do paddingu
                .navigationBarsPadding(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PagerIndicator(
                modifier = Modifier.width(PageIndicatorWidth),
                pagesSize = pages.size,
                selectedPage = pagerState.currentPage
            )

            Row(verticalAlignment = Alignment.CenterVertically) {

                val scope = rememberCoroutineScope()

                //Hide the button when the first element of the list is empty
                if (buttonState.value[0].isNotEmpty()) {
                    NewsTextButton(
                        text = buttonState.value[0],
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(page = pagerState.currentPage - 1)
                            }
                        }
                    )
                }
                NewsButton(text = buttonState.value[1],
                    onClick = {
                        scope.launch {
                            if (pagerState.currentPage == 3) {
                                //TODO: Navigate to HomeScreen
                            } else {
                                pagerState.animateScrollToPage(
                                    page = pagerState.currentPage + 1
                                )
                            }
                        }
                    }
                )
            }
        }
        Spacer(modifier = Modifier.weight(0.5f))
    }
}
