package com.karolina.jetpack.newsapp.presentation.details

sealed class DetailsEvent {
    object SaveArticle: DetailsEvent()
}