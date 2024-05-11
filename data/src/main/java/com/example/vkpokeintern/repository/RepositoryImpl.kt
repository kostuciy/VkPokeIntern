package com.example.vkpokeintern.repository

import android.util.Log
import com.example.vkpokeintern.api.PokemonService
import com.example.vkpokeintern.api.dto.UrlObjectList
import com.example.vkpokeintern.db.dao.PokemonDao
import com.example.vkpokeintern.utils.EntityUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import okio.IOException
import retrofit2.Response
import javax.inject.Inject

const val REPOSITORY_TAG = "repository_tag"

class RepositoryImpl @Inject constructor(
    private val apiService: PokemonService,
    private val dao: PokemonDao
) : Repository {

    override val pokemonList=
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

    override val typeList=
        dao.getTypes().map { typeEntityList ->
            typeEntityList.map { EntityUtils.toTypeModel(it) }
        }.flowOn(Dispatchers.Main)

    private suspend fun getListInfo(
        apiCallName: String,
        apiCall: suspend () -> Response<UrlObjectList>
    ): UrlObjectList {
        try {
            val response = apiCall()
//                if (url != null) apiService.getPokemonsInfo(url)
//                else apiService.getPokemonsInfo()

            if (!response.isSuccessful) {
                Log.d(REPOSITORY_TAG, "$apiCallName was unsuccessful")
                throw IOException()
            } else if (response.body() == null) {
                Log.d(REPOSITORY_TAG, "$apiCallName body is null")
                throw IOException()
            }

            return response.body()!!
        } catch (e: IOException) {
            Log.d(REPOSITORY_TAG, "Network error occurred in $apiCallName")
            throw IOException()
        } catch (e: Exception) {
            Log.d(REPOSITORY_TAG, "Unexpected error occurred in $apiCallName")
            throw Exception()
        }
    }

    suspend fun getPokemonsInfo(url: String? = null): UrlObjectList =
        getListInfo("getPokemonsInfo") {
            if (url == null) apiService.getPokemonsInfo()
            else apiService.getPokemonsInfo(url)
    }

    suspend fun getAbilitiesInfo(url: String? = null): UrlObjectList =
        getListInfo("getAbilitiesInfo") {
            if (url == null) apiService.getAbilitiesInfo()
            else apiService.getPokemonsInfo(url)
        }

    suspend fun getTypesInfo(url: String? = null): UrlObjectList =
        getListInfo("getTypesInfo") {
            if (url == null) apiService.getTypesInfo()
            else apiService.getTypesInfo(url)
        }

    suspend fun getLocationsInfo(url: String? = null): UrlObjectList =
        getListInfo("getLocationsInfo") {
            if (url == null) apiService.getLocationsInfo()
            else apiService.getLocationsInfo(url)
        }





//        getListInfo(
//            if (url == null) (apiService::getPokemonsInfo)()
//            else (apiService::getPokemonsInfo)(url),
//            "getPokemonInfo"
//        )


//    suspend fun getAbilitiesInfo(url: String? = null): UrlObjectList {
//        try {
//            val response =
//                if (url != null) apiService.getAbilitiesInfo(url)
//                else apiService.getPokemonsInfo()
//
//            if (!response.isSuccessful) {
//                Log.d(REPOSITORY_TAG, "Pokemon data retrieval was unsuccessful")
//                throw IOException()
//            } else if (response.body() == null) {
//                Log.d(REPOSITORY_TAG, "Pokemon data body is null")
//                throw IOException()
//            }
//
//            return response.body()!!
//        } catch (e: IOException) {
//            Log.d(REPOSITORY_TAG, "Network error occurred in getPokemonData")
//            throw IOException()
//        } catch (e: Exception) {
//            Log.d(REPOSITORY_TAG, "Unexpected error occurred in getPokemonData")
//            throw Exception()
//        }
//    }



}