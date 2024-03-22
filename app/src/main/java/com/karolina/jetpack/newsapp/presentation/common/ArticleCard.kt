package com.karolina.jetpack.newsapp.presentation.common

import android.content.DialogInterface.OnClickListener
import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.karolina.jetpack.newsapp.domain.model.Article
import com.karolina.jetpack.newsapp.domain.model.Source
import com.karolina.jetpack.newsapp.presentation.Dimens.ArticleCardSize
import com.karolina.jetpack.newsapp.ui.theme.NewsAppTheme

@Composable
fun ArticleCard(
    modifier: Modifier = Modifier,
    article: Article,
    onClick: () -> Unit
) {
    val context = LocalContext.current

    Row(modifier = Modifier.clickable { onClick() }) {
        AsyncImage(
            modifier = Modifier
                .size(ArticleCardSize)
                .clip(MaterialTheme.shapes.medium),
            model = ImageRequest.Builder(context).data(article.urlToImage).build(),
            contentDescription = null
        )
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ArticleCardPreview() {
    NewsAppTheme {
        ArticleCard(article = Article(
            author = "",
            content = "",
            description = "",
            source = Source("",""),
            title = "broke don trtrte",
            url = "",
            urlToImage = "",
            publishedAt = "2 hours"
        )) {

        }
    }
}