package com.karolina.jetpack.newsapp.presentation.onboarding

import androidx.annotation.DrawableRes
import com.loc.newsapp.R

class Page(
    val title: String,
    val description: String,
    @DrawableRes val image:Int
)

val pages = listOf(
    Page(
        title = "Lorem ipsum is simply dummy",
        description = "Lorem Ipsum is simply dummy text of the printing and",
        image = R.drawable.onboarding1
    ),  Page(
        title = "Lorem ipsum is simply dummy",
        description = "Lorem Ipsum is simply dummy text of the printing and",
        image = R.drawable.onboarding2
    ),  Page(
        title = "Lorem ipsum is simply dummy",
        description = "Lorem Ipsum is simply dummy text of the printing and",
        image = R.drawable.onboarding3
    ),
)