package com.example.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.db.entity.AbilityEntity
import com.example.data.db.entity.LocationEntity
import com.example.data.db.entity.PokemonEntity
import com.example.data.db.entity.TypeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemonentity")
    fun getPokemons(): Flow<List<PokemonEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun setPokemons(pokemons: List<PokemonEntity>)

    @Query("SELECT * FROM abilityentity")
    fun getAbilities(): Flow<List<AbilityEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun setAbilities(abilities: List<AbilityEntity>)

    @Query("SELECT * FROM typeentity")
    fun getTypes(): Flow<List<TypeEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun setTypes(types: List<TypeEntity>)

    @Query("SELECT * FROM locationentity")
    fun getLocations(): Flow<List<LocationEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun setLocations(locations: List<LocationEntity>)

    @Query("DELETE FROM pokemonentity")
    suspend fun removePokemons()

    @Query("DELETE FROM abilityentity")
    suspend fun removeAbilities()

    @Query("DELETE FROM typeentity")
    suspend fun removeTypes()

    @Query("DELETE FROM locationentity")
    suspend fun removeLocations()
}