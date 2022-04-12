package com.marc.dinino.sandboxxsample.viewmodel

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.marc.dinino.sandboxxsample.R
import com.marc.dinino.sandboxxsample.api.ApiService
import com.marc.dinino.sandboxxsample.model.mostpopular.Media
import com.marc.dinino.sandboxxsample.model.mostpopular.MediaMetadata
import com.marc.dinino.sandboxxsample.model.mostpopular.Result
import com.marc.dinino.sandboxxsample.view.ArticleActivity
import com.marc.dinino.sandboxxsample.view.util.swiperefresh.SwipeRefresh
import com.marc.dinino.sandboxxsample.view.util.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking


object Newsfeed {

    @OptIn(ExperimentalCoilApi::class)
    @Composable
    fun compose(context: Context) {

        // TODO figure out why refresh animation is not displaying on first try

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(stringResource(R.string.newsfeed_name)) },
                    backgroundColor = MaterialTheme.colors.surface,
                )
            },
            modifier = Modifier.fillMaxSize()
        ) { padding ->

            var refreshing by remember { mutableStateOf(true) }
            var mostPopular by remember { mutableStateOf(emptyList<Result>()) }

            LaunchedEffect(refreshing) {
                if (refreshing) {
                    mostPopular = getMostPopularOfLastDayBlocking()
                    refreshing = false
                }
            }

            SwipeRefresh(
                state = rememberSwipeRefreshState(isRefreshing = refreshing),
                onRefresh = { refreshing = true },
            ) {
                LazyColumn(contentPadding = padding) {
                    items(mostPopular.size) { index ->
                        Row(Modifier.padding(16.dp).clickable {
                            ArticleActivity.launch(
                                context = context,
                                url = mostPopular[index].url)
                        }) {
                            Image(
                                painter = rememberImagePainter(mostPopular[index].getThumbnailUrlOrNull()),
                                contentDescription = null,
                                modifier = Modifier.size(64.dp),
                            )

                            Spacer(Modifier.width(8.dp))

                            Text(
                                text = mostPopular[index].title,
                                style = MaterialTheme.typography.subtitle2,
                                modifier = Modifier
                                    .weight(1f)
                                    .align(Alignment.CenterVertically)
                            )
                        }
                    }
                }
            }
        }
    }

    private fun Result?.getThumbnailUrlOrNull(): String? {
        val result = this.getFirstResultThumbnailOrNull()
        return result?.url
    }

    private fun Result?.getFirstResultThumbnailOrNull(): MediaMetadata? {
        return this?.media?.getFirstMediaThumbnailOrNull()
    }

    private fun List<Media>.getFirstMediaThumbnailOrNull(): MediaMetadata? {
        this.forEach {
            val mediaMetadata = it.mediaMetadata.getFirstMediaMetadataThumbnailOrNull()
            if (mediaMetadata != null) return mediaMetadata
        }
        return null
    }

    private fun List<MediaMetadata>.getFirstMediaMetadataThumbnailOrNull(): MediaMetadata? {
        return this.firstOrNull {
             it.format == "Standard Thumbnail"
        }
    }

    private val api = ApiService.create()

    // TODO don't block
    private fun getMostPopularOfLastDayBlocking(): List<Result> {
        return runBlocking(Dispatchers.IO) { // this: CoroutineScope
            api.getMostPopularOfLastDay() ?: emptyList()
        }
    }


}

