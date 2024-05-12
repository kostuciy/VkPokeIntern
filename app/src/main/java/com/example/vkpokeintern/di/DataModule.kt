package com.example.vkpokeintern.di

import android.content.Context
import androidx.room.Room
import com.example.data.api.PokemonService
import com.example.data.db.AppDatabase
import com.example.data.db.dao.PokemonDao
import com.example.vkpokeintern.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    private const val BASE_URL = BuildConfig.BASE_URL
//    TODO: provide retrofit, okhttp (for logging), bd, bd dao as singletons

    @Singleton
    @Provides
    fun provideApiService(
        okHttpClient: OkHttpClient
    ): PokemonService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
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
        )
        .build()

    @Singleton
    @Provides
    fun provideRoomDb(@ApplicationContext applicationContext: Context): AppDatabase
        = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
        "pokemon-database"
        )
        .build()

    @Singleton
    @Provides
    fun provideDao(appDb: AppDatabase): PokemonDao = appDb.dao()
}