package com.example.vkpokeintern.repository

import com.example.vkpokeintern.model.Ability
import com.example.vkpokeintern.model.InfoObject
import com.example.vkpokeintern.model.Location
import com.example.vkpokeintern.model.Pokemon
import com.example.vkpokeintern.model.Type
import kotlinx.coroutines.flow.Flow

interface Repository {

    val pokemonList: Flow<List<Pokemon>>
    val abilityList: Flow<List<Ability>>
    val locationList: Flow<List<Location>>
    val typeList: Flow<List<Type>>


//    suspend fun getPokemonsInfo(
//        limit: Int = 20,
//        offset: Int = 0
//    ): InfoObject
//
//    suspend fun getPokemonsInfo(
//        url: String
//    ): InfoObject
//
//    suspend fun getPokemon(url: String): Pokemon
//
//    suspend fun getAbilitiesInfo(
//        limit: Int = 20,
//        offset: Int = 0
//    ): InfoObject
//
//    suspend fun getAbilitiesInfo(
//        url: String
//    ): InfoObject
//
//
//    suspend fun getAbility(
//        url: String
//    ): Ability
//
//    suspend fun getPokemonLocationsInfo(
//        url: String
//    ): List<InfoObject>
//
//    suspend fun getLocationsInfo(
//        limit: Int = 20,
//        offset: Int = 0
//    ): List<InfoObject>
//
//    suspend fun getLocationsInfo(
//        url: String
//    ): List<InfoObject>
//
//    suspend fun getLocation(
//        url: String
//    ): Location
//
//    suspend fun getTypesInfo(
//        limit: Int = 20,
//        offset: Int = 0
//    ): InfoObject
//
//    suspend fun getTypesInfo(
//        url: String
//    ): InfoObject
}