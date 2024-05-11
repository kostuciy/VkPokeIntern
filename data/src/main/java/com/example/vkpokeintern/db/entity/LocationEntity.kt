package com.example.vkpokeintern.db.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity
data class LocationEntity(
    @PrimaryKey val id: Long,
    val name: String,
    val pokemons: Map<String, String> // name to url
)

//data class LocationDetailed(
//    @Embedded val location: LocationEntity,
//    @Relation(
//        parentColumn = "locationId",
//        entityColumn = "pokemonId"
//    )
//    val pokemons: List<PokemonEntity>
//)

