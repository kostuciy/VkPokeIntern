package com.example.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemonentity")
data class PokemonEntity(
    @PrimaryKey val pokemonId: Long,
    val abilities: Map<String, String>, // name to url
    val baseExperience: Int,
    val forms: List<String>,
    val height: Int,
//    val pokemonLocations: Map<String, String>, // name to url,
    val pokemonLocations: String,
    val moves: List<String>,
    val pokemonName: String,
    val sprites: List<String>, // url, first non-null - avatar
    val stats: Map<String, Int>, // stat name to value
    val types: Map<String, String>, // name to url
    val weight: Int
)

