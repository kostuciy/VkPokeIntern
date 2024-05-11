package com.example.vkpokeintern.di

import com.example.vkpokeintern.BuildConfig
import com.example.vkpokeintern.api.PokemonService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
//    TODO: provide retrofit, okhttp (for logging), bd, bd dao as singletons

    @Singleton
    @Provides
    fun provideApiService(
        okHttpClient: OkHttpClient
    ): PokemonService = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .client(okHttpClient)
        .build()
        .create()

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                if (BuildConfig.DEBUG) {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            }
        ).build()
//
}