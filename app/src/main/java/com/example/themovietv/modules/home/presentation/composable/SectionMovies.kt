package com.example.themovietv.modules.home.presentation.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.foundation.lazy.list.TvLazyRow
import androidx.tv.foundation.lazy.list.itemsIndexed
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import com.example.themovietv.modules.home.domain.model.MovieModel
import com.example.themovietv.modules.home.presentation.HomeViewModel

@Composable
@OptIn(ExperimentalTvMaterial3Api::class)
fun SectionMovies(
    rowItem: HomeViewModel.SectionCategories,
    modifier: Modifier,
    focusedIndex: Int,
    focusedIndexes: SnapshotStateMap<Int, Int>,
    rowIndex: Int,
    onItemSelected: (MovieModel) -> Unit
) {
    Text(
        text = rowItem.category.name,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    )
    Spacer(modifier = Modifier.height(12.dp))
    TvLazyRow(
        modifier = modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        itemsIndexed(rowItem.movies) { itemIndex, item ->
            MovieCard(
                movie = item,
                index = itemIndex,
                hasFocus = focusedIndex == itemIndex,
                onSelected = {
                    focusedIndexes[rowIndex] = itemIndex
                    focusedIndexes.forEach {
                        if (rowIndex != it.key) {
                            focusedIndexes[it.key] = -1
                        }
                    }
                },
                onClick = { onItemSelected(item) }
            )
        }
    }
}
