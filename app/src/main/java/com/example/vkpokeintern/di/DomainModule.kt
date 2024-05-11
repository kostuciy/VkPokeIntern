package com.example.vkpokeintern.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {
//    TODO: provide repo (with repo implem) as singleton

}

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {
//    TODO: provide usecases with view model scope
}