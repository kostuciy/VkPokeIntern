package com.example.data.repository

import android.util.Log
import com.example.data.api.PokemonService
import com.example.data.db.dao.PokemonDao
import com.example.data.db.entity.PokemonEntity
import com.example.data.utils.EntityUtils
import com.example.vkpokeintern.model.ListState
import com.example.vkpokeintern.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import okio.IOException
import javax.inject.Inject

const val REPOSITORY_TAG = "repository_tag"

class RepositoryImpl @Inject constructor(
    private val apiService: PokemonService,
    private val dao: PokemonDao
) : Repository {

    override val pokemonList =
        dao.getPokemons().map { pokemonEntityList ->
            pokemonEntityList.map { EntityUtils.toPokemonModel(it) }
        }.flowOn(Dispatchers.Main)

    override val locationList =
        dao.getLocations().map { locationEntityList ->
            locationEntityList.map { EntityUtils.toLocationModel(it) }
        }.flowOn(Dispatchers.Main)

    override val abilityList=
        dao.getAbilities().map { abilityEntityList ->
            abilityEntityList.map { EntityUtils.toAbilityModel(it) }
        }.flowOn(Dispatchers.Main)

    override val typeList =
        dao.getTypes().map { typeEntityList ->
            typeEntityList.map { EntityUtils.toTypeModel(it) }
        }.flowOn(Dispatchers.Main)

    override suspend fun getPokemonsInfo(url: String?): ListState {
//        try { TODO: remove comm
            val response =
                if (url == null) apiService.getPokemonsInfo()
                else apiService.getPokemonsInfo(url)
            if (!response.isSuccessful) {
                Log.d(REPOSITORY_TAG, "getPokemonsInfo was unsuccessful")
                throw IOException()
            } else if (response.body() == null) {
                Log.d(REPOSITORY_TAG, "getPokemonsInfo body is null")
                throw IOException()
            }

            return EntityUtils.toListState(response.body()!!)
//        } catch (e: IOException) {
//            Log.d(REPOSITORY_TAG, "Network error occurred in getPokemonsInfo")
//            throw IOException()
//        } catch (e: Exception) {
//            Log.d(REPOSITORY_TAG, "Unexpected error occurred in getPokemonsInfo")
//            throw Exception()
//        }
    }

    override suspend fun getAbilitiesInfo(url: String?): ListState {
//        try {
            val response =
                if (url == null) apiService.getAbilitiesInfo()
                else apiService.getAbilitiesInfo(url)
            if (!response.isSuccessful) {
                Log.d(REPOSITORY_TAG, "getAbilitiesInfo was unsuccessful")
                throw IOException()
            } else if (response.body() == null) {
                Log.d(REPOSITORY_TAG, "getAbilitiesInfo body is null")
                throw IOException()
            }

            return EntityUtils.toListState(response.body()!!)
//        } catch (e: IOException) {
//            Log.d(REPOSITORY_TAG, "Network error occurred in getAbilitiesInfo")
//            throw IOException()
//        } catch (e: Exception) {
//            Log.d(REPOSITORY_TAG, "Unexpected error occurred in getAbilitiesInfo")
//            throw Exception()
//        }
    }

    override suspend fun getTypesInfo(url: String?): ListState {
//        try {
            val response =
                if (url == null) apiService.getTypesInfo()
                else apiService.getTypesInfo(url)
            if (!response.isSuccessful) {
                Log.d(REPOSITORY_TAG, "getTypesInfo was unsuccessful")
                throw IOException()
            } else if (response.body() == null) {
                Log.d(REPOSITORY_TAG, "getTypesInfo body is null")
                throw IOException()
            }

            return EntityUtils.toListState(response.body()!!)
//        } catch (e: IOException) {
//            Log.d(REPOSITORY_TAG, "Network error occurred in getTypesInfo")
//            throw IOException()
//        } catch (e: Exception) {
//            Log.d(REPOSITORY_TAG, "Unexpected error occurred in getTypesInfo")
//            throw Exception()
//        }
    }

    override suspend fun getLocationsInfo(url: String?): ListState {
//        try {
            val response =
                if (url == null) apiService.getLocationsInfo()
                else apiService.getLocationsInfo(url)
            if (!response.isSuccessful) {
                Log.d(REPOSITORY_TAG, "getLocationsInfo was unsuccessful")
                throw IOException()
            } else if (response.body() == null) {
                Log.d(REPOSITORY_TAG, "getLocationsInfo body is null")
                throw IOException()
            }

            return EntityUtils.toListState(response.body()!!)
//        } catch (e: IOException) {
//            Log.d(REPOSITORY_TAG, "Network error occurred in getLocationsInfo")
//            throw IOException()
//        } catch (e: Exception) {
//            Log.d(REPOSITORY_TAG, "Unexpected error occurred in getLocationsInfo")
//            throw Exception()
//        }
    }

    override suspend fun getPokemons(urls: List<String>) {
        withContext(Dispatchers.IO) {
            val entityList = mutableListOf<PokemonEntity>()
            //        try { TODO: uncom
            urls.forEach { url ->
                val pokemonResponse = apiService.getPokemon(url).let { response ->
                    if (!response.isSuccessful) {
                        Log.d(REPOSITORY_TAG, "getPokemon was unsuccessful")
                        throw IOException()
                    }
                    response.body()
                } ?: throw NullPointerException("getPokemon body is null")
//            val pokemonLocationsResponseList =
////                if (pokemonResponse.location_area_encounters == null) null
//                /*else */apiService.getEncountersInfo(
//                    pokemonResponse.location_area_encounters!! // TODO fix
//                ).let { response ->
//                    if (!response.isSuccessful) {
//                        Log.d(REPOSITORY_TAG, "getAreEncounters was unsuccessful")
//                        throw IOException()
//                    }
//                    response.body()
//                } ?: throw NullPointerException("getAreEncounters body is null")
//            Log.d(REPOSITORY_TAG, "$pokemonResponse")
                val pokemonEntity = EntityUtils.toPokemonEntity(
                    pokemonResponse
                )
                entityList += pokemonEntity
                Log.d(REPOSITORY_TAG, "$pokemonEntity")
//        } catch (e: IOException) {
//            Log.d(REPOSITORY_TAG, "Network error occurred in getPokemon")
//            throw IOException()
//        } catch (e: Exception) {
//            Log.d(REPOSITORY_TAG, "Unexpected error occurred in getPokemon")
//            throw Exception()
//        }
            }
            dao.setPokemons(entityList)
        }
    }

    override suspend fun getAbility(url: String) {
//        try {
            val abilityResponse = apiService.getAbility(url).let { response ->
                if (!response.isSuccessful) {
                    Log.d(REPOSITORY_TAG, "getAbility was unsuccessful")
                    throw IOException()
                }
                response.body()
            } ?: throw NullPointerException("getAbility body is null")

            Log.d(REPOSITORY_TAG, "$abilityResponse")
            val abilityEntity = EntityUtils.toAbilityEntity(
                abilityResponse
            )
            dao.setAbilities(listOf(abilityEntity))
//        } catch (e: IOException) {
//            Log.d(REPOSITORY_TAG, "Network error occurred in getAbility")
//            throw IOException()
//        } catch (e: Exception) {
//            Log.d(REPOSITORY_TAG, "Unexpected error occurred in getAbility")
//            throw Exception()
//        }
    }

    override suspend fun getLocation(url: String) {
//        try {
            val locationResponse = apiService.getLocation(url).let { response ->
                if (!response.isSuccessful) {
                    Log.d(REPOSITORY_TAG, "getLocation was unsuccessful")
                    throw IOException()
                }
                response.body()
            } ?: throw NullPointerException("getLocation body is null")

            val locationEntity = EntityUtils.toLocationEntity(
                locationResponse
            )
            dao.setLocations(listOf(locationEntity))
//        } catch (e: IOException) {
//            Log.d(REPOSITORY_TAG, "Network error occurred in getLocation")
//            throw IOException()
//        } catch (e: Exception) {
//            Log.d(REPOSITORY_TAG, "Unexpected error occurred in getLocation")
//            throw Exception()
//        }
    }

    override suspend fun getType(url: String) {
        try {
            val locationResponse = apiService.getType(url).let { response ->
                if (!response.isSuccessful) {
                    Log.d(REPOSITORY_TAG, "getType was unsuccessful")
                    throw IOException()
                }
                response.body()
            } ?: throw NullPointerException("getType body is null")

            val typeEntity = EntityUtils.toTypeEntity(
                locationResponse
            )
            dao.setTypes(listOf(typeEntity))
        } catch (e: IOException) {
            Log.d(REPOSITORY_TAG, "Network error occurred in getType")
            throw IOException()
        } catch (e: Exception) {
            Log.d(REPOSITORY_TAG, "Unexpected error occurred in getType")
            throw Exception()
        }
    }

    override suspend fun clearPokemons() {
        try {
            dao.removePokemons()
        } catch (e: Exception) {
            Log.d(REPOSITORY_TAG, "Unexpected error occurred in clearPokemons")
            throw Exception()
        }
    }

    override suspend fun clearAbilities() {
        try {
            dao.removeAbilities()
        } catch (e: Exception) {
            Log.d(REPOSITORY_TAG, "Unexpected error occurred in clearAbilities")
            throw Exception()
        }
    }

    override suspend fun clearTypes() {
        try {
            dao.removeTypes()
        } catch (e: Exception) {
            Log.d(REPOSITORY_TAG, "Unexpected error occurred in clearTypes")
            throw Exception()
        }
    }

    override suspend fun clearLocations() {
        try {
            dao.removeLocations()
        } catch (e: Exception) {
            Log.d(REPOSITORY_TAG, "Unexpected error occurred in clearLocations")
            throw Exception()
        }
    }

    override suspend fun updateDb() {

    }

}