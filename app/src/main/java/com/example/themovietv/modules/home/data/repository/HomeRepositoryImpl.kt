package com.example.themovietv.modules.home.data.repository

import com.example.themovietv.common.Result
import com.example.themovietv.common.data.exception.DomainExceptionRepository
import com.example.themovietv.modules.home.data.endpoint.HomeApi
import com.example.themovietv.modules.home.domain.HomeRepository
import com.example.themovietv.modules.home.domain.model.MovieModel
import javax.inject.Inject
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class HomeRepositoryImpl @Inject constructor(
    private val homeApi: HomeApi,
    private val domainExceptionRepository: DomainExceptionRepository
) : HomeRepository {

    override fun getCategories() = flow<Result<Map<Category, List<MovieModel>>>> {
        val categories = Category.values().associateWith { category ->
            val response = homeApi.getDiscoverMovies(category.id)

            if (response.isSuccessful) {
                response.body()?.let { discoverResponse ->
                    discoverResponse.dataResponses.map { it.toDomain() }
                } ?: emptyList()
            } else {
                throw HttpException(response)
            }
        }

        emit(Result.Success(categories))
    }.catch {
        emit(Result.Failure(domainExceptionRepository.manageError(it)))
    }
}
