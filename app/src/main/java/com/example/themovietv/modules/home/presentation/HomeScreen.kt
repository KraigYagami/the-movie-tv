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
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.PivotOffsets
import androidx.tv.foundation.lazy.list.TvLazyColumn
import androidx.tv.foundation.lazy.list.TvLazyRow
import androidx.tv.foundation.lazy.list.items
import androidx.tv.material3.Card
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import coil.compose.AsyncImage

@Composable
fun HomeScreen() {
    HomeView()
}

@Preview
@Composable
private fun HomePreview() {
    HomeScreen()
}

@Composable
private fun HomeView() {
    val sectionList = mutableListOf<Section>()

    (1..5).forEach { indexSection ->
        sectionList.add(
            Section(
                id = indexSection,
                title = "Section $indexSection",
                movieList = (1..10).map {
                    Movie(
                        id = it,
                        title = "Movie $it",
                        thumbnailUrl = "https://loremflickr.com/320/240?lock=$it"
                    )
                }
            )
        )
    }

    CatalogBrowser(
        sectionList = sectionList,
        onItemSelected = {}
    )
}

@Composable
private fun CatalogBrowser(
    sectionList: List<Section>,
    modifier: Modifier = Modifier,
    onItemSelected: (Movie) -> Unit = {}
) {
    TvLazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        items(sectionList) { section ->
            Section(section, onItemSelected = onItemSelected)
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun Section(
    section: Section,
    modifier: Modifier = Modifier,
    onItemSelected: (Movie) -> Unit = {}
) {
    Text(
        text = section.title,
        style = MaterialTheme.typography.headlineSmall
    )
    Spacer(modifier = Modifier.height(12.dp))
    TvLazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        pivotOffsets = PivotOffsets(0f, 0f)
    ) {
        items(section.movieList) { movie ->
            MovieCard(
                movie = movie,
                onClick = { onItemSelected(movie) }
            )
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun MovieCard(
    movie: Movie,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(modifier = modifier.width(320.dp).height(180.dp), onClick = onClick) {
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = movie.thumbnailUrl,
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

data class Section(
    val id: Int,
    val title: String,
    val movieList: List<Movie>
)

data class Movie(
    val id: Int,
    val title: String,
    val thumbnailUrl: String
)
