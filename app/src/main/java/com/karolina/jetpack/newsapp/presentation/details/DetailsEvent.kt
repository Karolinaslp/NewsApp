package com.karolina.jetpack.newsapp.presentation.details

import com.karolina.jetpack.newsapp.domain.model.Article

sealed class DetailsEvent {
    data class UpsertDeleteArticle(val article: Article) : DetailsEvent()

    object RemoveSideEffect : DetailsEvent()
}