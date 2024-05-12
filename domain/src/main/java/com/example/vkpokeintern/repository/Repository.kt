package com.example.vkpokeintern.repository

import com.example.vkpokeintern.model.Ability
import com.example.vkpokeintern.model.InfoObject
import com.example.vkpokeintern.model.ListState
import com.example.vkpokeintern.model.Location
import com.example.vkpokeintern.model.Pokemon
import com.example.vkpokeintern.model.Type
import com.example.vkpokeintern.model.UrlHolder
import kotlinx.coroutines.flow.Flow

interface Repository {

    val pokemonList: Flow<List<Pokemon>>
    val abilityList: Flow<List<Ability>>
    val locationList: Flow<List<Location>>
    val typeList: Flow<List<Type>>


    suspend fun getPokemonsInfo(url: String? = null): ListState
    suspend fun getAbilitiesInfo(url: String? = null): ListState
    suspend fun getTypesInfo(url: String? = null): ListState
    suspend fun getLocationsInfo(url: String? = null): ListState
    suspend fun getPokemons(urls: List<String>)
    suspend fun getAbility(url: String)
    suspend fun getLocation(url: String)
    suspend fun getType(url: String)
    suspend fun clearTypes()
    suspend fun clearAbilities()
    suspend fun clearPokemons()
    suspend fun clearLocations()

    suspend fun updateDb()
}