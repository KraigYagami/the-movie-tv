package com.example.themovietv.modules.home.presentation

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.tv.foundation.lazy.list.TvLazyColumn
import androidx.tv.foundation.lazy.list.itemsIndexed
import com.example.themovietv.common.presentation.LoadingDialog
import com.example.themovietv.modules.home.domain.model.MovieModel
import com.example.themovietv.modules.home.presentation.composable.SectionMovies

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = hiltViewModel()) {
    LaunchedEffect(Unit) {
        homeViewModel.getCategories()
    }

    val state by homeViewModel.state.collectAsStateWithLifecycle()
    Log.d("HomeScreen", "HomeScreen: $state")

    HomeView(state = state)
}

@Preview
@Composable
private fun HomePreview() {
    HomeScreen()
}

@Composable
private fun HomeView(state: HomeViewModel.UiState) {
    val context = LocalContext.current
    if (state.loading) {
        LoadingDialog()
    }

    CatalogBrowser(
        sectionCategories = state.sectionCategories,
        onItemSelected = {
            Toast.makeText(context, "Movie: ${it.title}", Toast.LENGTH_SHORT).show()
        }
    )
}

@Composable
private fun CatalogBrowser(
    sectionCategories: List<HomeViewModel.SectionCategories>,
    modifier: Modifier = Modifier,
    onItemSelected: (MovieModel) -> Unit = {}
) {
    val focusedIndexes = remember { mutableStateMapOf(Pair(0, 0)) }

    TvLazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        itemsIndexed(sectionCategories) { rowIndex, rowItem ->
            val focusedIndex = focusedIndexes[rowIndex] ?: -1
            SectionMovies(
                rowItem = rowItem,
                modifier = modifier,
                focusedIndex = focusedIndex,
                focusedIndexes = focusedIndexes,
                rowIndex = rowIndex,
                onItemSelected = onItemSelected
            )
        }
    }
}
