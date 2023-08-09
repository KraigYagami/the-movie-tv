package com.example.themovietv.modules.home.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themovietv.common.fold
import com.example.themovietv.modules.home.data.repository.Category
import com.example.themovietv.modules.home.domain.model.MovieModel
import com.example.themovietv.modules.home.domain.useCases.GetCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val coroutineDispatcher: CoroutineDispatcher
) : ViewModel() {

    private var _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()

    fun getCategories() {
        getCategoriesUseCase().map { result ->
            result.fold(
                onSuccess = { categories ->
                    Log.d("HomeViewModel", "getCategories: $categories")
                    _state.value = _state.value.copy(
                        loading = false,
                        categories = categories
                    )
                },
                onFailure = { error ->
                    Log.d("HomeViewModel", "getCategories: $error")
                    _state.value = _state.value.copy(
                        loading = false,
                        errorMessage = error.message
                    )
                }
            )
        }.onStart {
            _state.value = _state.value.copy(loading = true)
        }.flowOn(coroutineDispatcher).launchIn(viewModelScope)
    }

    data class UiState(
        val loading: Boolean = false,
        val categories: Map<Category, List<MovieModel>> = emptyMap(),
        val errorMessage: String? = null
    )
}
