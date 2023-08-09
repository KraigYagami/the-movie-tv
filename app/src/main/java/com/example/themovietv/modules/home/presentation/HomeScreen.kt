package com.example.themovietv.modules.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.tv.foundation.PivotOffsets
import androidx.tv.foundation.lazy.list.TvLazyColumn
import androidx.tv.foundation.lazy.list.TvLazyRow
import androidx.tv.foundation.lazy.list.items
import androidx.tv.material3.Card
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import coil.compose.AsyncImage
import com.example.themovietv.modules.home.data.repository.Category
import com.example.themovietv.modules.home.domain.model.MovieModel

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = hiltViewModel()) {
    LaunchedEffect(Unit) {
        homeViewModel.getCategories()
    }

    val state by homeViewModel.state.collectAsStateWithLifecycle()

    HomeView(state = state)
}

@Preview
@Composable
private fun HomePreview() {
    HomeScreen()
}

@Composable
private fun HomeView(state: HomeViewModel.UiState) {
    if (state.loading) {
        Box(modifier = Modifier.fillMaxSize()) {
            LoadingDialog()
        }
    }

    CatalogBrowser(
        categoryList = state.categories,
        onItemSelected = {}
    )
}

@Composable
fun LoadingDialog() {
}

@Composable
private fun CatalogBrowser(
//    sectionList: List<Section>,
    categoryList: Map<Category, List<MovieModel>>,
    modifier: Modifier = Modifier,
    onItemSelected: (MovieModel) -> Unit = {}
) {
    TvLazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        categoryList.map { (category, movieList) ->
            item {
                Section(
                    title = category.name,
                    movieList = movieList,
                    onItemSelected = onItemSelected
                )
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun Section(
    title: String,
    movieList: List<MovieModel>,
    modifier: Modifier = Modifier,
    onItemSelected: (MovieModel) -> Unit = {}
) {
    Text(
        text = title,
        style = MaterialTheme.typography.headlineSmall
    )
    Spacer(modifier = Modifier.height(12.dp))
    TvLazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        pivotOffsets = PivotOffsets(0f, 0f)
    ) {
        items(movieList) { movie ->
            MovieCard(
                movie = movie
            ) { onItemSelected(movie) }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun MovieCard(
    movie: MovieModel,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(modifier = modifier.width(320.dp).height(180.dp), onClick = onClick) {
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = movie.poster,
                contentDescription = movie.title,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Text(
                text = movie.title,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}
