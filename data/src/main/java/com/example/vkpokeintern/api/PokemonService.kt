package com.example.vkpokeintern.api

import com.example.vkpokeintern.api.dto.AbilityResponse
import com.example.vkpokeintern.api.dto.EncountersListResponse
import com.example.vkpokeintern.api.dto.LocationResponse
import com.example.vkpokeintern.api.dto.PokemonResponse
import com.example.vkpokeintern.api.dto.TypeResponse
import com.example.vkpokeintern.api.dto.UrlObjectList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface PokemonService {

    @GET("pokemon")
    suspend fun getPokemonsInfo(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): Response<UrlObjectList>

    @GET
    suspend fun getPokemonsInfo(
        @Url url: String
    ): Response<UrlObjectList>

    @GET
    suspend fun getPokemon(
        @Url url: String
    ): Response<PokemonResponse>

    @GET("ability")
    suspend fun getAbilitiesInfo(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): Response<UrlObjectList>

    @GET
    suspend fun getAbilitiesInfo(
        @Url url: String
    ): Response<UrlObjectList>

    @GET
    suspend fun getAbility(
        @Url url: String
    ): Response<AbilityResponse>

    @GET
    suspend fun getEncountersInfo(
        @Url url: String
    ): Response<EncountersListResponse>

    @GET("location-area")
    suspend fun getLocationsInfo(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): Response<UrlObjectList>

    @GET
    suspend fun getLocationsInfo(
        @Url url: String
    ): Response<UrlObjectList>

    @GET
    suspend fun getLocation(
        @Url url: String
    ): Response<LocationResponse>

    @GET("type")
    suspend fun getTypesInfo(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): Response<UrlObjectList>

    @GET
    suspend fun getTypesInfo(
        @Url url: String
    ): Response<UrlObjectList>

    @GET
    suspend fun getType(
        @Url url: String
    ): Response<TypeResponse>
}