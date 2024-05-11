package com.example.vkpokeintern.api

import com.example.vkpokeintern.api.dto.AbilityResponse
import com.example.vkpokeintern.api.dto.EncountersListResponse
import com.example.vkpokeintern.api.dto.UrlObjectList
import com.example.vkpokeintern.api.dto.LocationResponse
import com.example.vkpokeintern.api.dto.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {

    @GET("pokemon")
    suspend fun getPokemonsInfo(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ) : UrlObjectList

    @GET("{url}")
    suspend fun getPokemon(
        @Path("url") url: String
    ) : PokemonResponse

    @GET("ability")
    suspend fun getAbilitiesInfo(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ) : UrlObjectList

    @GET("{url}")
    suspend fun getAbility(
        @Path("url") url: String
    ) : AbilityResponse

    @GET("{url}")
    suspend fun getEncountersInfo(
        @Path("url") url: String
    ) : EncountersListResponse

    @GET("location-area")
    suspend fun getLocationsInfo(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ) : UrlObjectList

    @GET("{url}")
    suspend fun getLocation(
        @Path("url") url: String
    ) : LocationResponse

    @GET("{url")
    suspend fun getMovesInfo(
        @Path("url") url: String
    )

    @GET("type")
    suspend fun getTypesInfo(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ) : UrlObjectList
}