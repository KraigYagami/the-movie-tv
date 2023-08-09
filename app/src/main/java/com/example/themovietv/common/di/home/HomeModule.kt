package com.example.themovietv.common.di.home

import com.example.themovietv.common.data.exception.ExceptionRepositoryImpl
import com.example.themovietv.modules.home.data.endpoint.HomeApi
import com.example.themovietv.modules.home.data.repository.HomeRepositoryImpl
import com.example.themovietv.modules.home.domain.HomeRepository
import com.example.themovietv.modules.home.domain.useCases.GetCategoriesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object HomeModule {

    @Provides
    @ViewModelScoped
    fun provideGetCategoriesUseCase(
        homeRepository: HomeRepository
    ) = GetCategoriesUseCase(homeRepository = homeRepository)

    @Provides
    @ViewModelScoped
    fun provideHomeRepository(
        homeApi: HomeApi
    ): HomeRepository = HomeRepositoryImpl(
        homeApi = homeApi,
        domainExceptionRepository = ExceptionRepositoryImpl()
    )

    @Provides
    @ViewModelScoped
    fun provideHomeApiService(
        retrofit: Retrofit
    ): HomeApi = retrofit.create(HomeApi::class.java)
}
