package com.example.vkpokeintern.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.vkpokeintern.db.entity.AbilityEntity
import com.example.vkpokeintern.db.entity.LocationEntity
import com.example.vkpokeintern.db.entity.PokemonEntity
import com.example.vkpokeintern.db.entity.TypeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemonentity")
    fun getPokemons(): Flow<List<PokemonEntity>>

    @Insert
    fun setPokemons(pokemons: List<PokemonEntity>)

    @Query("SELECT * FROM abilityentity")
    fun getAbilities(): Flow<List<AbilityEntity>>

    @Insert
    fun setAbilities(abilities: List<AbilityEntity>)

    @Query("SELECT * FROM typeentity")
    fun getTypes(): Flow<List<TypeEntity>>

    @Insert
    fun setTypes(types: List<TypeEntity>)

    @Query("SELECT * FROM locationentity")
    fun getLocations(): Flow<List<LocationEntity>>

    @Insert
    fun setLocations(locations: List<LocationEntity>)
}