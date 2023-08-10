package com.example.themovietv.modules.home.presentation.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.Card
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import coil.compose.AsyncImage
import com.example.themovietv.modules.home.domain.model.MovieModel
import com.example.themovietv.ui.theme.LightBlue

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun MovieCard(
    movie: MovieModel,
    modifier: Modifier = Modifier,
    cardWidth: Dp = 320.dp,
    cardHeight: Dp = 180.dp,
    index: Int = 0,
    hasFocus: Boolean = false,
    onSelected: (index: Int) -> Unit,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .width(if (hasFocus) cardWidth.times(1.1f) else cardWidth)
            .height(if (hasFocus) cardHeight.times(1.1f) else cardHeight)
            .border(
                width = if (hasFocus) 3.dp else 0.dp,
                color = if (hasFocus) LightBlue else Color.Transparent,
                shape = if (hasFocus) RoundedCornerShape(8.dp) else RoundedCornerShape(0.dp)
            )
            .onFocusChanged { if (it.hasFocus) onSelected(index) }
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                onSelected(index)
            },
        onClick = onClick,
        shape = CardDefaults.shape(shape = RoundedCornerShape(8.dp))
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = movie.poster,
                contentDescription = movie.title,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Text(
                text = movie.title,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0x99000000))
                    .padding(8.dp)
                    .align(Alignment.BottomStart)
            )
        }
    }
}
