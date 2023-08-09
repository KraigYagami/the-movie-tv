package com.example.themovietv.modules.home.domain

import com.example.themovietv.common.Result
import com.example.themovietv.modules.home.data.repository.Category
import com.example.themovietv.modules.home.domain.model.MovieModel
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun getCategories(): Flow<Result<Map<Category, List<MovieModel>>>>
}
