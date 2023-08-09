package com.example.themovietv.modules.home.domain.useCases

import com.example.themovietv.modules.home.domain.HomeRepository
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    operator fun invoke() = homeRepository.getCategories()
}
