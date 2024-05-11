package com.example.vkpokeintern.di

import com.example.vkpokeintern.repository.Repository
import com.example.vkpokeintern.repository.RepositoryImpl
import com.example.vkpokeintern.usecase.GetAbilityListStateUseCase
import com.example.vkpokeintern.usecase.GetAbilityListUseCase
import com.example.vkpokeintern.usecase.GetLocationListStateUseCase
import com.example.vkpokeintern.usecase.GetLocationListUseCase
import com.example.vkpokeintern.usecase.GetPokemonListStateUseCase
import com.example.vkpokeintern.usecase.GetPokemonListUseCase
import com.example.vkpokeintern.usecase.GetPokemonUseCase
import com.example.vkpokeintern.usecase.GetTypesListStateUseCase
import com.example.vkpokeintern.usecase.GetTypesListUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @Singleton
    @Binds
    abstract fun bindRepositoryImpl(
        repositoryImpl: RepositoryImpl
    ): Repository
}

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {
    @ViewModelScoped
    @Provides
    fun provideGetAbilityListStateUseCase(repository: Repository): GetAbilityListStateUseCase =
        GetAbilityListStateUseCase(repository)

    @ViewModelScoped
    @Provides
    fun provideGetAbilityListUseCase(repository: Repository): GetAbilityListUseCase =
        GetAbilityListUseCase(repository)

    @ViewModelScoped
    @Provides
    fun provideGetLocationListStateUseCase(repository: Repository): GetLocationListStateUseCase =
        GetLocationListStateUseCase(repository)

    @ViewModelScoped
    @Provides
    fun provideGetLocationListUseCase(repository: Repository): GetLocationListUseCase =
        GetLocationListUseCase(repository)

    @ViewModelScoped
    @Provides
    fun provideGetPokemonListStateUseCase(repository: Repository): GetPokemonListStateUseCase =
        GetPokemonListStateUseCase(repository)

    @ViewModelScoped
    @Provides
    fun provideGetPokemonListUseCase(repository: Repository): GetPokemonListUseCase =
        GetPokemonListUseCase(repository)

    @ViewModelScoped
    @Provides
    fun provideGetPokemonUseCase(repository: Repository): GetPokemonUseCase =
        GetPokemonUseCase(repository)

    @ViewModelScoped
    @Provides
    fun provideGetTypesListStateUseCase(repository: Repository): GetTypesListStateUseCase =
        GetTypesListStateUseCase(repository)

    @ViewModelScoped
    @Provides
    fun provideGetTypesListUseCase(repository: Repository): GetTypesListUseCase =
        GetTypesListUseCase(repository)
//    TODO: provide usecases with view model scope
}