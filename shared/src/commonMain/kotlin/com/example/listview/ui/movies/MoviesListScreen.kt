package com.example.listview.ui.movies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.example.listview.model.Movie
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun MoviesListRoute(
    viewModel: MovieListViewModel = koinViewModel()
) {
    val state by viewModel.movieListStates.collectAsStateWithLifecycle()
    val listState = rememberLazyListState()

    // dispara quando chega no final visível da lista
    LaunchedEffect(listState, state) {
        snapshotFlow {
            val layoutInfo = listState.layoutInfo
            val totalItems = layoutInfo.totalItemsCount
            val lastVisible = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: -1
            totalItems > 0 && lastVisible >= totalItems - 1
        }
            .distinctUntilChanged()
            .filter { it }
            .collectLatest {
                viewModel.loadPage()
            }
    }

    MoviesListScreen(
        state = state,
        listState = listState
    )
}


@Composable
private fun MoviesListScreen(
    state: MoviesListStates,
    listState: LazyListState
) {
    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (state) {
                MoviesListStates.Loading -> LoadingContent()
                is MoviesListStates.Error -> ErrorContent(message = state.message)
                is MoviesListStates.Success -> MoviesContent(
                    movies = state.movieSections.items,
                    listState = listState
                )
            }
        }
    }
}

@Composable
private fun MoviesContent(
    movies: List<Movie>,
    listState: LazyListState
) {
    LazyColumn(
        state = listState,
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(vertical = 12.dp, horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(movies) { movie ->
            Row {
                AsyncImage(
                    model = movie.posterUrl,
                    contentDescription = "Profile Picture",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(120.dp)
                )

                Text(text = movie.title, style = MaterialTheme.typography.titleMedium)
            }

        }
    }
}


@Composable
private fun LoadingContent() {
    CircularProgressIndicator(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .wrapContentSize(Alignment.Center)
    )
}

@Composable
private fun ErrorContent(message: String) {
    Text(
        text = message,
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .wrapContentSize(Alignment.Center),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.bodyLarge
    )
}


